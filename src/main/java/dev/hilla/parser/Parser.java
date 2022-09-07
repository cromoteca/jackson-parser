package dev.hilla.parser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;
import dev.hilla.parser.annotations.Endpoint;
import dev.hilla.parser.annotations.EndpointExposed;
import dev.hilla.parser.model.EntityClass;
import dev.hilla.parser.model.MethodClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class Parser {
    private final ObjectMapper mapper;

    @Autowired
    public Parser(MappingJackson2HttpMessageConverter springMvcJacksonConverter) {
        mapper = springMvcJacksonConverter.getObjectMapper();
    }

    public ParserResult parseEndpoints(List<Class<?>> endpointClasses) {
        var endpoints = endpointClasses.stream().map(endpoint -> {
            var methods = Arrays.stream(endpoint.getMethods())
                    .filter(method -> Modifier.isPublic(method.getModifiers()))
                    .filter(method -> method.getDeclaringClass().isAnnotationPresent(Endpoint.class)
                            || method.getDeclaringClass().isAnnotationPresent(EndpointExposed.class))
                    .toList();
            return new MethodClass(endpoint, methods);
        }).toList();

        var entities = new ConcurrentHashMap<Class<?>, EntityClass>();

        endpoints.stream()
                .flatMap(e -> {
                    var bean = bean(e.type());
                    return e.methods().stream()
                            .map(method -> bean.findMethod(method.getName(), method.getParameterTypes()));
                })
                .distinct()
                .flatMap(am -> {
                    if (am == null) {
                        return Stream.of();
                    }

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

        return new ParserResult(endpoints, entities.values().stream().toList());
    }

    private void addType(Map<Class<?>, EntityClass> entities, JavaType type) {
        if (type instanceof ArrayType arrayType) {
            addType(entities, arrayType.getContentType());
        } else {
            addClass(entities, type.getRawClass());
            IntStream.range(0, type.containedTypeCount()).forEach(i -> addType(entities, type.containedType(i)));
        }
    }

    private void addClass(Map<Class<?>, EntityClass> entities, Class<?> cls) {
        if (entities.containsKey(cls)) {
            return;
        }
        if (cls.isPrimitive()) {
            return;
        }

        var bean = bean(cls);

        if (!bean.getBeanClass().equals(cls)) {
            addClass(entities, bean.getBeanClass());
            return;
        }
        if (cls.getName().startsWith("java.")) {
            return;
        }

        var builder = Stream.<JavaType>builder();

        entities.put(cls, new EntityClass(cls.getName(), bean.findProperties().stream()
                .map(pd -> {
                    builder.add(pd.getPrimaryType());
                    return (Member) pd.getAccessor().getMember();
                }).toList()));

        builder.build().distinct().parallel().forEach(jt -> addType(entities, jt));
    }

    private BeanDescription bean(Class<?> cls) {
        var bean = mapper.getSerializationConfig().introspect(mapper.getTypeFactory().constructType(cls));

        var converter = bean.findSerializationConverter();

        if (converter != null) {
            var javaType = converter.getOutputType(mapper.getTypeFactory());
            bean = mapper.getSerializationConfig().introspect(javaType);
        }

        return bean;
    }

    public static record ParserResult(List<MethodClass> endpoints, List<EntityClass> entities) {
    }
}
