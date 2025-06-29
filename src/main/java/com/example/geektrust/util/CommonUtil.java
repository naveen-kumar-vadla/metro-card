package com.example.geektrust.util;

import java.util.Arrays;

public class CommonUtil {
  public static <T extends Enum<T>> boolean isInvalidEnum(Class<T> enumClass, String value) {
    return Arrays.stream(enumClass.getEnumConstants())
        .noneMatch(e -> e.name().equals(value));
  }
}
