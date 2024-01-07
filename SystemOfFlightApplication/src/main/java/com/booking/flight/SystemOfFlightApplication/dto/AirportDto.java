package com.booking.flight.SystemOfFlightApplication.dto;

import com.booking.flight.SystemOfFlightApplication.entity.Flight;
import lombok.Data;

import java.util.List;

@Data
public class AirportDto {
    private Long id;
    private String name;
    private String countryName;
    private String cityName;
    private String capacity;
    private String communicationInformation;
    private AirportInformationDto airportInformation;
    private List<Long> departureFlights;
    private List<Long> arrivalFlights;
}

//  private AirportInformationDto airportInformation;
//  private List<FlightDto> flights;
