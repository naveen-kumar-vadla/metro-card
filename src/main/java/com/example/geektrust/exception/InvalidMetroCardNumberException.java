package com.example.geektrust.exception;

public class InvalidMetroCardNumberException extends MetroCardException {
  public InvalidMetroCardNumberException(String cardNumber) {
    super("Invalid metro card number " + cardNumber);
  }
}
