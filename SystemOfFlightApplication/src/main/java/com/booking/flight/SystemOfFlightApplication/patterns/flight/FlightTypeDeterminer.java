package com.booking.flight.SystemOfFlightApplication.patterns.flight;

import com.booking.flight.SystemOfFlightApplication.dto.AirportDto;
import com.booking.flight.SystemOfFlightApplication.enums.FlightType;

public class FlightTypeDeterminer {

    public FlightType determineFlightType(AirportDto departure, AirportDto destination, boolean isConnecting) {
        if (isDomestic(departure, destination)) {
            return isConnecting ? FlightType.DOMESTICCONNECTING : FlightType.DOMESTICFLIGHT;
        } else {
            return isConnecting ? FlightType.INTERNATIONALCONNECTING : FlightType.INTERNATIONALFLIGHT;
        }
    }
    private boolean isDomestic(AirportDto departure, AirportDto destination) {
        // Yurt içi uçuş, eğer kalkış ve varış havalimanlarının ülke kodları aynıysa
        return departure.getCountryName().equals(destination.getCountryName());
    }
}
