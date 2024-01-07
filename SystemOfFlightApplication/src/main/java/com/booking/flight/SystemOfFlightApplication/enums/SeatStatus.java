package com.booking.flight.SystemOfFlightApplication.enums;

import java.util.stream.Stream;

public enum SeatStatus {

    AVAILABLE("Available"),
    RESERVED("Reserved"),
    SELECTED("Selected");

    private final String description;

    SeatStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    public static SeatStatus value(String description){
        return Stream.of(SeatStatus.values())
                .filter(i -> i.getDescription().equals(description))
                .findFirst()
                .orElse(null);
    }
}
