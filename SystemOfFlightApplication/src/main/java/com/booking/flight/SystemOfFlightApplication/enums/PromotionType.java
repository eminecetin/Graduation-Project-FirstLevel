package com.booking.flight.SystemOfFlightApplication.enums;

import java.util.stream.Stream;

public enum PromotionType {

    PERCENTAGE_DISCOUNT("percentagediscount"),
    FIXED_DISCOUNT("fixediscount");

    private String description;
    PromotionType(String description){
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
