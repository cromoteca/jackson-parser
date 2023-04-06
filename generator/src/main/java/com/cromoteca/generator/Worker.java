package com.cromoteca.generator;

import com.cromoteca.ScanResult;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import java.lang.reflect.Parameter;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.lang.NonNullApi;

class Worker {

  private final Set<String> keywords = new HashSet<>();
  private final List<Import> imports = new ArrayList<>();
  private final Class<?> mainClass;
  private final boolean nonNullApi;
  private List<String> methodImplementations;
  private List<String> methods;
  private String initTypeName;
  private String clientVariableName;
  private List<String> properties;

  Worker(ScanResult.EndpointClass endpoint) {
    mainClass = endpoint.type().getBeanClass();
    nonNullApi =
        endpoint.type().getType().getRawClass().getPackage().isAnnotationPresent(NonNullApi.class);
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

  Worker(ScanResult.EntityClass entity) {
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

  String imports() {
    var groupedImports = new TreeMap<String, String>();

    imports.stream()
        .collect(Collectors.groupingBy(i -> i.from()))
        .forEach(
            (from, sameFrom) -> {
              var type = sameFrom.stream().anyMatch(i -> i.isType()) ? "type " : "";
              var defaultImport = sameFrom.stream().filter(i -> i.isDefault()).findFirst();
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
                          defaultImport.map(i -> i.alias()).orElse(""),
                          namedImports.isEmpty()
                              ? ""
                              : namedImports.stream().collect(Collectors.joining(", ", "{ ", " }")))
                      .filter(s -> !s.isBlank())
                      .collect(Collectors.joining(", "));

              var relativePath = NameResolver.resolve(from, mainClass.getPackageName());

              groupedImports.put(
                  relativePath,
                  """
                                            import %s%s from '%s';
                                            """
                      .formatted(
                          type,
                          imported,
                          relativePath.startsWith("@") ? relativePath : relativePath + ".js"));
            });

    var lines = String.join("", groupedImports.values());
    return lines.isEmpty() ? lines : lines + '\n';
  }

  String methodImplementations() {
    return String.join("\n\n", methodImplementations);
  }

  String methods() {
    return String.join("\n", methods);
  }

  String properties() {
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
  static final Pattern SUFFIX_REGEX = Pattern.compile("^(.*?)(\\d+)?$");

  static String incrementSuffix(String str) {

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
    return """
                async function %s%s(%s): Promise<%s> {
                    return %s.call('%s', '%s', {%s}, %s);
                }"""
        .formatted(
            method.getName(),
            generateTypeParams(method.getAnnotated().getTypeParameters()),
            generateParamList(method),
            generateType(
                new FullType(method.getType(), method.getAnnotated().getAnnotatedReturnType())),
            clientVariableName,
            className,
            method.getName(),
            generateParamNameObject(method),
            chooseInitParamName(method));
  }

  String generateTypeParams(TypeVariable<?>[] typeParameters) {
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
      params.add(javaParam.getName() + ": " + generateType(new FullType(param.getType(), generic)));
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
    return entity.properties().stream().map(this::generateProperty).toList();
  }

  private String generateProperty(BeanPropertyDefinition property) {
    var getterType =
        Optional.ofNullable(property.getGetter())
            .map(
                getter ->
                    new FullType(getter.getType(), getter.getAnnotated().getAnnotatedReturnType()));
    var setterType =
        Optional.ofNullable(property.getSetter())
            .map(
                setter ->
                    new FullType(
                        setter.getParameterType(0),
                        setter.getAnnotated().getAnnotatedParameterTypes()[0]));
    var fieldType =
        Optional.ofNullable(property.getField())
            .map(field -> new FullType(field.getType(), field.getAnnotated().getAnnotatedType()));
    var propertyType =
        new MultipleType(
            Stream.of(getterType, setterType, fieldType).flatMap(Optional::stream).toList());

    return """
                \s   %s: %s;"""
        .formatted(property.getName(), generateType(propertyType));
  }

  private String generateType(FullType type) {
    if (type.isOptional()) {
      return generateType(type.getBoundType());
    }
    if (type.isCollectionLikeType()) {
      return "Array<" + generateType(type.getContentType()) + '>';
    }

    if (type.isArrayType()) {
      return "Array<" + generateType(type.getArrayType()) + '>';
    }

    if (type.isMapLikeType()) {
      var mapTypes = type.getMapTypes();
      return "Map<" + generateType(mapTypes[0]) + ", " + generateType(mapTypes[1]) + '>';
    }

    var rawType = mapType(type.rawTypeName());
    var typeVariable = type.typeVariableName();
    var parameterizedTypes =
        type.parameterizedTypes()
            .map(s -> s.map(this::generateType).collect(Collectors.joining(", ", "<", ">")))
            .map(params -> rawType + params);
    var result = typeVariable.orElse(parameterizedTypes.orElse(rawType));

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
          case "java.lang.String" -> "string";
          case "java.lang.Object" -> "unknown";
          case "int",
              "long",
              "float",
              "double",
              "java.lang.Integer",
              "java.lang.Long",
              "java.lang.Float",
              "java.lang.Double" -> "number";
          default -> typeName;
        };

    if (type.contains(".")) {
      type = addImport(type.replaceAll(".*[.$]", ""), type, true, true);
    }

    return type;
  }
}