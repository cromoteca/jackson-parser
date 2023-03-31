package dev.hilla.parser;

import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import java.util.stream.Collectors;

public class Generator {

  public String generateEndpoint(ScanResult.EndpointClass result) {
    return """
    declare type EndpointRequestInit = {};
    declare function _call(endpoint: string, method: string, params?: any, __init?: EndpointRequestInit): Promise<any>;
    declare const _client: { call: typeof _call };

    %s
    """
        .formatted(generateMethods(result));
  }

  private String generateMethods(ScanResult.EndpointClass result) {
    return result.methods().stream().map(this::generateMethod).collect(Collectors.joining("\n"));
  }

  private String generateMethod(AnnotatedMethod method) {
    return """
        %s
        """.formatted(method.getName());
  }
}
