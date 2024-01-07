package com.booking.flight.SystemOfFlightApplication.beans;

import lombok.Data;

@Data
public class PaymentRequest {
    private String cardNumber;
    private String cardHolderName;
    private int amount;
    private String paymentType; // Örneğin, "creditcard" veya "paypal"

}
