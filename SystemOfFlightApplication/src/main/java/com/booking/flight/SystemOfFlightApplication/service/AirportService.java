package com.booking.flight.SystemOfFlightApplication.service;

import com.booking.flight.SystemOfFlightApplication.dto.AirportDto;

import java.util.List;

public interface AirportService {
    String createAirport(AirportDto airportDto);
    List<AirportDto> getAllAirports();
    AirportDto getAirportById(Long id) throws Exception;
    String updateAirport(Long id, AirportDto airportDto) throws Exception;
    String deleteAirport(Long id) throws Exception;
}
