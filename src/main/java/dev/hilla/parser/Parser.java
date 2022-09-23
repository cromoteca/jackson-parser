package dev.hilla.parser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;
import dev.hilla.parser.annotations.Endpoint;
import dev.hilla.parser.annotations.EndpointExposed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static dev.hilla.parser.ScanResult.*;

@Component
public class Parser {
    private final ObjectMapper mapper;

    @Autowired
    public Parser(MappingJackson2HttpMessageConverter springMvcJacksonConverter) {
        mapper = springMvcJacksonConverter.getObjectMapper();
    }

    public ScanResult parseEndpoints(List<Class<?>> endpointClasses) {
        // Collect exposed methods for all provided endpoints
        var endpoints = endpointClasses.stream().map(endpoint -> {
            var methods = Arrays.stream(endpoint.getMethods())
                    // only public methods
                    .filter(method -> Modifier.isPublic(method.getModifiers()))
                    // only methods in annotated classes
                    .filter(method -> method.getDeclaringClass().isAnnotationPresent(Endpoint.class)
                            || method.getDeclaringClass().isAnnotationPresent(EndpointExposed.class))
                    .toList();
            return new EndpointClass(endpoint, methods);
        }).toList();

        // Will contain all entities found while parsing
        var entities = new ConcurrentHashMap<Class<?>, EntityClass>();

        endpoints.stream()
                .flatMap(e -> {
                    // Although technically not necessary, let's use a Jackson BeanDescription to get information about
                    // endpoint methods, so that the code used to parse them will be the same as for entity accessors
                    var bean = bean(e.type());
                    return e.methods().stream()
                            // for each endpoint method, find its corresponding AnnotatedMethod
                            .map(method -> bean.findMethod(method.getName(), method.getParameterTypes()));
                })
                .distinct()
                // Methods are "flattened" to their types
                .flatMap(am -> {
                    // It happens for certain methods, maybe we shouldn't use Jackson directly on methods
                    if (am == null) {
                        return Stream.of();
                    }

                    // Rather that concatenating streams, it is better to build one by adding return type and
                    // parameter types one by one
                    var types = Stream.<JavaType>builder();
                    types.add(am.getType());

                    for (int i = 0; i < am.getParameterCount(); i++) {
                        types.add(am.getParameterType(i));
                    }

                    return types.build();
                })
                .distinct()
                // Parallelize type parsing
                .parallel()
                .forEach(jt -> addType(entities, jt));

        return new ScanResult(endpoints, entities.values().stream().toList());
    }

    private void addType(Map<Class<?>, EntityClass> entities, JavaType type) {
        if (type instanceof ArrayType arrayType) {
            // For arrays, let's add the item type instead
            addType(entities, arrayType.getContentType());
        } else {
            addClass(entities, type.getRawClass());
            // Let's deal with generics (recursion will work here)
            IntStream.range(0, type.containedTypeCount()).forEach(i -> addType(entities, type.containedType(i)));
        }
    }

    private void addClass(Map<Class<?>, EntityClass> entities, Class<?> cls) {
        // Skip already added classes
        if (entities.containsKey(cls)) {
            return;
        }
        // Skip primitive types
        if (cls.isPrimitive()) {
            return;
        }

        // Get the Jackson BeanDescription for this class
        var bean = bean(cls);

        // If the BeanDescription class does not correspond, it has been remapped using a Jackson converter
        if (!bean.getBeanClass().equals(cls)) {
            // Call `addClass` again with the converted type
            addClass(entities, bean.getBeanClass());
            // Stop treating this class, it has been replaced
            return;
        }

        // Skip Java types (should add other packages here)
        // Do this after conversion check in case a Java type has been remapped
        if (cls.getName().startsWith("java.")) {
            return;
        }

        // Here we have to do two things: finally add the class to the entities collection, but also add dependencies.
        // We must avoid recursion. That's why a stream is built to be processed *after* the collection insertions
        var builder = Stream.<JavaType>builder();

        // Call to `findProperties` is expensive: it took 885 ms for 190 hits
        entities.put(cls, new EntityClass(cls.getName(), bean.findProperties().stream()
                .map(pd -> {
                    // Call to `getPrimaryType` is expensive: it took 350 ms for 133 hits
                    builder.add(pd.getPrimaryType());
                    return pd.getAccessor().getMember();
                }).toList()));

        // Dependencies are added in parallel
        builder.build().distinct().parallel().forEach(jt -> addType(entities, jt));
    }

    /**
     * Creates a `BeanDescription` for a class, taking converters into account.
     *
     * @param cls the class
     * @return the bean description
     */
    private BeanDescription bean(Class<?> cls) {
        var bean = mapper.getSerializationConfig().introspect(mapper.getTypeFactory().constructType(cls));

        var converter = bean.findSerializationConverter();

        if (converter != null) {
            var javaType = converter.getOutputType(mapper.getTypeFactory());
            bean = mapper.getSerializationConfig().introspect(javaType);
        }

        return bean;
    }
}
