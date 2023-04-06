package com.cromoteca.generator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.cromoteca.parser.Parser;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.hilla.Endpoint;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class GeneratorTest {
  private final Parser parser;

  protected GeneratorTest() {
    var messageConverter = mock(MappingJackson2HttpMessageConverter.class);
    doReturn(new ObjectMapper()).when(messageConverter).getObjectMapper();
    parser = new Parser(messageConverter);
  }

  protected void testEndpoint(Class<?> endpoint) throws IOException {
    var endpointName = endpoint.getSimpleName();
    var scanResult = parser.parseEndpoints(List.of(endpoint));

    try (var typeScriptFile = getClass().getResourceAsStream(endpointName + ".ts")) {
      var actual = new Generator().generateEndpoint(scanResult.endpoints().get(0));

      if (typeScriptFile == null) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(endpointName);
        System.out.println(actual);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      } else {
        var expected = new String(typeScriptFile.readAllBytes());
        assertEquals(expected, actual, endpointName);
      }
    }

    for (var entity : scanResult.entities()) {
      var entityName = '/' + entity.type().getName().replaceAll("[.$]", "/");
      var actual = new Generator().generateEntity(entity);

      try (var typeScriptFile = getClass().getResourceAsStream(entityName + ".ts")) {
        if (typeScriptFile == null) {
          System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
          System.out.println(entityName);
          System.out.println(actual);
          System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        } else {
          var expected = new String(typeScriptFile.readAllBytes());
          assertEquals(expected, actual, endpointName);
        }
      }
    }
  }

  protected static Stream<Class<?>> findEndpoints(String... packageNames) {
    var scanner = new ClassPathScanningCandidateComponentProvider(false);
    scanner.addIncludeFilter(new AnnotationTypeFilter(Endpoint.class));

    return Arrays.stream(packageNames)
        .map(scanner::findCandidateComponents)
        .flatMap(Collection::stream)
        .distinct()
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
