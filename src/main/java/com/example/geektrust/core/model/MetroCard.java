package com.example.geektrust.core.model;

import static com.example.geektrust.AppConstants.*;

public class MetroCard {
  private final String cardNumber;
  private Double balance;
  private MetroTicket lastJourney;

  public MetroCard(String metroCardNumber) {
    this.cardNumber = metroCardNumber;
    this.balance = DEFAULT_AMOUNT;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void recharge(Double amount) {
    this.balance += amount;
  }

  public void charge(Double amount) {
    this.balance -= amount;
  }

  public MetroTicket issueTicket(PassengerType passengerType, StationName from, StationName to) {
    Double fare = passengerType.fare();
    Double discount = calculateDiscount(fare, from);
    Double additionalCharge = calculateAdditionalCharge(fare, discount);

    MetroTicket metroTicket = new MetroTicket(
        from,
        to,
        fare,
        additionalCharge,
        discount,
        passengerType
    );
    charge(metroTicket.finalFare());
    this.lastJourney = metroTicket;
    return metroTicket;
  }

  private Double calculateAdditionalCharge(Double fare, Double discount) {
    double requiredFare = fare - discount;
    if (balance >= requiredFare) return DEFAULT_AMOUNT;
    double requiredBalance = requiredFare - balance;
    double additionalCharge = requiredBalance * ADDITIONAL_CHARGE_MULTIPLIER;
    recharge(requiredBalance + additionalCharge);
    return additionalCharge;
  }

  private Double calculateDiscount(Double fare, StationName from) {
    if (isReturnJourney(from)) {
      return fare * DISCOUNT_MULTIPLIER;
    }
    return DEFAULT_AMOUNT;
  }

  private boolean isReturnJourney(StationName from) {
    if (lastJourney == null) return false;
    if (lastJourney.getTo() == null) return false;
    if (!lastJourney.getTo().equals(from)) return false;
    return !lastJourney.isDiscounted();
  }
}
