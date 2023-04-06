package dev.hilla.parser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import java.util.List;

/**
 * Scan result
 *
 * @param endpoints the endpoints
 * @param entities the entities
 */
public record ScanResult(List<EndpointClass> endpoints, List<EntityClass> entities) {
  /**
   * An endpoint, as part of scan result
   *
   * @param type the endpoint class
   * @param methods exposed methods
   */
  public record EndpointClass(BeanDescription type, List<AnnotatedMethod> methods)
      implements ParserResult {
    @Override
    public String getName() {
      return type.getBeanClass().getSimpleName();
    }
  }

  /**
   * An entity, as part of scan result
   *
   * @param type entity type
   * @param properties entity properties
   */
  public record EntityClass(Class<?> type, List<BeanPropertyDefinition> properties)
      implements ParserResult {
    @Override
    public String getName() {
      return type.getSimpleName();
    }
  }

  public interface ParserResult {
    String getName();
  }
}
