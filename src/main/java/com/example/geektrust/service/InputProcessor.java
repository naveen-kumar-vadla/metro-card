package com.example.geektrust.service;

import com.example.geektrust.core.command.Command;
import com.example.geektrust.logger.Logger;

import java.util.List;

public class InputProcessor {
  private final Logger logger;
  private final StationManager stationManager;

  public InputProcessor(StationManager stationManager, Logger logger) {
    this.stationManager = stationManager;
    this.logger = logger;
  }

  public void processCommands(List<Command> commands) {
    commands.forEach(command -> command.execute(stationManager, logger));
  }
}
