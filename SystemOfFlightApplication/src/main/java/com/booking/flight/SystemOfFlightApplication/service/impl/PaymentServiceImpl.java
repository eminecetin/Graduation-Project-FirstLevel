package com.booking.flight.SystemOfFlightApplication.service.impl;

import com.booking.flight.SystemOfFlightApplication.patterns.payment.CreditCardPaymentStrategy;
import com.booking.flight.SystemOfFlightApplication.patterns.payment.PaymentStrategy;
import com.booking.flight.SystemOfFlightApplication.patterns.payment.PaymentStrategyImpl;
import com.booking.flight.SystemOfFlightApplication.patterns.payment.PaypalPaymentStrategy;
import com.booking.flight.SystemOfFlightApplication.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private PaymentStrategyImpl paymentService;

    @Override
    public String processPayment(int amount, String paymentType) {
        switch (paymentType) {
            case "paypal":
                paymentService.setPaymentStrategy(new PaypalPaymentStrategy());

                break;
            case "creditcard":
                paymentService.setPaymentStrategy(new CreditCardPaymentStrategy());
                break;
            default:
                throw new IllegalArgumentException("Unknown payment type");
        }

        paymentService.executePayment(amount);
        return "Payment processed successfully";
    }


}
