package com.cromoteca.samples.endpoints;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.Endpoint;

@Endpoint
@AnonymousAllowed
public class JacksonEndpoint {
  public void dummy(Entity entity, User user, Product product) {}

  @JsonIgnoreProperties({"ignoredByJsonIgnoreProperties"})
  public static class Entity {
    @JsonIgnore private String ignoredByJsonIgnore;

    @JsonProperty("renamedByJsonProperty")
    private String toBeRenamedByJsonProperty;

    private String ignoredByJsonIgnoreProperties;

    public String getIgnoredByJsonIgnore() {
      return ignoredByJsonIgnore;
    }

    public void setIgnoredByJsonIgnore(String ignoredByJsonIgnore) {
      this.ignoredByJsonIgnore = ignoredByJsonIgnore;
    }

    public String getToBeRenamedByJsonProperty() {
      return toBeRenamedByJsonProperty;
    }

    public void setToBeRenamedByJsonProperty(String toBeRenamedByJsonProperty) {
      this.toBeRenamedByJsonProperty = toBeRenamedByJsonProperty;
    }

    public String getIgnoredByJsonIgnoreProperties() {
      return ignoredByJsonIgnoreProperties;
    }

    public void setIgnoredByJsonIgnoreProperties(String ignoredByJsonIgnoreProperties) {
      this.ignoredByJsonIgnoreProperties = ignoredByJsonIgnoreProperties;
    }
  }

  @JsonIgnoreProperties(
      value = {"password"},
      allowSetters = true)
  public static class User {
    public String name;
    public String password;
  }

  @JsonIgnoreProperties(
      value = {"id"},
      allowGetters = true)
  public class Product {
    public String id;
    public String name;
  }
}
