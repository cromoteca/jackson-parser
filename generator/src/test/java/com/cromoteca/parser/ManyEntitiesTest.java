package com.cromoteca.parser;

import static org.mockito.Mockito.*;

import com.cromoteca.parser.entities.Bear;
import com.cromoteca.parser.entities.Group;
import com.cromoteca.parser.entities.Notebook;
import com.cromoteca.parser.entities.Strictness;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.hilla.Endpoint;
import dev.hilla.Nonnull;
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
