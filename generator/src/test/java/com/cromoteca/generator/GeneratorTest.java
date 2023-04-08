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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

public class GeneratorTest {
  private static final String HEADER =
      "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
  private final Parser parser;

  protected GeneratorTest() {
    var messageConverter = mock(MappingJackson2HttpMessageConverter.class);
    doReturn(new ObjectMapper()).when(messageConverter).getObjectMapper();
    parser = new Parser(messageConverter);
  }

  @ParameterizedTest
  @MethodSource
  public void testGeneration(Class<?> endpoint) throws IOException {
    generateAndCheck(List.of(endpoint));
  }

  @Test
  public void testMultipleGeneration() throws IOException {
    generateAndCheck(findEndpoints("com.cromoteca.samples").toList());
  }

  private void generateAndCheck(List<Class<?>> endpoints) {
    var scanResult = parser.parseEndpoints(endpoints);
    var generator = new Generator(scanResult);
    generator.generateEndpoints().forEach(this::checkResult);
    generator.generateEntities().forEach(this::checkResult);
  }

  private void checkResult(Class<?> cls, String actual) {
    var className = '/' + cls.getName().replaceAll("[.$]", "/");

    try (var typeScriptFile = getClass().getResourceAsStream(className + ".ts")) {
      if (typeScriptFile == null) {
        printHeader(className);
        System.out.println(actual);
        System.out.println(HEADER);
        System.out.println();
      } else {
        var expected = new String(typeScriptFile.readAllBytes());
        assertEquals(expected, actual, className);
      }
    } catch (IOException e) {
      fail(e);
    }
  }

  private static Stream<Class<?>> testGeneration() {
    return findEndpoints("com.cromoteca.samples");
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

  private void printHeader(String title) {
    System.out.print(HEADER.substring(0, 2));
    System.out.print(' ');
    System.out.print(title);
    System.out.print(".ts ");
    System.out.println(HEADER.substring(0, Math.max(0, HEADER.length() - title.length() - 7)));
  }
}
