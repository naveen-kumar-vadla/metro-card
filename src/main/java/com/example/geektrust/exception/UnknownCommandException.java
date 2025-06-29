package com.example.geektrust.exception;

public class UnknownCommandException extends MetroCardException {
  public UnknownCommandException(String command) {
    super("Unknown command: " + command);
  }
}
