package com.example.geektrust.core.command;

import com.example.geektrust.exception.InvalidAmountException;
import com.example.geektrust.exception.InvalidMetroCardNumberException;
import com.example.geektrust.logger.Logger;
import com.example.geektrust.service.StationManager;

import java.util.List;

import static com.example.geektrust.AppConstants.INDEX_1;
import static com.example.geektrust.AppConstants.INDEX_2;

public class BalanceCommand implements Command {
  private final String metroCardNumber;
  private final String balanceAmount;

  public BalanceCommand(String metroCardNumber, String balanceAmount) {
    this.metroCardNumber = metroCardNumber;
    this.balanceAmount = balanceAmount;
  }

  public static BalanceCommand create(List<String> params) {
    String metroCardNumber = params.get(INDEX_1);
    String balanceAmount = params.get(INDEX_2);
    return new BalanceCommand(metroCardNumber, balanceAmount);
  }

  public Double getBalanceAmount() {
    return Double.parseDouble(balanceAmount);
  }

  @Override
  public void validate() {
    if (metroCardNumber == null || metroCardNumber.isEmpty()) {
      throw new InvalidMetroCardNumberException(metroCardNumber);
    }
    if (balanceAmount == null || balanceAmount.isEmpty()) {
      throw new InvalidAmountException(balanceAmount);
    }
    if (getBalanceAmount() <= 0) {
      throw new InvalidAmountException(balanceAmount);
    }
  }

  @Override
  public void execute(StationManager stationManager, Logger logger) {
    stationManager.addBalance(metroCardNumber, getBalanceAmount());
  }
}
