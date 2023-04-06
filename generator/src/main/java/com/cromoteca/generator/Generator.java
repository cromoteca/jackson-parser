package com.cromoteca.generator;

import com.cromoteca.ScanResult;
import java.util.Optional;

public class Generator {
  public String generateEndpoint(ScanResult.EndpointClass endpoint) {
    var worker = new Worker(endpoint);

    return """
      %s%s

      const %s = {
      %s
      };

      export default %s;
      """
        .formatted(
            worker.imports(),
            worker.methodImplementations(),
            endpoint.getName(),
            worker.methods(),
            endpoint.getName());
  }

  public String generateEntity(ScanResult.EntityClass entity) {
    var worker = new Worker(entity);

    return """
      %sinterface %s%s {
      %s
      }

      export default %s;
      """
        .formatted(
            worker.imports(),
            entity.getName(),
            worker.generateTypeParams(entity.type().getTypeParameters()),
            worker.properties(),
            entity.getName());
  }

  public static <T> Optional<T> castIfPossible(Object object, Class<T> cls) {
    if (object instanceof Optional<?> optional) {
      return optional.map(o -> cls.isInstance(o) ? cls.cast(o) : null);
    }
    return cls.isInstance(object) ? Optional.of(cls.cast(object)) : Optional.empty();
  }
}
