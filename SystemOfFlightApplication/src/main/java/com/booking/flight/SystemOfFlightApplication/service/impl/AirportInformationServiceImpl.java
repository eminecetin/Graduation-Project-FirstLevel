package com.booking.flight.SystemOfFlightApplication.service.impl;

import com.booking.flight.SystemOfFlightApplication.dto.AirportInformationDto;
import com.booking.flight.SystemOfFlightApplication.entity.AirportInformation;
import com.booking.flight.SystemOfFlightApplication.repository.AirportInformationRepository;
import com.booking.flight.SystemOfFlightApplication.service.AirportInformationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class AirportInformationServiceImpl implements AirportInformationService {
    private final AirportInformationRepository airportInformationRepository;
    private final ModelMapper modelMapper;

    public AirportInformationServiceImpl(AirportInformationRepository airportInformationRepository, ModelMapper modelMapper) {
        this.airportInformationRepository = airportInformationRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public String createAirportInformation(AirportInformationDto airportInformationDto) {
        AirportInformation airportInformation = modelMapper.map(airportInformationDto, AirportInformation.class);
        airportInformationRepository.save(airportInformation);
        return "Airport information created successfully";
    }

    @Override
    public List<AirportInformationDto> getAllAirportInformation() {
        return airportInformationRepository.findAll()
                .stream()
                .map(airportInformation -> modelMapper.map(airportInformation, AirportInformationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AirportInformationDto getAirportInformationById(Long id) throws Exception {
        AirportInformation airportInformation = airportInformationRepository.findById(id)
                .orElseThrow(() -> new Exception("Airport information not found with ID: " + id));
        return modelMapper.map(airportInformation, AirportInformationDto.class);
    }

   /* @Override
    public void save(AirportInformationDto airportInformationDto) {

    }*/

    @Override
    public String updateAirportInformation(Long id, AirportInformationDto airportInformationDto) throws Exception {
        AirportInformation existingAirportInformation = airportInformationRepository.findById(id)
                .orElseThrow(() -> new Exception("Airport information not found with ID: " + id));

        modelMapper.map(airportInformationDto, existingAirportInformation);
        airportInformationRepository.save(existingAirportInformation);
        return "Airport information updated successfully";
    }

    @Override
    public String deleteAirportInformation(Long id) throws Exception {
        if (!airportInformationRepository.existsById(id)) {
            throw new Exception("Airport information not found with ID: " + id);
        }
        airportInformationRepository.deleteById(id);
        return "Airport information deleted successfully";
    }
}
