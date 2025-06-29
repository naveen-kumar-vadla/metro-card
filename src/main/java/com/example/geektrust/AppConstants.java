package com.example.geektrust;

import com.example.geektrust.core.model.PassengerType;
import com.example.geektrust.core.model.StationName;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
  public static final int ZERO = 0;
  public static final double DEFAULT_AMOUNT = ZERO;
  public static final int INDEX_1 = 1;
  public static final int INDEX_2 = 2;
  public static final int INDEX_3 = 3;

  public static final double ADULT_FARE = 200.0;
  public static final double KID_FARE = 50.0;
  public static final double SENIOR_CITIZEN_FARE = 100.0;

  public static final double ADDITIONAL_CHARGE_MULTIPLIER = 0.02;
  public static final double DISCOUNT_MULTIPLIER = 0.5;

  public static final String BALANCE = "BALANCE";
  public static final String CHECK_IN = "CHECK_IN";
  public static final String PRINT_SUMMARY = "PRINT_SUMMARY";

  public static final String SPACE_DELIMITER = " ";
  public static final String STATION_SUMMARY_FORMAT = "TOTAL_COLLECTION %s %d %d";
  public static final String PASSENGER_TYPE_SUMMARY = "PASSENGER_TYPE_SUMMARY";

  public static final List<StationName> STATION_SUMMARY_ORDER = Arrays.asList(StationName.CENTRAL, StationName.AIRPORT);
  public static final List<PassengerType> PASSENGER_SUMMARY_ORDER = Arrays.asList(PassengerType.ADULT, PassengerType.KID, PassengerType.SENIOR_CITIZEN);
}