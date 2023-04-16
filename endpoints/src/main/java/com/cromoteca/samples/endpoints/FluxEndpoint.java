package com.cromoteca.samples.endpoints;

import com.cromoteca.samples.annotations.Nullable;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.Endpoint;
import dev.hilla.EndpointSubscription;
import java.util.List;
import java.util.Map;
import reactor.core.publisher.Flux;

@Endpoint
@AnonymousAllowed
public class FluxEndpoint {
  public Flux<Map<String, List<@Nullable String>>> flux(List<@Nullable String> arg) {
    return Flux.empty();
  }

  public EndpointSubscription<String> subscription() {
    return EndpointSubscription.of(Flux.empty(), () -> {});
  }
}
