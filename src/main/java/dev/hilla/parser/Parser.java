package dev.hilla.parser;

import static dev.hilla.parser.ScanResult.*;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.hilla.parser.annotations.Endpoint;
import dev.hilla.parser.annotations.EndpointExposed;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

@Component
public class Parser {
  private final ObjectMapper mapper;

  @Autowired
  public Parser(MappingJackson2HttpMessageConverter springMvcJacksonConverter) {
    mapper = springMvcJacksonConverter.getObjectMapper();
  }

  public ScanResult parseEndpoints(List<Class<?>> endpointClasses) {
    // Collect exposed methods for all provided endpoints.
    var endpoints =
        endpointClasses.stream()
            .map(
                endpoint -> {
                  var bean = bean(mapper.getTypeFactory().constructType(endpoint));
                  var methods =
                      Arrays.stream(endpoint.getMethods())
                          // Only public methods.
                          .filter(method -> Modifier.isPublic(method.getModifiers()))
                          // Only methods in annotated classes.
                          .filter(
                              method ->
                                  method.getDeclaringClass().isAnnotationPresent(Endpoint.class)
                                      || method
                                          .getDeclaringClass()
                                          .isAnnotationPresent(EndpointExposed.class))
                          .map(
                              method ->
                                  bean.findMethod(method.getName(), method.getParameterTypes()))
                          .toList();
                  return new EndpointClass(bean, methods);
                })
            .toList();

    // Will contain all entities found while parsing.
    var entities = new ConcurrentHashMap<JavaType, EntityClass>();

    endpoints.stream()
        .flatMap(e -> e.methods().stream())
        .distinct()
        // Methods are "flattened" to their types.
        .flatMap(
            am -> {
              // It happens for certain methods, maybe we shouldn't use Jackson directly on methods.
              if (am == null) {
                return Stream.of();
              }

              // Rather that concatenating streams, it is better to build one by adding return type
              // and parameter types one by one.
              var types = Stream.<JavaType>builder();
              types.add(am.getType());

              for (int i = 0; i < am.getParameterCount(); i++) {
                types.add(am.getParameterType(i));
              }

              return types.build();
            })
        .distinct()
        .parallel()
        .forEach(jt -> addType(entities, jt));

    return new ScanResult(endpoints, entities.values().stream().toList());
  }

  private void addType(Map<JavaType, EntityClass> entities, JavaType type) {
    if (type.isArrayType()) {
      // For arrays, let's add the item type instead.
      addType(entities, type.getContentType());
    } else {
      addClass(entities, type);
      // Let's deal with generics (recursion will work here).
      IntStream.range(0, type.containedTypeCount())
          .forEach(i -> addType(entities, type.containedType(i)));
    }
  }

  private void addClass(Map<JavaType, EntityClass> entities, JavaType cls) {
    // Skip already added classes.
    if (entities.containsKey(cls)) {
      return;
    }
    // Skip primitive types.
    if (cls.isPrimitive()) {
      return;
    }

    // Get the Jackson BeanDescription for this class.
    var bean = bean(cls);

    // If the BeanDescription class does not correspond, it has been remapped using a Jackson
    // converter.
    if (!bean.getType().equals(cls)) {
      // Call `addClass` again with the converted type.
      addClass(entities, bean.getType());
      // Stop treating this class, it has been replaced.
      return;
    }

    // Skip Java types (should add other packages here).
    // Do this after conversion check in case a Java type has been remapped.
    if (cls.getRawClass().getName().startsWith("java.")) {
      return;
    }

    // Call to `findProperties` is expensive: it took 885 ms for 190 hits.
    var properties = bean.findProperties();
    entities.put(cls, new EntityClass(cls, properties));
    properties.stream().parallel().forEach(p -> addType(entities, p.getPrimaryType()));
  }

  /**
   * Creates a `BeanDescription` for a class, taking converters into account.
   *
   * @param cls the class
   * @return the bean description
   */
  private BeanDescription bean(JavaType cls) {
    var bean = mapper.getSerializationConfig().introspect(cls);

    var converter = bean.findSerializationConverter();

    if (converter != null) {
      var javaType = converter.getOutputType(mapper.getTypeFactory());
      bean = mapper.getSerializationConfig().introspect(javaType);
    }

    return bean;
  }
}
