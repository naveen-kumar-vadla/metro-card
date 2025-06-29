package com.example.geektrust.core.model;

import com.example.geektrust.exception.InvalidPassengerTypeException;

import static com.example.geektrust.AppConstants.*;

public enum PassengerType {
  ADULT,
  KID,
  SENIOR_CITIZEN;

  public Double fare() {
    switch (this) {
      case ADULT:
        return ADULT_FARE;
      case KID:
        return KID_FARE;
      case SENIOR_CITIZEN:
        return SENIOR_CITIZEN_FARE;
      default:
        throw new InvalidPassengerTypeException(this.name());
    }
  }
}
