package com.booking.flight.SystemOfFlightApplication.service.impl;

import com.booking.flight.SystemOfFlightApplication.dto.AirportDto;
import com.booking.flight.SystemOfFlightApplication.dto.FlightDto;
import com.booking.flight.SystemOfFlightApplication.dto.PlaneDto;
import com.booking.flight.SystemOfFlightApplication.entity.Airport;
import com.booking.flight.SystemOfFlightApplication.entity.Flight;
import com.booking.flight.SystemOfFlightApplication.entity.Plane;
import com.booking.flight.SystemOfFlightApplication.enums.PlaneType;
import com.booking.flight.SystemOfFlightApplication.patterns.plane.PlaneFactoryImpl;
import com.booking.flight.SystemOfFlightApplication.repository.FlightRepository;
import com.booking.flight.SystemOfFlightApplication.repository.PlaneRepository;
import com.booking.flight.SystemOfFlightApplication.service.FlightService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    private final PlaneFactoryImpl planeFactory;
    private final PlaneRepository planeRepository;
    private final FlightRepository flightRepository;
    private final ModelMapper modelMapper;

    public FlightServiceImpl(PlaneFactoryImpl planeFactory,
                             PlaneRepository planeRepository, FlightRepository flightRepository,
                             ModelMapper modelMapper) {
        this.planeFactory = planeFactory;
        this.planeRepository = planeRepository;
        this.flightRepository = flightRepository;
        this.modelMapper = modelMapper;
    }


    @Transactional
    public Long createFlight(FlightDto flightDto) {
        PlaneDto planeDto = flightDto.getPlane();
        Plane plane = convertPlaneDtoToEntity(planeDto);
        if (plane.getId() == null) {
            planeRepository.save(plane); // Eğer yeni ise önce plane'i kaydedin
        }
        AirportDto airportDto = flightDto.getDepartureAirport();
        Airport departureairport = convertAirportDtoToEntity(airportDto);
        AirportDto arrivalAirportDto = flightDto.getArrivalAirport();
        Airport arrivalAirport = convertAirportDtoToEntity(arrivalAirportDto);

        Flight flight = convertToEntity(flightDto, plane, departureairport, arrivalAirport); // DTO'yu entity'e dönüştürme metodu
        flightRepository.save(flight);
        return flight.getId();
    }

    private Plane convertPlaneDtoToEntity(PlaneDto planeDto) {
        Plane plane = new Plane();

        // PlaneDto'dan Plane entity'sine alanları dönüştürün
        plane.setId(planeDto.getId());
        plane.setModel(planeDto.getModel());
        plane.setManufacturing(planeDto.getManufacturing());
        plane.setPlaneType(planeDto.getPlaneType()); // Enum tipini doğru şekilde dönüştürdüğünüzden emin olun
        plane.setLongHaul(planeDto.isLongHaul());
        plane.setTotalSeats(planeDto.getTotalSeats());
        plane.setBusinessClassSeats(planeDto.getBusinessClassSeats());
        plane.setEconomyClassSeats(planeDto.getEconomyClassSeats());

        // Diğer gerekli alanlar da burada dönüştürülebilir

        return plane;
    }
    private Airport convertAirportDtoToEntity(AirportDto airportDto) {
        Airport airport = new Airport();
        airport.setId(airportDto.getId());
        airport.setName(airportDto.getName());
        airport.setCityName(airportDto.getCityName());
        airport.setCountryName(airportDto.getCountryName());
        return airport;
    }

    private Flight convertToEntity(FlightDto flightDto, Plane plane, Airport arrivelAirport, Airport departureairport) {
        Flight flight = new Flight();
        flight.setFlightNumber(flightDto.getFlightNumber());
        flight.setDepartureDateTime(flightDto.getDepartureDateTime());
        flight.setArrivalDateTime(flightDto.getArrivalDateTime());
        flight.setPlane(plane); // Dönüştürülen Plane nesnesini set edin
        flight.setDepartureAirport(departureairport);
        flight.setArrivalAirport(arrivelAirport);
        // Diğer alanları da benzer şekilde dönüştürün
        return flight;
    }

    @Override
    public List<FlightDto> getAllFlights() {
        return flightRepository.findAll()
                .stream()
                .map(flight -> modelMapper.map(flight, FlightDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public FlightDto getFlightDetails(Long flightId) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Uçuş bulunamadı: " + flightId));

        FlightDto flightDto = modelMapper.map(flight, FlightDto.class);

        // Uçağın tipini al ve PlaneFactory ile koltuk konfigürasyonunu oluştur/güncelle
        PlaneType planeType = flightDto.getPlane().getPlaneType();
        PlaneDto updatedPlaneDto = planeFactory.createPlaneConfiguration(planeType, flightDto.getPlane());
        flightDto.setPlane(updatedPlaneDto);

        return flightDto;
    }

    @Override
    public FlightDto getFlightById(Long id) throws Exception {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new Exception("Flight not found with ID: " + id));
        return modelMapper.map(flight, FlightDto.class);
    }

    @Override
    public String updateFlight(Long id, FlightDto flightDto) throws Exception {
        Flight existingFlight = flightRepository.findById(id)
                .orElseThrow(() -> new Exception("Flight not found with ID: " + id));

        modelMapper.map(flightDto, existingFlight);
        flightRepository.save(existingFlight);
        return "Flight updated successfully";
    }

    @Override
    public String deleteFlight(Long id) throws Exception {
        if (!flightRepository.existsById(id)) {
            throw new Exception("Flight not found with ID: " + id);
        }
        flightRepository.deleteById(id);
        return "Flight deleted successfully";
    }
}
