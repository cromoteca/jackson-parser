package com.cromoteca.hilla;

import com.cromoteca.generator.Generator;
import com.cromoteca.parser.Parser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.server.frontend.EndpointGeneratorTaskFactory;
import com.vaadin.flow.server.frontend.Options;
import com.vaadin.flow.server.frontend.TaskGenerateEndpoint;
import com.vaadin.flow.server.frontend.TaskGenerateOpenAPI;
import dev.hilla.Endpoint;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public class EndpointGeneratorTaskFactoryImpl implements EndpointGeneratorTaskFactory {
  @Override
  public TaskGenerateOpenAPI createTaskGenerateOpenAPI(Options options) {
    return () -> {};
  }

  @Override
  public TaskGenerateEndpoint createTaskGenerateEndpoint(Options options) {
    return () -> {
      var scanner = new ClassPathScanningCandidateComponentProvider(false);
      scanner.addIncludeFilter(new AnnotationTypeFilter(Endpoint.class));
      var classes =
          Stream.of("com.example.application", "com.cromoteca.samples")
              .map(scanner::findCandidateComponents)
              .flatMap(Collection::stream)
              .map(EndpointGeneratorTaskFactoryImpl::getClassFromBeanDefinition)
              .toArray(Class<?>[]::new);
      var parser = new Parser(new ObjectMapper());
      var scanResult = parser.parseEndpoints(List.of(classes));
      var generator = new Generator(scanResult);
      Stream.of(generator.generateEndpoints(), generator.generateEntities())
          .flatMap(m -> m.entrySet().stream())
          .forEach(
              entry -> {
                try {
                  var path =
                      options
                          .getFrontendGeneratedFolder()
                          .toPath()
                          .resolve(entry.getKey().replaceAll("[.$]", "/") + ".ts");
                  Files.createDirectories(path.getParent());
                  Files.writeString(path, entry.getValue());
                } catch (IOException e) {
                  throw new RuntimeException(e);
                }
              });

      var userDir = Path.of(System.getProperty("user.dir"));

      if (Files.isDirectory(userDir.resolve("frontend"))) {
        try {
          Files.copy(
              userDir.resolve("../generator/src/test/resources/connect-client.default.ts"),
              userDir.resolve("frontend/generated/connect-client.default.ts"),
              StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    };
  }

  private static Class<?> getClassFromBeanDefinition(BeanDefinition bd) {
    try {
      return Class.forName(bd.getBeanClassName());
    } catch (ClassNotFoundException ex) {
      throw new RuntimeException(ex);
    }
  }
}
