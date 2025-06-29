package com.example.geektrust.exception;

public class InvalidStationNameException extends MetroCardException {
  public InvalidStationNameException(String stationName) {
    super("Invalid station name: " + stationName);
  }
}
