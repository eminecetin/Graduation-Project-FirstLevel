package com.booking.flight.SystemOfFlightApplication.enums;

import java.util.stream.Stream;

public enum Role {

    ADMIN("admin"),
    CUSTOMER("customer");
    private String description;

    Role(String description){
        this.description=description;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public static Role value(String description){
        return Stream.of(Role.values())
                .filter(i -> i.getDescription().equals(description))
                .findFirst()
                .orElse(null);
    }
}
