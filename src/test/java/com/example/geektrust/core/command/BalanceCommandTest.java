package com.example.geektrust.core.command;

import com.example.geektrust.logger.Logger;
import com.example.geektrust.service.StationManager;
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

class BalanceCommandTest implements CommandTest {
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
        arguments(BalanceCommand.create(Arrays.asList("BALANCE", "1234567890", "100.0"))),
        arguments(BalanceCommand.create(Arrays.asList("BALANCE", "1234567891", "600.0"))),
        arguments(BalanceCommand.create(Arrays.asList("BALANCE", "1234567892", "1000.0")))
    );
  }

  @Override
  public Stream<Arguments> invalidCommands() {
    return Stream.of(
        arguments(BalanceCommand.create(Arrays.asList("BALANCE", null, "100.0"))),
        arguments(BalanceCommand.create(Arrays.asList("BALANCE", "", "600.0"))),
        arguments(BalanceCommand.create(Arrays.asList("BALANCE", "1234567892", null))),
        arguments(BalanceCommand.create(Arrays.asList("BALANCE", "1234567892", ""))),
        arguments(BalanceCommand.create(Arrays.asList("BALANCE", "1234567892", "0")))
    );
  }

  @ParameterizedTest
  @MethodSource("validCommands")
  void shouldExecuteCommand(BalanceCommand command) {
    assertDoesNotThrow(() -> command.execute(stationManager, logger));
    verify(stationManager).addBalance(any(String.class), any(Double.class));
  }
}
