package com.cromoteca.samples.endpoints;

import com.cromoteca.samples.endpoints.entities.MyOtherType;
import dev.hilla.Endpoint;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Endpoint
public class MixEndpoint {

  public void expose(@Nullable MyType arg) {}

  public record MyType(
      byte[][] byteArray2D,
      int primitive,
      String javaObject,
      MyOtherType customObject,
      List<String> collection,
      Map<String, String> stringMap,
      Map<Integer, Integer> intMap,
      Map<Object, Object> objectMap,
      MyEnum enumType,
      int[] primitiveArray,
      String[] javaObjectArray,
      MyOtherType[] customObjectArray,
      List<String>[] collectionArray,
      MyEnum[] enumTypeArray,
      Optional<String> optional,
      @Nullable String nullable,
      @Min(4) @Max(10) int minMax,
      @Email String email,
      @NotBlank @Email String notBlankEmail) {}

  public static enum MyEnum {
    A,
    B,
    C
  }
}
