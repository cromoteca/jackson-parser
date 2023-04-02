package dev.hilla.generator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.hilla.generator.example.BasicEndpoint;
import dev.hilla.generator.example.CustomTypesEndpoint;
import dev.hilla.parser.Parser;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class BasicGeneratorTest {
  private final Parser parser;

  public BasicGeneratorTest() {
    var messageConverter = mock(MappingJackson2HttpMessageConverter.class);
    doReturn(new ObjectMapper()).when(messageConverter).getObjectMapper();
    parser = new Parser(messageConverter);
  }

  @ParameterizedTest
  @MethodSource
  public void basicGeneration(Class<?> endpoint) throws IOException {
    var name = endpoint.getSimpleName();
    var scanResult = parser.parseEndpoints(List.of(endpoint));
    var expected =
        new String(getClass().getResourceAsStream("example/" + name + ".ts").readAllBytes());
    var actual = new Generator().generateEndpoint(scanResult.endpoints().get(0));
    assertEquals(expected, actual, name);
  }

  private static Stream<Class<?>> basicGeneration() {
    return Stream.of(BasicEndpoint.class, CustomTypesEndpoint.class);
  }
}
