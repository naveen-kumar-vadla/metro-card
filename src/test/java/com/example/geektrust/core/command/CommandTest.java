package com.example.geektrust.core.command;

import com.example.geektrust.exception.MetroCardException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public interface CommandTest {

  @ParameterizedTest
  @MethodSource("validCommands")
  default void shouldValidateCommand(Command command) {
    assertDoesNotThrow(command::validate);
  }

  @ParameterizedTest
  @MethodSource("invalidCommands")
  default void shouldThrowWhenValidationFails(Command command) {
    assertThrows(MetroCardException.class, command::validate);
  }

  Stream<Arguments> validCommands();

  Stream<Arguments> invalidCommands();
}