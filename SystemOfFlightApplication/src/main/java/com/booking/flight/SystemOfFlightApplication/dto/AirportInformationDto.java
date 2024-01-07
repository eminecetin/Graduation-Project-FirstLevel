package com.booking.flight.SystemOfFlightApplication.dto;


import lombok.Data;

import java.util.List;

@Data
public class AirportInformationDto {
    private Long id;
    private List<String> restaurantNames;
    private List<String> storeNames;
    private String carPark;
    private String luggageService;
    private Long airportId;
}

// private AirportDto airport; // Burada AirportDto sınıfının yapısı önemli