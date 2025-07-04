package com.example.geektrust.core.command;

import com.example.geektrust.logger.Logger;
import com.example.geektrust.service.StationManager;

public class PrintSummaryCommand implements Command {
  public static PrintSummaryCommand create() {
    return new PrintSummaryCommand();
  }

  @Override
  public void execute(StationManager stationManager, Logger logger) {
    stationManager.printSummary(logger);
  }
}
