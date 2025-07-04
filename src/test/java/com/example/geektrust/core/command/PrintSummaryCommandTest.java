package com.example.geektrust.core.command;

import com.example.geektrust.logger.Logger;
import com.example.geektrust.service.StationManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PrintSummaryCommandTest {
  StationManager stationManager = mock(StationManager.class);
  Logger logger = mock(Logger.class);

  @Test
  void shouldValidateCommand() {
    PrintSummaryCommand command = PrintSummaryCommand.create();
    assertDoesNotThrow(command::validate);
  }

  @Test
  void shouldExecuteCommand() {
    PrintSummaryCommand command = PrintSummaryCommand.create();
    assertDoesNotThrow(() -> command.execute(stationManager, logger));
    verify(stationManager).printSummary(logger);
  }
}
