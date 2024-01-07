package com.booking.flight.SystemOfFlightApplication.beans;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class StripeChargeDto {
    private Long id;

    private String stripeToken;
    private String userName;
    private int amount;
    private boolean success;
    private String message;
    private String chargeId;
    private Map<String, Object> additionalInfo=new HashMap<>();
}
