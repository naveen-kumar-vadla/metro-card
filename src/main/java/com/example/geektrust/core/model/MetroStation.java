package com.example.geektrust.core.model;

import com.example.geektrust.logger.Logger;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.geektrust.AppConstants.*;
import static com.example.geektrust.AppConstants.PASSENGER_TYPE_SUMMARY;

public class MetroStation {
  private final StationName source;
  private final StationName destination;
  private Double totalCollection;
  private Double totalDiscount;
  private final Map<PassengerType, Integer> passengerCount;

  public MetroStation(StationName source) {
    this.source = source;
    this.destination = source.destination();
    this.totalCollection = DEFAULT_AMOUNT;
    this.totalDiscount = DEFAULT_AMOUNT;
    this.passengerCount = new EnumMap<>(PassengerType.class);
    this.passengerCount.put(PassengerType.ADULT, ZERO);
    this.passengerCount.put(PassengerType.KID, ZERO);
    this.passengerCount.put(PassengerType.SENIOR_CITIZEN, ZERO);
  }

  public StationName getSource() {
    return source;
  }

  public StationName getDestination() {
    return destination;
  }

  public void issueTicket(MetroTicket metroTicket) {
    this.totalCollection += metroTicket.finalFare();
    this.totalDiscount += metroTicket.getDiscount();
    this.passengerCount.put(metroTicket.getPassengerType(), this.passengerCount.get(metroTicket.getPassengerType()) + 1);
  }

  public void logSummary(Logger logger) {
    String stationSummary = getStationSummary();
    String passengerTypeSummary = getPassengerTypeSummary();

    StringBuilder summary = new StringBuilder();
    summary.append(stationSummary);
    summary.append(System.lineSeparator());
    summary.append(passengerTypeSummary);
    logger.info(summary);
  }

  private String getPassengerTypeSummary() {
    String passengerSummary = passengerCount.entrySet()
        .stream()
        .filter(m -> m.getValue() > ZERO)
        .sorted(Comparator
            .comparing(Map.Entry<PassengerType, Integer>::getValue).reversed()
            .thenComparing(entry -> PASSENGER_SUMMARY_ORDER.indexOf(entry.getKey()))
        )
        .map(m -> m.getKey().name() + SPACE_DELIMITER + m.getValue())
        .collect(Collectors.joining(System.lineSeparator()));

    return PASSENGER_TYPE_SUMMARY + System.lineSeparator() + passengerSummary;
  }

  private String getStationSummary() {
    return String.format(
        STATION_SUMMARY_FORMAT,
        source.name(),
        totalCollection.intValue(),
        totalDiscount.intValue());
  }
}
