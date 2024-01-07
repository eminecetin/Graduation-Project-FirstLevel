package com.booking.flight.SystemOfFlightApplication.service;

import com.booking.flight.SystemOfFlightApplication.patterns.payment.PaymentStrategy;

public interface PaymentService {
    String processPayment(int amount, String paymentType);
}
