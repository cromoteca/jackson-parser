package dev.hilla.parser;

import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.hilla.parser.annotations.Endpoint;
import dev.hilla.parser.annotations.Nonnull;
import dev.hilla.parser.entities.Bear;
import dev.hilla.parser.entities.Group;
import dev.hilla.parser.entities.Notebook;
import dev.hilla.parser.entities.Strictness;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class ManyEntitiesTest {

  private final Parser parser;

  public ManyEntitiesTest() {
    var messageConverter = mock(MappingJackson2HttpMessageConverter.class);
    doReturn(new ObjectMapper()).when(messageConverter).getObjectMapper();
    parser = new Parser(messageConverter);
  }

  public static void main(String[] args) {
    long millis = System.currentTimeMillis();
    new ManyEntitiesTest().shouldFindAllEntitiesInPackage();
    System.out.println((System.currentTimeMillis() - millis) / 1000.0);
  }

  @Test
  void shouldFindAllEntitiesInPackage() {
    var scanResult = parser.parseEndpoints(List.of(LocalEndpoint.class));
    SimpleConsoleOutput.describe(scanResult);
  }

  @Endpoint
  public static class LocalEndpoint {
    @Nonnull
    public List<Notebook> getNotebooks(@Nonnull Group<Strictness> strictnessGroup, Bear bear) {
      return List.of();
    }
  }
}
