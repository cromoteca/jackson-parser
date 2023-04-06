package com.cromoteca.generator;

import com.cromoteca.ScanResult;

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
}
