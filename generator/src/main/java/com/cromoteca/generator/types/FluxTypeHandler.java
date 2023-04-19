package com.cromoteca.generator.types;

import dev.hilla.EndpointSubscription;
import java.util.Set;
import reactor.core.publisher.Flux;

public class FluxTypeHandler extends DefaultTypeHandler {

  public static final Set<Class<?>> SUPPORTED = Set.of(Flux.class, EndpointSubscription.class);

  public boolean isSupported(Class<?> cls) {
    return SUPPORTED.contains(cls);
  }

  @Override
  public EndpointMethodType endpointMethodType() {
    return EndpointMethodType.SUBSCRIBE;
  }

  @Override
  public boolean generateEntityProperties() {
    return false;
  }

  @Override
  public boolean canHaveGenerics() {
    return false;
  }
}
