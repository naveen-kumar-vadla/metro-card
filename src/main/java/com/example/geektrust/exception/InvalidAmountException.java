package com.example.geektrust.exception;

public class InvalidAmountException extends RuntimeException {
  public InvalidAmountException(String amount) {
    super("Invalid amount: " + amount);
  }
}
