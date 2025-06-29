package com.example.geektrust.core.model;

public class MetroTicket {
  private final StationName from;
  private final StationName to;
  private final Double fare;
  private final Double additionalCharge;
  private final Double discount;
  private final PassengerType passengerType;

  public MetroTicket(StationName from, StationName to, Double fare, Double additionalCharge, Double discount, PassengerType passengerType) {
    this.from = from;
    this.to = to;
    this.fare = fare;
    this.additionalCharge = additionalCharge;
    this.discount = discount;
    this.passengerType = passengerType;
  }

  public Double finalFare() {
    return fare + additionalCharge - discount;
  }

  public StationName getTo() {
    return to;
  }

  public Double getDiscount() {
    return discount;
  }

  public boolean isDiscounted() {
    return discount > 0;
  }

  public PassengerType getPassengerType() {
    return passengerType;
  }
}
