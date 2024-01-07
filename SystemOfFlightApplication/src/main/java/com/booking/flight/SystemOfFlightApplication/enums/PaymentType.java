package com.booking.flight.SystemOfFlightApplication.enums;

import java.util.stream.Stream;

public enum PaymentType {

    CREDITCARD("Credit Card"),
    PAYPAL("PayPal");

    private String description;

    PaymentType(String description){
        this.description=description;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public static PaymentType value(String description){
        return Stream.of(PaymentType.values())
                .filter(i -> i.getDescription().equals(description))
                .findFirst()
                .orElse(null);
    }
}
