package com.cromoteca.samples.endpoints;

import com.cromoteca.samples.Simple;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.Endpoint;
import java.util.List;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Flux;

@Endpoint
@AnonymousAllowed
public class ExternalEntitiesEndpoint {
  public void page(Page<List<String>> argument) {}

  public void flux(Flux<List<String>> argument) {}

  public void inner(Inner<String> argument) {}

  public void simple(Simple argument) {}

  public static class Inner<T> {
    public Page<T> page;
    public Flux<T> flux;
  }
}
