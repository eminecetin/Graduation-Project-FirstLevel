package com.booking.flight.SystemOfFlightApplication.service.impl;

import com.booking.flight.SystemOfFlightApplication.dto.AirlineDto;
import com.booking.flight.SystemOfFlightApplication.dto.AirportDto;
import com.booking.flight.SystemOfFlightApplication.entity.Airline;
import com.booking.flight.SystemOfFlightApplication.repository.AirlineRepository;
import com.booking.flight.SystemOfFlightApplication.service.AirlineService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AirlineServiceImpl implements AirlineService {
    private final AirlineRepository airlineRepository;
    private final ModelMapper modelMapper;

    public AirlineServiceImpl(AirlineRepository airlineRepository, ModelMapper modelMapper) {
        this.airlineRepository = airlineRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public String createAirline(AirlineDto airlineDto) {
        Airline airline = modelMapper.map(airlineDto, Airline.class);
        Airline savedAirline = airlineRepository.save(airline);
        return "Airline created successfully. Airline ID: " + savedAirline.getId();
    }

    @Override
    public List<AirlineDto> getAllAirlines() {
        return airlineRepository.findAll()
                .stream()
                .map(airline -> modelMapper.map(airline, AirlineDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AirlineDto getAirlineById(Long id) throws Exception {
        Airline airline = airlineRepository.findById(id)
                .orElseThrow(() -> new Exception("Airline not found with ID: " + id));
        return modelMapper.map(airline, AirlineDto.class);
    }

    @Override
    public String updateAirline(Long id, AirlineDto airlineDto) throws Exception {
        Airline existingAirline = airlineRepository.findById(id)
                .orElseThrow(() -> new Exception("Airline not found with ID: " + id));

        modelMapper.map(airlineDto, existingAirline);
        airlineRepository.save(existingAirline);
        return "Airline updated successfully. Airline ID: " + id;
    }

    @Override
    public String deleteAirline(Long id) throws Exception {
        if (!airlineRepository.existsById(id)) {
            throw new Exception("Airline not found with ID: " + id);
        }
        airlineRepository.deleteById(id);
        return "Airline deleted successfully. Airline ID: " + id;
    }
}
