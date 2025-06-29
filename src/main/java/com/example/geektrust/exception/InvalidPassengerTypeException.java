package com.example.geektrust.exception;

public class InvalidPassengerTypeException extends MetroCardException {
  public InvalidPassengerTypeException(String passengerType) {
    super("Invalid passenger type: " + passengerType);
  }
}
