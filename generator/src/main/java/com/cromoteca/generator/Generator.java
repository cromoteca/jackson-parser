package com.cromoteca.generator;

import com.cromoteca.ScanResult;
import com.cromoteca.generator.types.BooleanTypeHandler;
import com.cromoteca.generator.types.FluxTypeHandler;
import com.cromoteca.generator.types.NumberTypeHandler;
import com.cromoteca.generator.types.StringTypeHandler;
import com.cromoteca.generator.types.TypeHandler;
import com.cromoteca.generator.types.UnknownTypeHandler;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.lang.annotation.Annotation;
import java.lang.reflect.TypeVariable;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.lang.NonNullApi;

public class Generator {
  private final ScanResult scan;
  private final Map<Class<?>, TypeHandler> typeHandlers =
      new HashMap<>() {
        private final TypeHandler defaultTypeHandler = new TypeHandler();
        private final List<TypeHandler> _typeHandlers =
            List.of(
                new NumberTypeHandler(),
                new BooleanTypeHandler(),
                new StringTypeHandler(),
                new UnknownTypeHandler(),
                new FluxTypeHandler());

        @Override
        public TypeHandler get(Object key) {
          if (key instanceof Class<?> cls) {
            return super.computeIfAbsent(
                cls,
                k ->
                    _typeHandlers.stream()
                        .filter(h -> h.supportedTypes().contains(k))
                        .findFirst()
                        .orElse(defaultTypeHandler));
          }
          throw new IllegalArgumentException("key must be a Class<?>");
        }
      };

  public Generator(ScanResult scan) {
    this.scan = scan;
  }

  public Map<String, String> generateEndpoints() {
    return scan.endpoints().stream()
        .collect(
            Collectors.toMap(
                endpoint -> endpoint.type().getBeanClass().getName(),
                endpoint -> {
                  var worker = new Worker(endpoint);
                  var maker = new EndpointMaker(worker, endpoint);
                  return maker.generate();
                }));
  }

  public Map<String, String> generateEntities() {
    return scan.entities().stream()
        .filter(entityClass -> typeHandlers.get(entityClass.type()).generateEntity())
        .collect(
            Collectors.toMap(
                entityClass -> entityClass.type().getName(),
                entity -> {
                  var worker = new Worker(entity, EntityFile.TYPE);

                  return StringTemplate.from(
                      """
                      ${imports}${construct} ${name}${typeParams} {
                      ${properties}
                      }

                      export default ${name};
                      """,
                      Map.of(
                          "imports",
                          worker.getImports(),
                          "construct",
                          entity.type().isEnum() ? "enum" : "interface",
                          "name",
                          entity.getName(),
                          "typeParams",
                          worker.generateTypeParams(entity.type().getTypeParameters()),
                          "properties",
                          worker.getProperties()));
                }));
  }

  public Map<String, String> generateModels() {
    return scan.entities().stream()
        .collect(
            Collectors.toMap(
                entityClass -> entityClass.type().getName() + "Model",
                entity -> {
                  var worker = new Worker(entity, EntityFile.MODEL);

                  return StringTemplate.from(
                      """
                      ${imports}class ${name}Model<T extends ${name} = ${name}> extends ${objectModel}<T> {
                          declare static createEmptyValue: () => ${name};
                      ${properties}
                      }

                      export default ${name}Model;
                      """,
                      Map.of(
                          "name",
                          worker.getModelType(),
                          "imports",
                          worker.getImports(),
                          "properties",
                          worker.getProperties(),
                          "objectModel",
                          worker.getObjectModel()));
                }));
  }

  public Map<String, String> generateYup() {
    return scan.entities().stream()
        .filter(entity -> entity.type().getAnnotation(Valid.class) != null)
        .collect(
            Collectors.toMap(
                entityClass -> entityClass.type().getName() + "FormikValidation",
                entity -> {
                  var worker = new Worker(entity, EntityFile.YUP);

                  return StringTemplate.from(
                      """
                      const emptyValue: ${className} = {
                        ${emptyValues}
                      };

                      const validationSchema: ObjectSchema<${className}> = object({
                        ${validationSchema}
                      });

                      export { emptyValue, validationSchema };
                      """,
                      Map.of(
                          "className",
                          entity.getName(),
                          "emptyValues",
                          worker.generateEmptyValues(entity),
                          "validationSchema",
                          worker.generateValidationSchema(entity)));
                }));
  }

  enum EntityFile {
    TYPE,
    MODEL,
    YUP
  }

  public class Worker implements MakerTools {
    private final Set<String> keywords = new HashSet<>();
    private final List<Import> imports = new ArrayList<>();
    private final String mainClass;
    private final boolean nonNullApi;
    private final String name;
    private List<String> methodImplementations;
    private List<String> methods;
    private List<String> properties;
    private String importedObjectModel;
    private String importedModelType;

    Worker(ScanResult.EndpointClass endpoint) {
      name = endpoint.getName();
      mainClass = endpoint.type().getBeanClass().getPackageName();
      nonNullApi = findNonNullApiAnnotation(endpoint.type().getType().getRawClass());
      endpoint
          .methods()
          .forEach(
              method ->
                  Arrays.stream(method.getAnnotated().getParameters())
                      .forEach(p -> keywords.add(p.getName())));
    }

    Worker(ScanResult.EntityClass entity, EntityFile file) {
      name = entity.getName();
      mainClass = entity.type().getName().replaceFirst("[.$][^.$]+$", "");
      nonNullApi = findNonNullApiAnnotation(entity.type());
      entity.properties().forEach(property -> keywords.add(property.getName()));

      switch (file) {
        case TYPE -> {
          keywords.add(entity.getName());
          properties = generateProperties(entity);
        }
        case MODEL -> {
          importedObjectModel = addImport("ObjectModel", "@hilla/form", false, false);
          importedModelType = addImport(entity.getName(), entity.type().getName(), true, true);
          keywords.add("createEmptyValue");
          properties = generateModelFunctions(entity);
        }
      }
    }

    private static boolean findNonNullApiAnnotation(Class<?> cls) {
      var classLoader = cls.getClassLoader();

      return Stream.iterate(
              cls.getPackageName(), n -> n.contains("."), n -> n.substring(0, n.lastIndexOf('.')))
          .map(classLoader::getDefinedPackage)
          .filter(Objects::nonNull)
          .anyMatch(pkg -> pkg.isAnnotationPresent(NonNullApi.class));
    }

    public String getName() {
      return name;
    }

    @Override
    public String getImports() {
      var groupedImports = new TreeMap<String, String>();

      imports.stream()
          .collect(Collectors.groupingBy(Import::from))
          .forEach(
              (from, sameFrom) -> {
                var type = sameFrom.stream().anyMatch(Import::isType) ? "type " : "";
                var defaultImport = sameFrom.stream().filter(Import::isDefault).findFirst();
                var namedImports =
                    sameFrom.stream()
                        .filter(i -> !i.isDefault())
                        .map(
                            i ->
                                i.variable().equals(i.alias())
                                    ? i.alias()
                                    : i.variable() + " as " + i.alias())
                        .toList();

                var imported =
                    Stream.of(
                            defaultImport.map(Import::alias).orElse(""),
                            namedImports.isEmpty()
                                ? ""
                                : namedImports.stream()
                                    .sorted()
                                    .collect(Collectors.joining(", ", "{ ", " }")))
                        .filter(s -> !s.isBlank())
                        .collect(Collectors.joining(", "));

                var relativePath = NameResolver.resolve(from, mainClass);

                groupedImports.put(
                    relativePath,
                    StringTemplate.from(
                        """
                        import ${type}${imported} from '${path}';
                        """,
                        Map.of(
                            "type",
                            type,
                            "imported",
                            imported,
                            "path",
                            relativePath.startsWith("@") ? relativePath : relativePath + ".js")));
              });

      var lines = String.join("", groupedImports.values());
      return lines.isEmpty() ? lines : lines + '\n';
    }

    public String getMethodImplementations() {
      return String.join("\n\n", methodImplementations);
    }

    public String getMethodList() {
      return String.join("\n", methods);
    }

    public String getProperties() {
      return String.join("\n", properties);
    }

    public String getObjectModel() {
      return importedObjectModel;
    }

    public String getModelType() {
      return importedModelType;
    }

    public String addImport(String variable, String from, boolean isDefault, boolean isType) {
      var existing =
          imports.stream()
              .filter(
                  i ->
                      Objects.equals(i.variable(), variable)
                          && Objects.equals(i.from(), from)
                          && i.isDefault() == isDefault
                          && i.isType() == isType)
              .findFirst();

      return existing
          .orElseGet(
              () -> {
                String alias = findAlias(variable);
                var newImport = new Import(variable, from, isDefault, isType, alias);
                imports.add(newImport);
                return newImport;
              })
          .alias();
    }

    private String findAlias(String variable) {
      while (keywords.contains(variable)) {
        // match the last numeric suffix in the string
        var matcher = SUFFIX_REGEX.matcher(variable);

        if (matcher.find()) {
          // extract the base string and the last numeric suffix
          var base = matcher.group(1);
          var suffix = matcher.group(2);

          if (suffix == null) {
            // no numeric suffix found, so add a suffix of "1"
            variable = base + '1';
          } else {
            // increment the numeric suffix and return the modified string
            variable = base + (Integer.parseInt(suffix) + 1);
          }
        } else {
          // no match found, so return the original string with a suffix of "1"
          variable += '1';
        }
      }

      keywords.add(variable);
      return variable;
    }

    // regular expression to match the last numeric suffix
    private static final Pattern SUFFIX_REGEX = Pattern.compile("^(.*?)(\\d+)?$");

    @Override
    public String generateTypeParams(TypeVariable<?>[] typeParameters) {
      return typeParameters.length == 0
          ? ""
          : Arrays.stream(typeParameters)
              .map(TypeVariable::getName)
              .collect(Collectors.joining(", ", "<", ">"));
    }

    private List<String> generateProperties(ScanResult.EntityClass entity) {
      if (entity.type().isEnum()) {
        return Arrays.stream(entity.type().getEnumConstants())
            .map(Object::toString)
            .sorted()
            .map(this::generateEnumConstant)
            .toList();
      }

      Stream<BeanPropertyDefinition> propertyStream = entity.properties().stream();
      JsonIgnoreProperties annotation = entity.type().getAnnotation(JsonIgnoreProperties.class);

      if (!(annotation == null
          || annotation.value() == null
          || annotation.allowGetters()
          || annotation.allowSetters())) {
        var ignoredProperties = Arrays.asList(annotation.value());
        propertyStream = propertyStream.filter(prop -> !ignoredProperties.contains(prop.getName()));
      }

      return propertyStream
          .sorted(Comparator.comparing(BeanPropertyDefinition::getName))
          .map(this::generateProperty)
          .toList();
    }

    private List<String> generateModelFunctions(ScanResult.EntityClass entity) {
      return entity.properties().stream()
          .sorted(Comparator.comparing(BeanPropertyDefinition::getName))
          .map(this::generateModelFunction)
          .toList();
    }

    private String generateEnumConstant(String value) {
      return """
          \s   %s = "%s",""".formatted(value, value);
    }

    private String generateProperty(BeanPropertyDefinition property) {
      var propertyType = MultipleType.forProperty(property);

      return """
          \s   %s: %s;"""
          .formatted(property.getName(), generateType(propertyType));
    }

    String generateEmptyValues(ScanResult.EntityClass entity) {
      return entity.properties().stream()
          .map(prop -> typeHandlers.get(prop.getRawPrimaryType()).emptyValue())
          .collect(Collectors.joining(", "));
    }

    String generateValidationSchema(ScanResult.EntityClass entity) {
      return entity.properties().stream()
          .map(
              prop -> {
                var constraints = new HashSet<String>();
                var annotations =
                    Arrays.stream(MultipleType.forProperty(prop).getAnnotations())
                        .collect(Collectors.toMap(Annotation::annotationType, Function.identity()));

                var notBlank = (NotBlank) annotations.get(NotBlank.class);
                if (notBlank != null) {
                  constraints.add("required()");
                }
                var email = (Email) annotations.get(Email.class);
                if (email != null) {
                  constraints.add("email()");
                }

                return constraints.isEmpty()
                    ? ""
                    : prop.getName() + "." + String.join(".", constraints);
              })
          .collect(Collectors.joining("\n"));
    }

    private String generateModelFunction(BeanPropertyDefinition property) {
      var propertyType = MultipleType.forProperty(property);
      var getPropertyModel = addImport("_getPropertyModel", "@hilla/form", false, false);

      return StringTemplate.from(
          """

      \s   get ${name}(): ${modelType} {
              return this[${getPropertyModel}]('${name}', ${modelType}, [${nullable}]) as ${modelType};
          }""",
          Map.of(
              "name",
              property.getName(),
              "modelType",
              chooseModel(propertyType),
              "nullable",
              isNullable(propertyType),
              "getPropertyModel",
              getPropertyModel));
    }

    private String chooseModel(FullType type) {
      return addImport("StringModel", "@hilla/form", false, false);
    }

    @Override
    public TypeHandler handlerFor(Class<?> cls) {
      return typeHandlers.get(cls);
    }

    @Override
    public String generateType(FullType type) {
      String result;
      var converted = scan.convertedClasses().get(type.getRawClass());
      if (converted != null) {
        result = generateType(new FullType(converted));
      } else if (type.isOptional()) {
        result = generateType(type.getBoundType());
      } else if (type.isCollectionLikeType()) {
        result = "Array<" + generateType(type.getContentType()) + '>';
      } else if (type.isArrayType()) {
        result = "Array<" + generateType(type.getArrayType()) + '>';
      } else if (type.isMapLikeType()) {
        var mapTypes = type.getMapTypes();
        result = "Map<" + generateType(mapTypes[0]) + ", " + generateType(mapTypes[1]) + '>';
      } else {
        result = clientType(type.getRawClass());
        var typeVariable = type.typeVariableName();
        var parameterizedTypes =
            type.parameterizedTypes()
                .map(s -> s.map(this::generateType).collect(Collectors.joining(", ", "<", ">")));
        result = typeVariable.orElse(result + parameterizedTypes.orElse(""));
      }

      return isNullable(type) ? result + " | undefined" : result;
    }

    private boolean isNullable(FullType type) {
      boolean nullable;
      if (type.isOptional()) {
        nullable = true;
      } else if (type.isPrimitive()) {
        nullable = false;
      } else {
        nullable = Objects.requireNonNullElse(type.nullable(), !nonNullApi);
      }
      return nullable;
    }

    private String clientType(Class<?> type) {
      var handler = typeHandlers.get(type);
      var clientType = handler.parameterType(type);

      if (clientType.contains(".")) {
        clientType = addImport(clientType.replaceAll(".*[.$]", ""), clientType, true, true);
      }

      return clientType;
    }
  }
}
