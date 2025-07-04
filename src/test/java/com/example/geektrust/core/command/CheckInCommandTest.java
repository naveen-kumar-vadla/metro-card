package com.example.geektrust.core.command;

import com.example.geektrust.core.model.PassengerType;
import com.example.geektrust.core.model.StationName;
import com.example.geektrust.logger.Logger;
import com.example.geektrust.service.StationManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CheckInCommandTest implements CommandTest {
  StationManager stationManager;
  Logger logger;

  @BeforeEach
  void setUp() {
    stationManager = mock(StationManager.class);
    logger = mock(Logger.class);
  }

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

  @ParameterizedTest
  @MethodSource("validCommands")
  void shouldExecuteCommand(CheckInCommand command) {
    assertDoesNotThrow(() -> command.execute(stationManager, logger));
    verify(stationManager).checkIn(any(String.class), any(PassengerType.class), any(StationName.class));
  }
}