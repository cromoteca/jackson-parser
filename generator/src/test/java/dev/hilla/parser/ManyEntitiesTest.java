package dev.hilla.parser;

import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.hilla.Endpoint;
import dev.hilla.Nonnull;
import dev.hilla.parser.entities.Bear;
import dev.hilla.parser.entities.Group;
import dev.hilla.parser.entities.Notebook;
import dev.hilla.parser.entities.Strictness;
import java.util.List;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class ManyEntitiesTest {

  public ManyEntitiesTest() {
    var messageConverter = mock(MappingJackson2HttpMessageConverter.class);
    doReturn(new ObjectMapper()).when(messageConverter).getObjectMapper();
  }

  @Endpoint
  public static class LocalEndpoint {
    @Nonnull
    public List<Notebook> getNotebooks(@Nonnull Group<Strictness> strictnessGroup, Bear bear) {
      return List.of();
    }
  }
}
