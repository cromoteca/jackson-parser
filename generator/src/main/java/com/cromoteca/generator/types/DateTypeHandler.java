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
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTypeHandler extends DefaultTypeHandler {
  @Override
  public boolean isSupported(Class<?> cls) {
    return cls == Date.class;
  }

  @Override
  public String parameterType(Class<?> type) {
    return "Date";
  }

  @Override
  public boolean canHaveGenerics() {
    return false;
  }

  @Override
  public JsonSerializer<?> jsonSerializer() {
    return new CustomDateSerializer();
  }

  public static class CustomDateSerializer extends StdSerializer<Date> {
    private static final SimpleDateFormat DATE_FORMAT =
        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    public CustomDateSerializer() {
      this(null);
    }

    public CustomDateSerializer(Class<Date> t) {
      super(t);
    }

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider provider)
        throws IOException {
      gen.writeStartObject();
      gen.writeStringField(HILLA_TYPE, "Date");
      gen.writeStringField("value", DATE_FORMAT.format(value));
      gen.writeEndObject();
    }

    @Override
    public Class<Date> handledType() {
      return Date.class;
    }
  }
}
