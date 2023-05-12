package com.cromoteca.samples.spring;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class ExampleSpringConfiguration {
  private final SpringDependency springDependency;

  public ExampleSpringConfiguration() {
    springDependency = new SpringDependency();
  }

  @Bean
  public SpringDependency springDependency() {
    return springDependency;
  }

  @Bean
  @ConditionalOnMissingBean
  public SpringEndpoint springEndpoint() {
    return new SpringEndpoint(springDependency);
  }
}
