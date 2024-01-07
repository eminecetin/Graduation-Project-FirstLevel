package com.booking.flight.SystemOfFlightApplication.patterns.payment;

import org.springframework.stereotype.Service;

public class PaypalPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paypal payment of " + amount + " was successful.");
    }
}
