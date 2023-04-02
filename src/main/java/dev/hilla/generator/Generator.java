package dev.hilla.generator;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.type.TypeFactory;
import dev.hilla.parser.ScanResult;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Generator {
  private final TypeFactory typeFactory;

  public Generator(TypeFactory typeFactory) {
    this.typeFactory = typeFactory;
  }

  public String generateEndpoint(ScanResult.EndpointClass result) {
    var worker = new Worker(result);

    return """
      %s

      %s

      const %s = {
      %s
      };

      export default %s;
      """
        .formatted(
            worker.imports(),
            worker.methodImplementations(),
            result.getName(),
            worker.methodList(),
            result.getName());
  }

  private class Worker {

    private final Set<String> keywords = new HashSet<>();
    private final List<Import> imports = new ArrayList<>();
    private final List<String> methodImplementations;
    private final List<String> methodList;
    private final String initTypeName;
    private final String clientVariableName;
    private final String endpointPackageName;

    Worker(ScanResult.EndpointClass result) {
      result
          .methods()
          .forEach(
              method ->
                  Arrays.stream(method.getAnnotated().getParameters())
                      .forEach(p -> keywords.add(p.getName())));
      initTypeName = addImport("EndpointRequestInit", "@hilla/frontend", false, true);
      clientVariableName = addImport("client", "/connect-client.default", true, false);
      endpointPackageName = result.type().getBeanClass().getPackageName();
      methodImplementations = generateMethodImplementations(result);
      methodList = generateMethodList(result);
    }

    String imports() {
      var groupedImports = new TreeMap<String, String>();

      imports.stream()
          .collect(Collectors.groupingBy(i -> i.from))
          .forEach(
              (from, sameFrom) -> {
                var type = sameFrom.stream().anyMatch(i -> i.isType) ? "type " : "";
                var defaultImport = sameFrom.stream().filter(i -> i.isDefault).findFirst();
                var namedImports =
                    sameFrom.stream()
                        .filter(i -> !i.isDefault)
                        .map(
                            i ->
                                i.variable.equals(i.alias)
                                    ? i.alias
                                    : i.variable + " as " + i.alias)
                        .toList();

                var imported =
                    Stream.of(
                            defaultImport.map(i -> i.alias).orElse(""),
                            namedImports.isEmpty()
                                ? ""
                                : namedImports.stream()
                                    .collect(Collectors.joining(", ", "{ ", " }")))
                        .filter(s -> !s.isBlank())
                        .collect(Collectors.joining(", "));

                var relativePath = NameResolver.resolve(from, endpointPackageName);

                groupedImports.put(
                    relativePath,
                    """
                    import %s%s from '%s';"""
                        .formatted(
                            type,
                            imported,
                            relativePath.startsWith("@") ? relativePath : relativePath + ".js"));
              });

      return String.join("\n", groupedImports.values());
    }

    String methodImplementations() {
      return String.join("\n\n", methodImplementations);
    }

    String methodList() {
      return String.join("\n", methodList);
    }

    private String addImport(String variable, String from, boolean isDefault, boolean isType) {
      var existing =
          imports.stream()
              .filter(
                  i ->
                      i.variable.equals(variable)
                          && i.from.equals(from)
                          && i.isDefault == isDefault
                          && i.isType == isType)
              .findFirst();

      return existing.orElseGet(
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
          .alias;
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
        const %s: { %s(%s): Promise<%s> } = async (%s) => {
            return %s.call('%s', '%s', {%s}, %s);
        };"""
          .formatted(
              method.getName(),
              generateTypeParams(method.getAnnotated().getTypeParameters()),
              generateParamList(method),
              generateType(method.getType(), method.getAnnotated().getGenericReturnType()),
              generateParamNameList(method),
              clientVariableName,
              className,
              method.getName(),
              generateParamNameObject(method),
              chooseInitParamName(method));
    }

    private String generateTypeParams(TypeVariable<Method>[] typeParameters) {
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
        var generic = method.getAnnotated().getGenericParameterTypes()[i];
        var javaParam = method.getAnnotated().getParameters()[i];
        params.add(javaParam.getName() + ": " + generateType(param.getType(), generic));
      }

      params.add(chooseInitParamName(method) + "?: " + initTypeName);

      return String.join(", ", params);
    }

    private String generateParamNameList(AnnotatedMethod method) {
      return Stream.concat(
              Arrays.stream(method.getAnnotated().getParameters()).map(Parameter::getName),
              Stream.of(chooseInitParamName(method)))
          .collect(Collectors.joining(", "));
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

    private String generateType(JavaType type, Type generic) {
      if (type.isCollectionLikeType()) {
        var itemType =
            castIfPossible(generic, ParameterizedType.class)
                .map(p -> p.getActualTypeArguments()[0]);
        return generateType(type.getContentType(), itemType.orElse(null)) + "[]";
      }

      if (type.isArrayType()) {
        var itemType =
            castIfPossible(generic, GenericArrayType.class)
                .map(GenericArrayType::getGenericComponentType);
        return generateType(type.getContentType(), itemType.orElse(null)) + "[]";
      }

      if (type.isMapLikeType()) {
        var itemTypes =
            castIfPossible(generic, ParameterizedType.class)
                .map(ParameterizedType::getActualTypeArguments);
        return "Map<"
            + generateType(type.getKeyType(), itemTypes.map(i -> i[0]).orElse(null))
            + ", "
            + generateType(type.getContentType(), itemTypes.map(i -> i[1]).orElse(null))
            + '>';
      }

      var rawType =
          Optional.ofNullable(generic)
              .flatMap(g -> castIfPossible(g, TypeVariable.class))
              .map(TypeVariable::getName)
              .orElse(mapType(type.getRawClass().getName()));

      var genericType =
          Optional.ofNullable(generic)
              .flatMap(g -> castIfPossible(g, ParameterizedType.class))
              .map(ParameterizedType::getActualTypeArguments)
              .map(
                  types ->
                      Arrays.stream(types)
                          .map(t -> generateType(typeFactory.constructType(t), t))
                          .collect(Collectors.joining(", ", "<", ">")))
              .map(params -> rawType + params);

      return genericType.orElse(rawType);
    }

    private String mapType(String typeName) {
      var type =
          switch (typeName) {
            case "java.lang.String" -> "string";
            case "java.lang.Object" -> "unknown";
            case "int", "long", "float", "double" -> "number";
            default -> typeName;
          };

      if (type.contains(".")) {
        type = addImport(type.replaceAll(".*[.$]", ""), type, false, true);
      }

      return type;
    }
  }

  private static <T> Optional<T> castIfPossible(Object object, Class<T> cls) {
    if (object instanceof Optional<?> optional) {
      return optional.map(o -> cls.isInstance(o) ? cls.cast(o) : null);
    }
    return Optional.ofNullable(cls.isInstance(object) ? cls.cast(object) : null);
  }

  private record Import(
      String variable, String from, boolean isDefault, boolean isType, String alias) {}
}
