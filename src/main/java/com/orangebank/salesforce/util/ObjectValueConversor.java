package com.orangebank.salesforce.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public final class ObjectValueConversor {

  private static ObjectMapper objectMapper = new ObjectMapper();

  /**
   * Parse LinkedMap to a List of type Objects.
   * @param value The value to convert.
   * @param type The type of the value to convert.
   * @return A list of Objects.
   */
  public static <T> List<T> convertValueToList(List<?> value, Class<T> type) {
    return objectMapper.convertValue(
      value,
      objectMapper.getTypeFactory().constructCollectionType(List.class, type)
    );
  }
  
  /**
   * Parse LinkedMap to a type Object.
   * @param value The value to convert.
   * @param type The type of the value to convert.
   * @return An Object.
   */
  public static <T> T convertValueToObject(Object value, Class<T> type) {
    return objectMapper.convertValue(
      value,
      type
    );
  }

}
