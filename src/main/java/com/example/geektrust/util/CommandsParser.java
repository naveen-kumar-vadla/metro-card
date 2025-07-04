package com.example.geektrust.util;

import com.example.geektrust.AppConstants;
import com.example.geektrust.core.command.BalanceCommand;
import com.example.geektrust.core.command.CheckInCommand;
import com.example.geektrust.core.command.Command;
import com.example.geektrust.core.command.PrintSummaryCommand;
import com.example.geektrust.exception.UnknownCommandException;

import java.util.*;
import java.util.function.Function;

import static com.example.geektrust.AppConstants.*;

public class CommandsParser {
  private final Map<String, Function<List<String>, Command>> commandCreators;

  public CommandsParser() {
    this.commandCreators = new HashMap<>();
    this.commandCreators.put(BALANCE, BalanceCommand::create);
    this.commandCreators.put(CHECK_IN, CheckInCommand::create);
    this.commandCreators.put(PRINT_SUMMARY, params -> PrintSummaryCommand.create());
  }

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
    Function<List<String>, Command> creator = commandCreators.get(commandType);
    if (creator == null) {
      throw new UnknownCommandException(commandType);
    }
    return creator.apply(params);
  }
}
