package dev.hilla.generator.basic;

import dev.hilla.parser.annotations.Endpoint;
import java.util.List;
import org.springframework.data.domain.Page;
import reactor.core.publisher.Flux;

@Endpoint
public class ExternalEntitiesEndpoint {
  public void page(Page<List<String>> argument) {}

  public void flux(Flux<List<String>> argument) {}

  public void inner(Inner<String> argument) {}

  public static class Inner<T> {
    public Page<T> page;
    public Flux<T> flux;
  }
}
