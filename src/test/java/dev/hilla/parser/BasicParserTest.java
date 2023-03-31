package dev.hilla.parser;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import dev.hilla.parser.example.BasicEndpoint;
import dev.hilla.parser.example.BasicEntities;
import dev.hilla.parser.example.ShouldBeParsed;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class BasicParserTest {

  private final Parser parser;

  public BasicParserTest() {
    var messageConverter = mock(MappingJackson2HttpMessageConverter.class);
    doReturn(new ObjectMapper()).when(messageConverter).getObjectMapper();
    parser = new Parser(messageConverter);
  }

  @Test
  void basicParsing() {
    var scanResult = parser.parseEndpoints(List.of(BasicEndpoint.class));

    assertStreamEquals(
        Stream.of(BasicEndpoint.class.getName()),
        scanResult.endpoints().stream().map(e -> e.type().getBeanClass().getName()),
        "Same endpoints");

    var exampleEndpoint =
        scanResult.endpoints().stream()
            .filter(e -> e.type().getBeanClass().equals(BasicEndpoint.class))
            .findAny()
            .orElseThrow();

    assertStreamEquals(
        Arrays.stream(BasicEndpoint.class.getMethods())
            .filter(m -> m.isAnnotationPresent(ShouldBeParsed.class))
            .map(Method::getName),
        exampleEndpoint.methods().stream().map(AnnotatedMethod::getName),
        "Same methods in endpoint");

    assertStreamEquals(
        Arrays.stream(BasicEntities.class.getDeclaredClasses())
            .filter(c -> c.isAnnotationPresent(ShouldBeParsed.class))
            .map(Class::getName),
        scanResult.entities().stream().map(e -> e.type().getRawClass().getName()),
        "Same entities");
  }

  private void assertStreamEquals(
      Stream<? extends CharSequence> expected,
      Stream<? extends CharSequence> actual,
      String message) {
    assertEquals(toString(expected), toString(actual), message);
  }

  private static String toString(Stream<? extends CharSequence> result) {
    return result.sorted().collect(Collectors.joining("\n"));
  }
}
