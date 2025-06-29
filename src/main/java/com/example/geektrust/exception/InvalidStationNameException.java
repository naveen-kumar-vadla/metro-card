package com.example.geektrust.exception;

public class InvalidStationNameException extends RuntimeException {
  public InvalidStationNameException(String stationName) {
    super("Invalid station name: " + stationName);
  }
}
