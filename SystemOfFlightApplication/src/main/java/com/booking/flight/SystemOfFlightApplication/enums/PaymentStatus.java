package com.booking.flight.SystemOfFlightApplication.enums;

import java.util.stream.Stream;

public enum PaymentStatus {
    PENDING("Pending"),
    COMPLETED("Completed"),
    FAILED("Failed");

    private String description;

    PaymentStatus(String description){
        this.description=description;
    }


    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public static PaymentStatus value(String description){
        return Stream.of(PaymentStatus.values())
                .filter(i -> i.getDescription().equals(description))
                .findFirst()
                .orElse(null);
    }
}
