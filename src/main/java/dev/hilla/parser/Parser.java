package dev.hilla.parser;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.type.ArrayType;
import dev.hilla.parser.annotations.Endpoint;
import dev.hilla.parser.model.EntityClass;
import dev.hilla.parser.model.MethodClass;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Parser {
    private final ObjectMapper mapper;

    @Autowired
    public Parser(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public ParserResult parseEndpoints(List<Class<?>> endpointClasses) {
        var endpoints = endpointClasses.stream().map(endpoint -> {
            var methods = Arrays.stream(endpoint.getMethods())
                    .filter(method -> Modifier.isPublic(method.getModifiers()))
                    .filter(method -> method.getDeclaringClass().isAnnotationPresent(Endpoint.class))
                    .toList();
            return new MethodClass(endpoint.getName(), methods);
        }).toList();

        var entities = new HashMap<Class<?>, EntityClass>();

        endpointClasses.stream()
                .map(ec -> mapper.getSerializationConfig().introspect(mapper.getTypeFactory().constructType(ec)))
                .flatMap(jt ->
                        Arrays.stream(jt.getBeanClass().getMethods())
                                .filter(method -> Modifier.isPublic(method.getModifiers()))
                                .map(method -> jt.findMethod(method.getName(), method.getParameterTypes())))
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
        if (cls.getName().startsWith("java.")) {
            return;
        }

        var entity = new EntityClass(cls.getName());
        entities.put(cls, entity);

        var bean = mapper.getSerializationConfig().introspect(mapper.getTypeFactory().constructType(cls));
        bean.findProperties().stream().map(BeanPropertyDefinition::getPrimaryType).forEach(c -> addType(entities, c));
    }

    public static record ParserResult(List<MethodClass> endpoints, List<EntityClass> entities) {
    }
}
