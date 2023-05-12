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

import com.fasterxml.jackson.databind.JsonSerializer;
import java.util.List;

public class DefaultTypeHandler {
  public static final String HILLA_TYPE = "__hilla_type";

  // Order matters in this list.
  public static final List<DefaultTypeHandler> ALL =
      List.of(
          new NumberTypeHandler(),
          new BooleanTypeHandler(),
          new StringTypeHandler(),
          new FluxTypeHandler(),
          new DateTypeHandler(),
          new BigIntTypeHandler(),
          new MapTypeHandler(),
          new UnknownTypeHandler(),
          new DefaultTypeHandler());

  /**
   * Returns true if this type handler supports the given class.
   *
   * @param cls the class to check
   * @return true if this type handler supports the given class
   */
  public boolean isSupported(Class<?> cls) {
    return true;
  }

  /**
   * Returns the type name when this class is used as a parameter.
   *
   * @param type the class of the parameter
   * @return the type name when this class is used as a parameter
   */
  public String parameterType(Class<?> type) {
    return type.getName();
  }

  /**
   * Returns the Hilla model name for this class.
   *
   * @param type the class of the model
   * @return the Hilla model name for this class
   */
  public String modelType(Class<?> type) {
    return type.getName() + "Model";
  }

  /**
   * Returns true if classes handled by this type handler can have generics.
   *
   * @return true if classes handled by this type handler can have generics
   */
  public boolean canHaveGenerics() {
    return true;
  }

  /**
   * Returns the type of call to be performed for methods returning this type.
   *
   * @return the type of call to be performed for methods returning this type
   */
  public EndpointMethodType endpointMethodType() {
    return EndpointMethodType.CALL;
  }

  /**
   * Can return false if entities of types handled by this type handler should be empty. This is
   * desirable for types that are not expected to be transferred.
   *
   * @return true if entities of types handled by this type handler should be empty
   */
  public boolean generateEntityProperties() {
    return true;
  }

  /**
   * Returns the empty value to be used in Hilla models for types handled by this type handler.
   *
   * @return the empty value to be used in Hilla models for types handled by this type handler
   */
  public String emptyValue() {
    return "undefined";
  }

  /** Possible endpoint call types. */
  public static enum EndpointMethodType {
    CALL,
    SUBSCRIBE
  }

  /**
   * Returns the JSON serializer to be used for this type handler, or null if the default serializer
   * should be used.
   *
   * @return the JSON serializer to be used for this type handler, or null if the default serializer
   *     should be used
   */
  public JsonSerializer<?> jsonSerializer() {
    return null;
  }
}
