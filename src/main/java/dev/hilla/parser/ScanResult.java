package dev.hilla.parser;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Scan result
 *
 * @param endpoints the endpoints
 * @param entities  the entities
 */
public record ScanResult(List<EndpointClass> endpoints, List<EntityClass> entities) {
    /**
     * An endpoint, as part of scan result
     *
     * @param type    the endpoint class
     * @param methods exposed methods
     */
    public record EndpointClass(Class<?> type, List<Method> methods) {
    }

    /**
     * An entity, as part of scan result
     *
     * @param name              entity name
     * @param propertyAccessors getters or fields used to read properties
     */
    public record EntityClass(String name, List<? extends Member> propertyAccessors) {
    }
}
