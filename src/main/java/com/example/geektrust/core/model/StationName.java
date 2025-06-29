package com.example.geektrust.core.model;

import com.example.geektrust.exception.InvalidStationNameException;

public enum StationName {
  CENTRAL,
  AIRPORT;

  public StationName destination() {
    switch (this) {
      case CENTRAL:
        return AIRPORT;
      case AIRPORT:
        return CENTRAL;
      default:
        throw new InvalidStationNameException(this.name());
    }
  }
}
