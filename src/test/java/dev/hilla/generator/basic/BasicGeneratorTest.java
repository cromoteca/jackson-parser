package dev.hilla.generator.basic;

import dev.hilla.generator.GeneratorTest;
import java.io.IOException;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class BasicGeneratorTest extends GeneratorTest {

  @ParameterizedTest
  @MethodSource
  public void testGeneration(Class<?> endpoint) throws IOException {
    testEndpoint(endpoint);
  }

  private static Stream<Class<?>> testGeneration() {
    return findEndpoints(BasicGeneratorTest.class.getPackageName());
  }
}
