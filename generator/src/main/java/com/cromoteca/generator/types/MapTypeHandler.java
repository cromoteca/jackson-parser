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
package com.cromoteca.generator.types;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.util.Map;

public class MapTypeHandler extends DefaultTypeHandler {

  @Override
  public boolean isSupported(Class<?> cls) {
    return Map.class.isAssignableFrom(cls);
  }

  @Override
  public String parameterType(Class<?> type) {
    return "Map";
  }

  @Override
  public JsonSerializer<?> jsonSerializer() {
    return new CustomMapSerializer();
  }

  public static class CustomMapSerializer extends StdSerializer<Map> {
    public CustomMapSerializer() {
      this(null);
    }

    public CustomMapSerializer(Class<Map> t) {
      super(t);
    }

    @Override
    public void serialize(Map value, JsonGenerator gen, SerializerProvider provider)
        throws IOException {
      gen.writeStartObject();
      gen.writeStringField(HILLA_TYPE, "Map");

      gen.writeFieldName("entries");
      gen.writeStartArray();
      for (var key : value.keySet()) {
        gen.writeStartObject();
        gen.writeObjectField("key", key);
        gen.writeObjectField("value", value.get(key));
        gen.writeEndObject();
      }
      gen.writeEndArray();

      gen.writeEndObject();
    }

    @Override
    public Class<Map> handledType() {
      return Map.class;
    }
  }
}
