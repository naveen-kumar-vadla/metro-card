package com.example.geektrust.exception;

public class InvalidPassengerTypeException extends RuntimeException {
  public InvalidPassengerTypeException(String passengerType) {
    super("Invalid passenger type: " + passengerType);
  }
}
