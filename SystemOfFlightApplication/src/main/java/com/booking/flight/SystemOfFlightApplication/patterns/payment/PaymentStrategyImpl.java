package com.booking.flight.SystemOfFlightApplication.patterns.payment;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;
@Service
public class PaymentStrategyImpl  {
    private PaymentStrategy paymentStrategy;
    // Ödeme stratejisini ayarlamak için kullanılır
    public void setPaymentStrategy(@Lazy PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    // Ödeme işlemini gerçekleştirir
    public void executePayment(int amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Payment strategy not set");
        }
        paymentStrategy.pay(amount);
    }

}
