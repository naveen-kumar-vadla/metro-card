package com.example.geektrust.util;

import java.util.Arrays;

public class CommonUtil {
  public static <T extends Enum<T>> boolean isValidEnum(Class<T> enumClass, String value) {
    return Arrays.stream(enumClass.getEnumConstants())
        .anyMatch(e -> e.name().equals(value));
  }
}
