package com.example.geektrust.core.command;

import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class CheckInCommandTest implements CommandTest {

  @Override
  public Stream<Arguments> validCommands() {
    return Stream.of(
        arguments(CheckInCommand.create(Arrays.asList("CHECK_IN", "1234567890", "ADULT", "CENTRAL"))),
        arguments(CheckInCommand.create(Arrays.asList("CHECK_IN", "1234567891", "KID", "CENTRAL"))),
        arguments(CheckInCommand.create(Arrays.asList("CHECK_IN", "1234567892", "SENIOR_CITIZEN", "CENTRAL")))
    );
  }

  @Override
  public Stream<Arguments> invalidCommands() {
    return Stream.of(
        arguments(CheckInCommand.create(Arrays.asList("CHECK_IN", null, "ADULT", "CENTRAL"))),
        arguments(CheckInCommand.create(Arrays.asList("CHECK_IN", "", "ADULT", "CENTRAL"))),
        arguments(CheckInCommand.create(Arrays.asList("CHECK_IN", "1234567891", null, "CENTRAL"))),
        arguments(CheckInCommand.create(Arrays.asList("CHECK_IN", "1234567891", "", "CENTRAL"))),
        arguments(CheckInCommand.create(Arrays.asList("CHECK_IN", "1234567891", "INVALID", "CENTRAL"))),
        arguments(CheckInCommand.create(Arrays.asList("CHECK_IN", "1234567892", "SENIOR_CITIZEN", null))),
        arguments(CheckInCommand.create(Arrays.asList("CHECK_IN", "1234567892", "SENIOR_CITIZEN", ""))),
        arguments(CheckInCommand.create(Arrays.asList("CHECK_IN", "1234567892", "SENIOR_CITIZEN", "INVALID_STATION")))
    );
  }
}