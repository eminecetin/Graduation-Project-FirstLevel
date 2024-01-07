package com.booking.flight.SystemOfFlightApplication.dto;

import lombok.Data;

import java.util.List;

@Data
public class AirlineDto {
    private Long id;
    private String name;
    private String airlineCode;
    private List<Long> flightId;
}
//  private List<FlightDto> flights;