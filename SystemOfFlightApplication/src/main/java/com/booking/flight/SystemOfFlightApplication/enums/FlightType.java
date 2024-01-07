package com.booking.flight.SystemOfFlightApplication.enums;

import java.util.stream.Stream;

public enum FlightType {

    DOMESTICFLIGHT("domesticflight"),
    INTERNATIONALFLIGHT("internationalflight"),
    DOMESTICCONNECTING("domesticconnecting"),
    INTERNATIONALCONNECTING("internationalconnecting");

    private String description;

    FlightType(String description){
        this.description=description;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public static FlightType value(String description){
        return Stream.of(FlightType.values())
                .filter(i -> i.getDescription().equals(description))
                .findFirst()
                .orElse(null);
    }
}
