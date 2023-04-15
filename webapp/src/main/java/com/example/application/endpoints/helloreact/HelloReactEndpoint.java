package com.example.application.endpoints.helloreact;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.Endpoint;
import dev.hilla.EndpointSubscription;
import dev.hilla.Nonnull;
import java.time.Duration;
import java.util.Date;
import reactor.core.publisher.Flux;

@Endpoint
@AnonymousAllowed
public class HelloReactEndpoint {

  @Nonnull
  public String sayHello(@Nonnull String name) {
    if (name.isEmpty()) {
      return "Hello stranger";
    } else {
      return "Hello " + name;
    }
  }

  public Flux<@Nonnull String> getClock() {
    return Flux.interval(Duration.ofSeconds(1))
        .onBackpressureDrop()
        .map(_interval -> new Date().toString());
  }

  public EndpointSubscription<@Nonnull String> getClockCancellable() {
    return EndpointSubscription.of(
        getClock(),
        () -> {
          System.out.println("Subscription has been cancelled");
        });
  }
}
