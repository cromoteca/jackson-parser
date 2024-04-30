/*
 * Copyright (C) 2023 Luciano Vernaschi (luciano at cromoteca.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.cromoteca.experiments;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
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
import com.fasterxml.jackson.databind.ser.std.JsonValueSerializer;
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

    Stream.of(MyClass.class, Distance.class, WillBeConverted.class, MyPrimitive.class)
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
      System.out.println("(Delegating serializer for class: " + cls.getSimpleName() + ")");
      useSerializer(cls, delegatingSerializer.getDelegatee());
    } else if (serializer instanceof JsonValueSerializer jsonValueSerializer) {
      System.out.println("Class: " + cls.getSimpleName());
      System.out.println("  will be serialized as: " + jsonValueSerializer.handledType().getName());
    } else if (serializer == null) {
      System.out.println("Class: " + cls.getName() + " has no BeanSerializer");
    } else {
      System.out.println(
          "Class: " + cls.getName() + " has serializer of type " + serializer.getClass().getName());
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

  private static class MyPrimitive {
    private final String value;

    @JsonCreator
    public MyPrimitive(String value) {
      this.value = value;
    }

    @JsonValue
    public String value() {
      return value;
    }
  }

  private record MyClass(
      @JsonProperty(value = "renamed1", required = true) String field1,
      @JsonIgnore int field2,
      @JsonProperty(required = true) WillBeConverted willBeConverted,
      MyPrimitive primitive) {}

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
      return new WillBeConverted(
          new MyClass("a", 1, new WillBeConverted(null), new MyPrimitive(null)));
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
