package com.example.geektrust.exception;

public class InvalidAmountException extends MetroCardException {
  public InvalidAmountException(String amount) {
    super("Invalid amount: " + amount);
  }
}
