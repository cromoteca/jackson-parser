package com.cromoteca.samples.spring;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.Endpoint;
import com.vaadin.hilla.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;

@Endpoint
@AnonymousAllowed
public class SpringEndpoint {
  private final SpringDependency dependency;

  @Autowired
  public SpringEndpoint(SpringDependency dependency) {
    this.dependency = dependency;
  }

  @Nonnull
  public String hello() {
    return dependency.hello();
  }
}
