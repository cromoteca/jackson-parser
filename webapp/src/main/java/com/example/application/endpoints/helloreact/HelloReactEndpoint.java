package com.example.application.endpoints.helloreact;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.Endpoint;
import com.vaadin.hilla.EndpointSubscription;
import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import reactor.core.publisher.Flux;

@Endpoint
@AnonymousAllowed
public class HelloReactEndpoint {

  public String sayHello(String name) {
    if (name.isEmpty()) {
      return "Hello stranger";
    } else {
      return "Hello " + name;
    }
  }

  public Drawer<Date> getDrawer() {
    return DRAWER;
  }

  public boolean verifyDrawer(Drawer<Date> drawer) {
    System.out.println(drawer);
    return DRAWER.equals(drawer);
  }

  public Flux<String> getClock() {
    return Flux.interval(Duration.ofSeconds(1))
        .onBackpressureDrop()
        .map(_interval -> new Date().toString());
  }

  public EndpointSubscription<String> getClockCancellable() {
    return EndpointSubscription.of(
        getClock(),
        () -> {
          System.out.println("Subscription has been cancelled");
        });
  }

  public record Drawer<T>(
      Date date, UUID uuid, Map<Integer, Double> map, BigInteger bigInteger, T other) {}

  public static final Drawer<Date> DRAWER =
      new Drawer<>(
          new Date(),
          UUID.randomUUID(),
          Map.of(0, 0.1, 1, 2.0, 3, 4.0),
          new BigInteger("1234567890123456789012345678901234567890"),
          Date.from(Instant.now().plusSeconds(1000)));
}
