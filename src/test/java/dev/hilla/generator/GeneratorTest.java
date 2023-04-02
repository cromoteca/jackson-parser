package dev.hilla.generator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.hilla.parser.Parser;
import dev.hilla.parser.annotations.Endpoint;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class GeneratorTest {
  private final Parser parser;
  private final ObjectMapper mapper = new ObjectMapper();

  protected GeneratorTest() {
    var messageConverter = mock(MappingJackson2HttpMessageConverter.class);
    doReturn(mapper).when(messageConverter).getObjectMapper();
    parser = new Parser(messageConverter);
  }

  protected void testEndpoint(Class<?> endpoint) throws IOException {
    var name = endpoint.getSimpleName();
    var scanResult = parser.parseEndpoints(List.of(endpoint));
    var expected = new String(getClass().getResourceAsStream(name + ".ts").readAllBytes());
    var actual =
        new Generator(mapper.getTypeFactory()).generateEndpoint(scanResult.endpoints().get(0));
    assertEquals(expected, actual, name);
  }

  protected static Stream<Class<?>> findEndpoints(String packageName) {
    var scanner = new ClassPathScanningCandidateComponentProvider(false);
    scanner.addIncludeFilter(new AnnotationTypeFilter(Endpoint.class));

    return scanner.findCandidateComponents(packageName).stream()
        .map(GeneratorTest::getClassFromBeanDefinition);
  }

  protected static Class<?> getClassFromBeanDefinition(BeanDefinition bd) {
    try {
      return Class.forName(bd.getBeanClassName());
    } catch (ClassNotFoundException ex) {
      throw new RuntimeException(ex);
    }
  }
}
