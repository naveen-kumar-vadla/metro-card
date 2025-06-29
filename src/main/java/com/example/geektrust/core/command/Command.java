package com.example.geektrust.core.command;

import com.example.geektrust.logger.Logger;
import com.example.geektrust.service.StationManager;

public interface Command {
  default void validate() {}

  void execute(StationManager stationManager, Logger logger);
}
