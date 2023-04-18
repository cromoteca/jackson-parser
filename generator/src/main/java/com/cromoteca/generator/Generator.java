package com.cromoteca.generator;

import com.cromoteca.ScanResult;
import com.cromoteca.generator.types.BooleanTypeHandler;
import com.cromoteca.generator.types.FluxTypeHandler;
import com.cromoteca.generator.types.NumberTypeHandler;
import com.cromoteca.generator.types.StringTypeHandler;
import com.cromoteca.generator.types.TypeHandler;
import com.cromoteca.generator.types.UnknownTypeHandler;
import jakarta.validation.Valid;
import java.lang.reflect.TypeVariable;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                  var worker = new Worker(entity);
                  var maker = new EntityMaker(worker, entity);
                  return maker.generate();
                }));
  }

  public Map<String, String> generateModels() {
    return scan.entities().stream()
        .collect(
            Collectors.toMap(
                entityClass -> entityClass.type().getName() + "Model",
                entity -> {
                  var worker = new Worker(entity);
                  var maker = new EntityModelMaker(worker, entity);
                  return maker.generate();
                }));
  }

  public Map<String, String> generateFormikValidation() {
    return scan.entities().stream()
        .filter(entity -> entity.type().getAnnotation(Valid.class) != null)
        .collect(
            Collectors.toMap(
                entityClass -> entityClass.type().getName() + "FormikValidation",
                entity -> {
                  var worker = new Worker(entity);
                  var maker = new FormikValidationMaker(worker, entity);
                  return maker.generate();
                }));
  }

  public Map<String, String> generateYupSchema() {
    return scan.entities().stream()
        .filter(entity -> entity.type().getAnnotation(Valid.class) != null)
        .collect(
            Collectors.toMap(
                entityClass -> entityClass.type().getName() + "YupSchema",
                entity -> {
                  var worker = new Worker(entity);
                  var maker = new YupSchemaMaker(worker, entity);
                  return maker.generate();
                }));
  }

  public class Worker implements MakerTools {
    // regular expression to match the last numeric suffix
    private static final Pattern SUFFIX_REGEX = Pattern.compile("^(.*?)(\\d+)?$");

    private final Set<String> keywords = new HashSet<>();
    private final List<Import> imports = new ArrayList<>();
    private final String mainClass;
    private final boolean nonNullApi;

    Worker(ScanResult.EndpointClass endpoint) {
      mainClass = endpoint.type().getBeanClass().getPackageName();
      nonNullApi = MakerTools.insideNonNullApi(endpoint.type().getType().getRawClass());
      endpoint
          .methods()
          .forEach(
              method ->
                  Arrays.stream(method.getAnnotated().getParameters())
                      .forEach(p -> addKeyword(p.getName())));
    }

    Worker(ScanResult.EntityClass entity) {
      mainClass = entity.type().getName().replaceFirst("[.$][^.$]+$", "");
      nonNullApi = MakerTools.insideNonNullApi(entity.type());
      entity.properties().forEach(property -> addKeyword(property.getName()));
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

    @Override
    public String fromImport(String variable, String from, boolean isDefault, boolean isType) {
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

      addKeyword(variable);
      return variable;
    }

    @Override
    public void addKeyword(String variable) {
      keywords.add(variable);
    }

    @Override
    public String generateTypeParams(TypeVariable<?>[] typeParameters) {
      return typeParameters.length == 0
          ? ""
          : Arrays.stream(typeParameters)
              .map(TypeVariable::getName)
              .collect(Collectors.joining(", ", "<", ">"));
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
        clientType = fromImport(clientType.replaceAll(".*[.$]", ""), clientType, true, true);
      }

      return clientType;
    }
  }
}
