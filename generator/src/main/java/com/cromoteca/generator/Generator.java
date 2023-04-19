package com.cromoteca.generator;

import com.cromoteca.ScanResult;
import com.cromoteca.generator.types.BooleanTypeHandler;
import com.cromoteca.generator.types.DefaultTypeHandler;
import com.cromoteca.generator.types.FluxTypeHandler;
import com.cromoteca.generator.types.NumberTypeHandler;
import com.cromoteca.generator.types.StringTypeHandler;
import com.cromoteca.generator.types.UnknownTypeHandler;
import jakarta.validation.Valid;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.lang.NonNullApi;

public class Generator {
  private final ScanResult scan;
  private final Map<Class<?>, DefaultTypeHandler> typeHandlers =
      new HashMap<>() {
        // Order matters in this list.
        private final List<DefaultTypeHandler> _typeHandlers =
            List.of(
                new NumberTypeHandler(),
                new BooleanTypeHandler(),
                new StringTypeHandler(),
                new FluxTypeHandler(),
                new UnknownTypeHandler(),
                new DefaultTypeHandler());

        @Override
        public DefaultTypeHandler get(Object key) {
          if (key instanceof Class<?> cls) {
            return super.computeIfAbsent(
                cls,
                k ->
                    _typeHandlers.stream()
                        .filter(h -> h.isSupported(cls))
                        .findFirst()
                        .orElseThrow());
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
                  var worker = new MakerTools(endpoint);
                  var maker = new EndpointMaker(worker, endpoint);
                  return maker.generate();
                }));
  }

  public Map<String, String> generateEntities() {
    return scan.entities().stream()
        .collect(
            Collectors.toMap(
                entityClass -> entityClass.type().getName(),
                entity -> {
                  var worker = new MakerTools(entity);
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
                  var worker = new MakerTools(entity);
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
                  var worker = new MakerTools(entity);
                  var maker = new FormikValidationMaker(worker, entity, Locale.getDefault());
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
                  var worker = new MakerTools(entity);
                  var maker = new YupSchemaMaker(worker, entity);
                  return maker.generate();
                }));
  }

  class MakerTools {
    // regular expression to match the last numeric suffix
    private static final Pattern SUFFIX_REGEX = Pattern.compile("^(.*?)(\\d+)?$");

    private final Set<String> keywords = new HashSet<>();
    private final List<Import> imports = new ArrayList<>();
    private final String mainClass;
    private final boolean nonNullApi;

    MakerTools(ScanResult.EndpointClass endpoint) {
      mainClass = endpoint.type().getBeanClass().getPackageName();
      nonNullApi = insideNonNullApi(endpoint.type().getType().getRawClass());
      endpoint
          .methods()
          .forEach(
              method ->
                  Arrays.stream(method.getAnnotated().getParameters())
                      .forEach(p -> addKeyword(p.getName())));
    }

    MakerTools(ScanResult.EntityClass entity) {
      mainClass = entity.type().getName().replaceFirst("[.$][^.$]+$", "");
      nonNullApi = insideNonNullApi(entity.type());
      entity.properties().forEach(property -> addKeyword(property.getName()));
    }

    private static boolean insideNonNullApi(Class<?> cls) {
      var classLoader = cls.getClassLoader();

      return Stream.iterate(
              cls.getPackageName(), n -> n.contains("."), n -> n.substring(0, n.lastIndexOf('.')))
          .map(classLoader::getDefinedPackage)
          .filter(Objects::nonNull)
          .anyMatch(pkg -> pkg.isAnnotationPresent(NonNullApi.class));
    }

    String getImports() {
      var groupedImports = new TreeMap<String, String>();

      imports.stream()
          .collect(Collectors.groupingBy(Import::getFrom))
          .forEach(
              (from, sameFrom) -> {
                var defaultImport =
                    sameFrom.stream()
                        .filter(Import::isDefault)
                        .findFirst()
                        .map(Import::toString)
                        .orElse("");

                var otherImports =
                    sameFrom.stream()
                        .filter(i -> !i.isDefault())
                        .map(Import::toString)
                        .collect(Collectors.joining(", "));

                if (!otherImports.isEmpty()) {
                  otherImports = "{ " + otherImports + " }";
                }

                var relativePath = NameResolver.resolve(from, mainClass);

                groupedImports.put(
                    relativePath,
                    StringTemplate.from(
                        """
                        import ${imports} from '${path}';
                        """,
                        Map.of(
                            "imports",
                            Stream.of(defaultImport, otherImports)
                                .filter(s -> !s.isEmpty())
                                .collect(Collectors.joining(", ")),
                            "path",
                            relativePath.startsWith("@") ? relativePath : relativePath + ".js")));
              });

      var lines = String.join("", groupedImports.values());
      return lines.isEmpty() ? lines : lines + '\n';
    }

    String fromImport(String variable, String from, boolean isDefault, boolean isType) {
      var existing =
          imports.stream()
              .filter(
                  i ->
                      Objects.equals(i.getVariable(), variable)
                          && Objects.equals(i.getFrom(), from))
              .findFirst();

      Import result =
          existing.orElseGet(
              () -> {
                String alias = findAlias(variable);
                var newImport = new Import(variable, from, isDefault, isType, alias);
                imports.add(newImport);
                return newImport;
              });

      if (isDefault != result.isDefault()) {
        throw new IllegalStateException(
            variable + " is imported from " + from + " both as default and not-default");
      }

      if (!isType && result.isType()) {
        result.setType(false);
      }

      return result.getAlias();
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

    void addKeyword(String variable) {
      keywords.add(variable);
    }

    String generateTypeVariables(Collection<String> typeVars) {
      return typeVars.isEmpty()
          ? ""
          : typeVars.stream().collect(Collectors.joining(", ", "<", ">"));
    }

    DefaultTypeHandler handlerFor(Class<?> cls) {
      return typeHandlers.get(cls);
    }

    String generateType(FullType type, Set<String> usedTypeVariables) {
      String result;
      var converted = scan.convertedClasses().get(type.getRawClass());
      if (converted != null) {
        result = generateType(new FullType(converted), usedTypeVariables);
      } else if (type.isOptional()) {
        result = generateType(type.getBoundType(), usedTypeVariables);
      } else if (type.isCollectionLikeType()) {
        result = "Array<" + generateType(type.getContentType(), usedTypeVariables) + '>';
      } else if (type.isArrayType()) {
        result = "Array<" + generateType(type.getArrayType(), usedTypeVariables) + '>';
      } else if (type.isMapLikeType()) {
        var mapTypes = type.getMapTypes();
        result =
            "Map<"
                + generateType(mapTypes[0], usedTypeVariables)
                + ", "
                + generateType(mapTypes[1], usedTypeVariables)
                + '>';
      } else {
        result = clientType(type.getRawClass());
        var typeVariable = type.typeVariableName();

        if (typeVariable != null) {
          usedTypeVariables.add(result = typeVariable);
        } else if (handlerFor(type.getRawClass()).canHaveGenerics()) {
          var parameterizedTypes =
              type.parameterizedTypes()
                  .map(
                      s ->
                          s.map(t -> generateType(t, usedTypeVariables))
                              .collect(Collectors.joining(", ", "<", ">")));
          result += parameterizedTypes.orElse("");
        }
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
