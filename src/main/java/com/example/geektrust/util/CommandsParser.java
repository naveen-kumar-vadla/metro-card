package com.example.geektrust.util;

import com.example.geektrust.AppConstants;
import com.example.geektrust.core.command.BalanceCommand;
import com.example.geektrust.core.command.CheckInCommand;
import com.example.geektrust.core.command.Command;
import com.example.geektrust.core.command.PrintSummaryCommand;
import com.example.geektrust.exception.UnknownCommandException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.geektrust.AppConstants.*;

public class CommandsParser {
  public List<Command> parseCommands(List<String> inputLines) {
    List<Command> commands = new ArrayList<>();
    for (String line : inputLines) {
      List<String> params = Arrays.asList(line.trim().split(AppConstants.SPACE_DELIMITER));
      commands.add(extractCommand(params));
    }
    return commands;
  }

  private Command extractCommand(List<String> params) {
    String commandType = params.get(ZERO);
    switch (commandType) {
      case BALANCE:
        return BalanceCommand.create(params);
      case CHECK_IN:
        return CheckInCommand.create(params);
      case PRINT_SUMMARY:
        return PrintSummaryCommand.create();
      default:
        throw new UnknownCommandException(commandType);
    }
  }
}
