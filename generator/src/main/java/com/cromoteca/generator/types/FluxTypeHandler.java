package com.cromoteca.generator.types;

import dev.hilla.EndpointSubscription;
import java.util.Set;
import reactor.core.publisher.Flux;

public class FluxTypeHandler extends TypeHandler {

  @Override
  public Set<Class<?>> supportedTypes() {
    return Set.of(Flux.class, EndpointSubscription.class);
  }

  @Override
  public String returnType(Class<?> type) {
    return "Subscription";
  }

  @Override
  public EndpointMethodType endpointMethodType() {
    return EndpointMethodType.SUBSCRIBE;
  }

  @Override
  public boolean generateEntity() {
    return false;
  }
}
