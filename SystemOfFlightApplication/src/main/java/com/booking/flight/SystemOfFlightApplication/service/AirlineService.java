package com.booking.flight.SystemOfFlightApplication.service;

import com.booking.flight.SystemOfFlightApplication.dto.AirlineDto;

import java.util.List;

public interface AirlineService {
    String createAirline(AirlineDto airlineDto);
    List<AirlineDto> getAllAirlines();
    AirlineDto getAirlineById(Long id) throws Exception;
    String updateAirline(Long id, AirlineDto airlineDto) throws Exception;
    String deleteAirline(Long id) throws Exception;
}
