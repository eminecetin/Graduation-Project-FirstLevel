package com.booking.flight.SystemOfFlightApplication.enums;

import java.util.stream.Stream;

public enum CustomerType {
    NEW_CUSTOMER("new_customer"),
    REGISTERED_CUSTOMER("registered_customer"),
    GUEST("guest");

    private String description;

    CustomerType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description){
        this.description=description;
    }
    public static CustomerType value(String description){
        return Stream.of(CustomerType.values())
                .filter(i -> i.getDescription().equals(description))
                .findFirst()
                .orElse(null);
    }
}
