package com.booking.flight.SystemOfFlightApplication.service;

import com.booking.flight.SystemOfFlightApplication.dto.AirlineDto;
import com.booking.flight.SystemOfFlightApplication.dto.FlightDto;

import java.util.List;

public interface FlightService {
    Long createFlight(FlightDto flightDto);
    List<FlightDto> getAllFlights();
    FlightDto getFlightDetails(Long flightId);
    FlightDto getFlightById(Long id) throws Exception;
    String updateFlight(Long id, FlightDto flightDto) throws Exception;
    String deleteFlight(Long id) throws Exception;
}
