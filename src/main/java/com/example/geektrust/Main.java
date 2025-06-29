package com.example.geektrust;

import com.example.geektrust.core.command.Command;
import com.example.geektrust.logger.Logger;
import com.example.geektrust.service.InputProcessor;
import com.example.geektrust.service.StationManager;
import com.example.geektrust.util.CommandsParser;
import com.example.geektrust.util.FileReader;

import java.util.List;

import static com.example.geektrust.AppConstants.ZERO;

public class Main {
  public static void main(String[] args) {
    CommandsParser commandsParser = new CommandsParser();
    Logger logger = new Logger();
    FileReader fileReader = new FileReader();
    StationManager stationManager = new StationManager();

    try {
      String filePath = args.length > ZERO ? args[ZERO] : null;
      List<String> inputs = fileReader.readLines(filePath);
      List<Command> commands = commandsParser.parseCommands(inputs);

      InputProcessor inputProcessor = new InputProcessor(stationManager, logger);
      inputProcessor.processCommands(commands);
    } catch (Exception e) {
      logger.error("An error occurred: " + e.getMessage());
    }
  }
}
