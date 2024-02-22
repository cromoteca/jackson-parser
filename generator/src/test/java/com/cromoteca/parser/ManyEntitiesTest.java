package com.cromoteca.parser;

import static org.mockito.Mockito.*;

import com.cromoteca.entities.Bear;
import com.cromoteca.entities.Group;
import com.cromoteca.entities.Notebook;
import com.cromoteca.entities.Strictness;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.hilla.Endpoint;
import com.vaadin.hilla.Nonnull;
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
