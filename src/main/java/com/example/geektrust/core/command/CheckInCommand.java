package com.example.geektrust.core.command;

import com.example.geektrust.core.model.PassengerType;
import com.example.geektrust.core.model.StationName;
import com.example.geektrust.exception.InvalidMetroCardNumberException;
import com.example.geektrust.exception.InvalidPassengerTypeException;
import com.example.geektrust.exception.InvalidStationNameException;
import com.example.geektrust.logger.Logger;
import com.example.geektrust.service.StationManager;
import com.example.geektrust.util.CommonUtil;

import java.util.List;

import static com.example.geektrust.AppConstants.*;

public class CheckInCommand implements Command {
  private final String metroCardNumber;
  private final String passengerType;
  private final String stationName;

  public CheckInCommand(String metroCardNumber, String passengerType, String stationName) {
    this.metroCardNumber = metroCardNumber;
    this.passengerType = passengerType;
    this.stationName = stationName;
  }

  public static Command create(List<String> params) {
    String metroCardNumber = params.get(INDEX_1);
    String passengerType = params.get(INDEX_2);
    String stationName = params.get(INDEX_3);
    return new CheckInCommand(metroCardNumber, passengerType, stationName);
  }

  public PassengerType getPassengerType() {
    return PassengerType.valueOf(passengerType);
  }

  public StationName getStationName() {
    return StationName.valueOf(stationName);
  }

  @Override
  public void validate() {
    if (metroCardNumber == null || metroCardNumber.isEmpty()) {
      throw new InvalidMetroCardNumberException(metroCardNumber);
    }
    if (passengerType == null || passengerType.isEmpty() || CommonUtil.isInvalidEnum(PassengerType.class, passengerType)) {
      throw new InvalidPassengerTypeException(passengerType);
    }
    if (stationName == null || stationName.isEmpty() || CommonUtil.isInvalidEnum(StationName.class, stationName)) {
      throw new InvalidStationNameException(stationName);
    }
  }

  @Override
  public void execute(StationManager stationManager, Logger logger) {
    stationManager.checkIn(metroCardNumber, getPassengerType(), getStationName());
  }
}
