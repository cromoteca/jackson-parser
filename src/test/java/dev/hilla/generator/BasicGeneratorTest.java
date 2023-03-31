package dev.hilla.generator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.hilla.generator.example.BasicEndpoint;
import dev.hilla.parser.Generator;
import dev.hilla.parser.Parser;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class BasicGeneratorTest {
  private final Parser parser;

  public BasicGeneratorTest() {
    var messageConverter = mock(MappingJackson2HttpMessageConverter.class);
    doReturn(new ObjectMapper()).when(messageConverter).getObjectMapper();
    parser = new Parser(messageConverter);
  }

  @Test
  public void basicGeneration() throws IOException {
    var scanResult = parser.parseEndpoints(List.of(BasicEndpoint.class));
    // read the BasicEndpoint.ts resource as bytes and convert to string
    var expected =
        new String(getClass().getResourceAsStream("expected/BasicEndpoint.ts").readAllBytes());
    var actual = new Generator().generateEndpoint(scanResult.endpoints().get(0));
    assertEquals(expected, actual, "BasicEndpoint");
  }
}
