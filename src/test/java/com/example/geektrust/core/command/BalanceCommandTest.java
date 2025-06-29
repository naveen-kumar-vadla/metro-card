package com.example.geektrust.core.command;

import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class BalanceCommandTest implements CommandTest {
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
}
