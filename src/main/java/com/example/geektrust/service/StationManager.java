package com.example.geektrust.service;

import com.example.geektrust.AppConstants;
import com.example.geektrust.core.model.*;
import com.example.geektrust.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class StationManager {
  private final List<MetroCard> metroCards;
  private final List<MetroStation> metroStations;

  public StationManager() {
    this.metroCards = new ArrayList<>();
    this.metroStations = new ArrayList<>();
  }

  private MetroCard getMetroCard(String metroCardNumber) {
    return metroCards.stream()
        .filter(metroCard -> metroCard.getCardNumber().equals(metroCardNumber))
        .findFirst()
        .orElse(this.createMetroCard(metroCardNumber));
  }

  private MetroCard createMetroCard(String metroCardNumber) {
    MetroCard metroCard = new MetroCard(metroCardNumber);
    this.metroCards.add(metroCard);
    return metroCard;
  }

  private MetroStation getMetroStation(StationName stationName) {
    return metroStations.stream()
        .filter(metroStation -> metroStation.getSource().equals(stationName))
        .findFirst()
        .orElse(createMetroStation(stationName));
  }

  private MetroStation createMetroStation(StationName stationName) {
    MetroStation metroStation = new MetroStation(stationName);
    this.metroStations.add(metroStation);
    return metroStation;
  }

  public void addBalance(String metroCardNumber, Double balanceAmount) {
    MetroCard metroCard = getMetroCard(metroCardNumber);
    metroCard.recharge(balanceAmount);
  }

  public void checkIn(String metroCardNumber, PassengerType passengerType, StationName stationName) {
    MetroCard metroCard = getMetroCard(metroCardNumber);
    MetroStation metroStation = getMetroStation(stationName);

    MetroTicket metroTicket = metroCard.issueTicket(passengerType, metroStation.getSource(), metroStation.getDestination());
    metroStation.issueTicket(metroTicket);
  }

  public void printSummary(Logger logger) {
    AppConstants.STATION_SUMMARY_ORDER.forEach(stationName -> {
      MetroStation metroStation = getMetroStation(stationName);
      metroStation.logSummary(logger);
    });
  }
}
