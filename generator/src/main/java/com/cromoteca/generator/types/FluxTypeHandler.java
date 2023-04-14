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
  public String generateType(Class<?> type) {
    return "Subscription";
  }

  @Override
  public String generateModelType(Class<?> type) {
    return "SubscriptionModel";
  }

  @Override
  public MethodType methodType() {
    return MethodType.SUBSCRIBE;
  }
}
