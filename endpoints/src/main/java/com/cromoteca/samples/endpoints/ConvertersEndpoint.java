package com.cromoteca.samples.endpoints;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import dev.hilla.Endpoint;

@Endpoint
public class ConvertersEndpoint {
  public WillBeConverted getConverted() {
    return new WillBeConverted();
  }

  @JsonSerialize(converter = ConverterUsedToSerialize.class)
  @JsonDeserialize(converter = ConverterUsedToDeserialize.class)
  public record WillBeConverted() {}

  public record HasBeenConverted() {}

  public static class ConverterUsedToSerialize
      implements Converter<WillBeConverted, HasBeenConverted> {
    @Override
    public HasBeenConverted convert(WillBeConverted value) {
      return new HasBeenConverted();
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
      return typeFactory.constructType(WillBeConverted.class);
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
      return typeFactory.constructType(HasBeenConverted.class);
    }
  }

  public static class ConverterUsedToDeserialize
      implements Converter<HasBeenConverted, WillBeConverted> {
    @Override
    public WillBeConverted convert(HasBeenConverted value) {
      return new WillBeConverted();
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
      return typeFactory.constructType(HasBeenConverted.class);
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
      return typeFactory.constructType(WillBeConverted.class);
    }
  }
}
