package com.example.application.endpoints.helloreact;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.Endpoint;
import com.vaadin.hilla.Nonnull;
import reactor.core.publisher.Flux;

@Endpoint
@AnonymousAllowed
public class SubtypesEndpoint {
  public Flux<@Nonnull AbstractEvent> subscribe() {
    return Flux.just(new AddEvent(), new UpdateEvent(), new DeleteEvent());
  }

  public @Nonnull AbstractEvent single() {
    return new AddEvent();
  }

  @JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS)
  public static class AbstractEvent {
    public String name;
  }

  public static class AddEvent extends AbstractEvent {
    public String item;
  }

  public static class UpdateEvent extends AbstractEvent {
    public String oldItem;
    public String newItem;
  }

  public static class DeleteEvent extends AbstractEvent {
    public String item;
    public boolean force;
  }
}
