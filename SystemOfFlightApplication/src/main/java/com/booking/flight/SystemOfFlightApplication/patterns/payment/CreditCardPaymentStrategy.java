package com.booking.flight.SystemOfFlightApplication.patterns.payment;

public class CreditCardPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Credit card payment of " + amount + " was successful.");
    }
}
