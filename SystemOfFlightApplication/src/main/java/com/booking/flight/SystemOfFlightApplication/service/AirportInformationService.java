package com.booking.flight.SystemOfFlightApplication.service;

import com.booking.flight.SystemOfFlightApplication.dto.AirportInformationDto;
import com.booking.flight.SystemOfFlightApplication.dto.PlaneDto;

import java.util.List;

public interface AirportInformationService{
    String createAirportInformation(AirportInformationDto airportInformationDto);
    List<AirportInformationDto> getAllAirportInformation();
    AirportInformationDto getAirportInformationById(Long id) throws Exception;
    //void save(AirportInformationDto airportInformationDto);
    String updateAirportInformation(Long id, AirportInformationDto airportInformationDto) throws Exception;
    String deleteAirportInformation(Long id) throws Exception;
}
