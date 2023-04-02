package dev.hilla.generator;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import dev.hilla.parser.ScanResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Generator {

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

  private static class Worker {

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
              method -> {
                Arrays.stream(method.getAnnotated().getParameters())
                    .forEach(p -> keywords.add(p.getName()));
              });
      initTypeName = addImport("EndpointRequestInit", "@hilla/frontend", false);
      clientVariableName = addImport("client", "/connect-client.default", true);
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
                    import %s from '%s';"""
                        .formatted(
                            imported,
                            relativePath.startsWith("@") ? relativePath : relativePath + ".js"));
              });

      return groupedImports.values().stream().collect(Collectors.joining("\n"));
    }

    String methodImplementations() {
      return methodImplementations.stream().collect(Collectors.joining("\n\n"));
    }

    String methodList() {
      return methodList.stream().collect(Collectors.joining("\n"));
    }

    private String addImport(String variable, String from, boolean isDefault) {
      var existing =
          imports.stream()
              .filter(
                  i ->
                      i.variable.equals(variable)
                          && i.from.equals(from)
                          && i.isDefault == isDefault)
              .findFirst();

      return existing.orElseGet(
              () -> {
                var alias = variable;

                while (keywords.contains(alias)) {
                  alias = incrementSuffix(alias);
                }

                var newImport = new Import(variable, from, isDefault, alias);
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
        const %s = async (%s): Promise<%s> => {
            return %s.call('%s', '%s', {%s}, %s);
        };"""
          .formatted(
              method.getName(),
              generateParamList(method),
              generateType(method.getType()),
              clientVariableName,
              className,
              method.getName(),
              generateParamNameList(method),
              chooseInitParamName(method));
    }

    private String generateParamList(AnnotatedMethod method) {
      var params = new ArrayList<String>();

      for (var i = 0; i < method.getParameterCount(); i++) {
        var param = method.getParameter(i);
        var javaParam = method.getAnnotated().getParameters()[i];
        params.add(javaParam.getName() + ": " + generateType(param.getType()));
      }

      params.add(chooseInitParamName(method) + "?: " + initTypeName);

      return String.join(", ", params);
    }

    private String generateParamNameList(AnnotatedMethod method) {
      var params =
          Arrays.stream(method.getAnnotated().getParameters())
              .map(param -> param.getName())
              .collect(Collectors.joining(", "));
      return params.isEmpty() ? "" : ' ' + params + ' ';
    }

    private String chooseInitParamName(AnnotatedMethod method) {
      var initParam = "init";
      var names =
          Arrays.stream(method.getAnnotated().getParameters())
              .map(param -> param.getName())
              .collect(Collectors.toList());

      while (names.contains(initParam)) {
        initParam = '_' + initParam;
      }

      return initParam;
    }

    private String generateType(JavaType type) {
      if (type.isCollectionLikeType()) {
        return generateType(type.getContentType()) + "[]";
      }

      if (type.isArrayType()) {
        return generateType(type.getContentType()) + "[]";
      }

      if (type.isMapLikeType()) {
        return "Map<"
            + generateType(type.getKeyType())
            + ", "
            + generateType(type.getContentType())
            + '>';
      }

      return mapType(type.getRawClass().getName());
    }

    private String mapType(String typeName) {
      var type =
          switch (typeName) {
            case "java.lang.String" -> "string";
            case "int", "long", "float", "double" -> "number";
            default -> typeName;
          };

      if (type.contains(".")) {
        type = addImport(type.replaceAll(".*[\\.\\$]", ""), type, false);
      }

      return type;
    }
  }

  private record Import(String variable, String from, boolean isDefault, String alias) {}
}
