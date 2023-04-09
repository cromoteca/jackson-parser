package com.cromoteca.experiments;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanSerializer;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdDelegatingSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;
import java.util.stream.Stream;

public class PropertyDiscover {
  public static void main(String[] args) throws JsonProcessingException {
    var objectMapper = new ObjectMapper();
    // objectMapper.registerModule(new MyModule());
    var serializerProvider =
        ((DefaultSerializerProvider) objectMapper.getSerializerProvider())
            .createInstance(
                objectMapper.getSerializationConfig(), objectMapper.getSerializerFactory());

    Stream.of(MyClass.class, Distance.class, WillBeConverted.class)
        .forEach(
            cls -> {
              try {
                var serializer = serializerProvider.findTypedValueSerializer(cls, true, null);
                useSerializer(cls, serializer);
              } catch (JsonMappingException e) {
                throw new RuntimeException(e);
              }
            });
  }

  private static void useSerializer(Class<?> cls, JsonSerializer<?> serializer) {
    if (serializer instanceof BeanSerializer beanSerializer) {
      System.out.println("Class: " + cls.getSimpleName());
      beanSerializer
          .properties()
          .forEachRemaining(
              prop -> {
                System.out.format(
                    "  Property %s of type %s comes from %s and is%s required\n",
                    prop.getName(),
                    prop.getType().getRawClass().getSimpleName(),
                    prop.getMember().getDeclaringClass().getSimpleName(),
                    prop.isRequired() ? "" : " not");
              });
    } else if (serializer instanceof StdDelegatingSerializer delegatingSerializer) {
      useSerializer(cls, delegatingSerializer.getDelegatee());
    } else {
      System.out.println("Class: " + cls.getName() + " has no BeanSerializer");
    }
  }

  private static class MyModule extends SimpleModule {
    public MyModule() {
      addSerializer(
          WillBeConverted.class,
          new JsonSerializer<WillBeConverted>() {
            final ConverterUsedToSerialize converter = new ConverterUsedToSerialize();

            @Override
            public void serialize(
                WillBeConverted value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException {
              gen.writeObject(converter.convert(value));
            }
          });
      addDeserializer(
          WillBeConverted.class,
          new JsonDeserializer<WillBeConverted>() {
            final ConverterUsedToDeserialize converter = new ConverterUsedToDeserialize();

            @Override
            public WillBeConverted deserialize(JsonParser p, DeserializationContext ctxt)
                throws IOException, JacksonException {
              return converter.convert(p.readValueAs(HasBeenConverted.class));
            }
          });
    }
  }

  private record MyClass(String field1, int field2, WillBeConverted willBeConverted) {

    @Override
    @JsonProperty(value = "renamed1", required = true)
    public String field1() {
      return field1;
    }

    @Override
    @JsonIgnore
    public int field2() {
      return field2;
    }

    @Override
    @JsonProperty(required = true)
    public WillBeConverted willBeConverted() {
      return willBeConverted;
    }
  }

  @JsonFormat(shape = JsonFormat.Shape.OBJECT)
  public static enum Distance {
    KILOMETER("km", 1000),
    MILE("miles", 1609.34),
    METER("meters", 1),
    INCH("inches", 0.0254),
    CENTIMETER("cm", 0.01),
    MILLIMETER("mm", 0.001);

    private final String unit;
    private final double meters;

    private Distance(String unit, double meters) {
      this.unit = unit;
      this.meters = meters;
    }

    public String getUnit() {
      return unit;
    }

    public double getMeters() {
      return meters;
    }
  }

  public record WillBeConverted(MyClass willDisappear) {}

  public record HasBeenConverted(Distance willAppear) {}

  public static class ConverterUsedToSerialize
      implements Converter<WillBeConverted, HasBeenConverted> {
    @Override
    public HasBeenConverted convert(WillBeConverted value) {
      return new HasBeenConverted(Distance.METER);
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
      return new WillBeConverted(new MyClass("a", 1, new WillBeConverted(null)));
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
