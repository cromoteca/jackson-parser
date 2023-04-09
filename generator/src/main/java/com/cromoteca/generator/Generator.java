package com.cromoteca.generator;

import com.cromoteca.ScanResult;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import java.lang.reflect.Parameter;
import java.lang.reflect.TypeVariable;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.lang.NonNullApi;

public class Generator {
  private final ScanResult scan;

  public Generator(ScanResult scan) {
    this.scan = scan;
  }

  public Map<Class<?>, String> generateEndpoints() {
    return scan.endpoints().stream()
        .collect(
            Collectors.toMap(
                endpoint -> endpoint.type().getBeanClass(),
                endpoint -> {
                  var worker = new Worker(endpoint);

                  return StringTemplate.from(
                      """
                    ${imports}${methodImplementations}

                    const ${name} = {
                    ${methodList}
                    };

                    export default ${name};
                          """,
                      worker);
                }));
  }

  public Map<Class<?>, String> generateEntities() {
    return scan.entities().stream()
        .collect(
            Collectors.toMap(
                ScanResult.EntityClass::type,
                entity -> {
                  var worker = new Worker(entity);

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

  public class Worker {

    private final Set<String> keywords = new HashSet<>();
    private final List<Import> imports = new ArrayList<>();
    private final Class<?> mainClass;
    private final boolean nonNullApi;
    private final String name;
    private List<String> methodImplementations;
    private List<String> methods;
    private String initTypeName;
    private String clientVariableName;
    private List<String> properties;

    public Worker(ScanResult.EndpointClass endpoint) {
      name = endpoint.getName();
      mainClass = endpoint.type().getBeanClass();
      nonNullApi =
          endpoint
              .type()
              .getType()
              .getRawClass()
              .getPackage()
              .isAnnotationPresent(NonNullApi.class);
      endpoint
          .methods()
          .forEach(
              method ->
                  Arrays.stream(method.getAnnotated().getParameters())
                      .forEach(p -> keywords.add(p.getName())));
      initTypeName = addImport("EndpointRequestInit", "@hilla/frontend", false, true);
      clientVariableName = addImport("client", "/connect-client.default", true, false);
      methodImplementations = generateMethodImplementations(endpoint);
      methods = generateMethodList(endpoint);
    }

    public Worker(ScanResult.EntityClass entity) {
      name = entity.getName();
      mainClass = entity.type();
      nonNullApi = nonNullApi(entity.type());
      entity.properties().forEach(property -> keywords.add(property.getName()));
      keywords.add(entity.getName());
      properties = generateProperties(entity);
    }

    private static boolean nonNullApi(Class<?> cls) {
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
                                    .collect(Collectors.joining(", ", "{ ", " }")))
                        .filter(s -> !s.isBlank())
                        .collect(Collectors.joining(", "));

                var relativePath = NameResolver.resolve(from, mainClass.getPackageName());

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

    private String addImport(String variable, String from, boolean isDefault, boolean isType) {
      var existing =
          imports.stream()
              .filter(
                  i ->
                      i.variable().equals(variable)
                          && i.from().equals(from)
                          && i.isDefault() == isDefault
                          && i.isType() == isType)
              .findFirst();

      return existing
          .orElseGet(
              () -> {
                var alias = variable;

                while (keywords.contains(alias)) {
                  alias = incrementSuffix(alias);
                }

                var newImport = new Import(variable, from, isDefault, isType, alias);
                imports.add(newImport);
                keywords.add(alias);
                return newImport;
              })
          .alias();
    }

    // regular expression to match the last numeric suffix
    private static final Pattern SUFFIX_REGEX = Pattern.compile("^(.*?)(\\d+)?$");

    private static String incrementSuffix(String str) {

      // match the last numeric suffix in the string
      var matcher = SUFFIX_REGEX.matcher(str);

      if (matcher.find()) {
        // extract the base string and the last numeric suffix
        var base = matcher.group(1);
        var suffix = matcher.group(2);

        if (suffix == null) {
          // no numeric suffix found, so add a suffix of "1"
          return base + '1';
        } else {
          // increment the numeric suffix and return the modified string
          return base + (Integer.parseInt(suffix) + 1);
        }
      } else {
        // no match found, so return the original string with a suffix of "1"
        return str + '1';
      }
    }

    private List<String> generateMethodImplementations(ScanResult.EndpointClass result) {
      return result.methods().stream()
          .sorted(Comparator.comparing(AnnotatedMethod::getName))
          .map(method -> generateMethod(method, result.type().getBeanClass().getName()))
          .toList();
    }

    private List<String> generateMethodList(ScanResult.EndpointClass result) {
      return result.methods().stream()
          .sorted(Comparator.comparing(AnnotatedMethod::getName))
          .map(method -> """
            \s   %s,""".formatted(method.getName()))
          .toList();
    }

    private String generateMethod(AnnotatedMethod method, String className) {
      return StringTemplate.from(
          """
        async function ${name}${typeParams}(${paramList}): Promise<${returnType}> {
            return ${client}.call('${class}', '${method}', {${paramNames}}, ${init});
        }""",
          Map.of(
              "name",
              method.getName(),
              "typeParams",
              generateTypeParams(method.getAnnotated().getTypeParameters()),
              "paramList",
              generateParamList(method),
              "returnType",
              generateType(
                  new FullType(method.getType(), method.getAnnotated().getAnnotatedReturnType())),
              "client",
              clientVariableName,
              "class",
              className,
              "method",
              method.getName(),
              "paramNames",
              generateParamNameObject(method),
              "init",
              chooseInitParamName(method)));
    }

    private String generateTypeParams(TypeVariable<?>[] typeParameters) {
      return typeParameters.length == 0
          ? ""
          : Arrays.stream(typeParameters)
              .map(TypeVariable::getName)
              .collect(Collectors.joining(", ", "<", ">"));
    }

    private String generateParamList(AnnotatedMethod method) {
      var params = new ArrayList<String>();

      for (var i = 0; i < method.getParameterCount(); i++) {
        var param = method.getParameter(i);
        var generic = method.getAnnotated().getAnnotatedParameterTypes()[i];
        var javaParam = method.getAnnotated().getParameters()[i];
        params.add(
            javaParam.getName() + ": " + generateType(new FullType(param.getType(), generic)));
      }

      params.add(chooseInitParamName(method) + "?: " + initTypeName);

      return String.join(", ", params);
    }

    private String generateParamNameObject(AnnotatedMethod method) {
      var params =
          Arrays.stream(method.getAnnotated().getParameters())
              .map(Parameter::getName)
              .collect(Collectors.joining(", "));
      return params.isEmpty() ? "" : ' ' + params + ' ';
    }

    private String chooseInitParamName(AnnotatedMethod method) {
      var initParam = "init";
      var names =
          Arrays.stream(method.getAnnotated().getParameters()).map(Parameter::getName).toList();

      while (names.contains(initParam)) {
        initParam = '_' + initParam;
      }

      return initParam;
    }

    private List<String> generateProperties(ScanResult.EntityClass entity) {
      return entity.type().isEnum()
          ? Arrays.stream(entity.type().getEnumConstants())
              .map(Object::toString)
              .sorted()
              .map(this::generateEnumConstant)
              .toList()
          : entity.properties().stream()
              .sorted(Comparator.comparing(BeanPropertyDefinition::getName))
              .map(this::generateProperty)
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

    private String generateType(FullType type) {
      String result;
      var converted = scan.convertedClasses().get(type.getRawClass());
      if (converted != null) {
        result = generateType(new FullType(converted, null));
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
        result = mapType(type.rawTypeName());
        var typeVariable = type.typeVariableName();
        var parameterizedTypes =
            type.parameterizedTypes()
                .map(s -> s.map(this::generateType).collect(Collectors.joining(", ", "<", ">")));
        result = typeVariable.orElse(result + parameterizedTypes.orElse(""));
      }

      boolean nullable;
      if (type.isOptional()) {
        nullable = true;
      } else if (type.isPrimitive()) {
        nullable = false;
      } else {
        nullable = Optional.ofNullable(type.nullable()).orElse(!nonNullApi);
      }

      return nullable ? result + " | undefined" : result;
    }

    private String mapType(String typeName) {
      var type =
          switch (typeName) {
            case "char", "java.lang.Character", "java.lang.String" -> "string";
            case "java.lang.Boolean" -> "boolean";
            case "java.lang.Object" -> "unknown";
            case "int",
                "long",
                "float",
                "double",
                "byte",
                "short",
                "java.lang.Integer",
                "java.lang.Long",
                "java.lang.Float",
                "java.lang.Double",
                "java.lang.Byte",
                "java.lang.Short" -> "number";
            default -> typeName;
          };

      if (type.contains(".")) {
        type = addImport(type.replaceAll(".*[.$]", ""), type, true, true);
      }

      return type;
    }
  }
}
