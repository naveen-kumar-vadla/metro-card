package com.example.geektrust.util;

import com.example.geektrust.core.command.Command;
import com.example.geektrust.exception.UnknownCommandException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CommandsParserTest {
  CommandsParser commandsParser = new CommandsParser();

  public static Stream<Arguments> validCommands() {
    return Stream.of(
        arguments(Collections.singletonList("BALANCE 1234567890 100.0")),
        arguments(Collections.singletonList("CHECK_IN 1234567891 ADULT CENTRAL")),
        arguments(Collections.singletonList("PRINT_SUMMARY"))
    );
  }

  @ParameterizedTest
  @MethodSource("validCommands")
  void shouldParseAllValidCommand(List<String> params) {
    List<Command> commands = commandsParser.parseCommands(params);
    assertNotNull(commands);
    assertFalse(commands.isEmpty());
  }

  @Test
  void shouldThrowForInvalidCommands() {
    List<String> params = Collections.singletonList("INVALID");
    assertThrows(UnknownCommandException.class, () -> commandsParser.parseCommands(params));
  }
}