package com.booking.flight.SystemOfFlightApplication.controller;

import com.booking.flight.SystemOfFlightApplication.dto.AirportInformationDto;
import com.booking.flight.SystemOfFlightApplication.service.AirportInformationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000") // React uygulamasının çalıştığı adres
@RestController
@RequestMapping("/airport-info")
public class AirportInformationController {
    private final AirportInformationService airportInformationService;

    public AirportInformationController(AirportInformationService airportInformationService) {
        this.airportInformationService = airportInformationService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAirportInformation(@RequestBody AirportInformationDto airportInformationDto) {
        String response = airportInformationService.createAirportInformation(airportInformationDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AirportInformationDto>> getAllAirportInformation() {
        List<AirportInformationDto> airportInformations = airportInformationService.getAllAirportInformation();
        return ResponseEntity.ok(airportInformations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportInformationDto> getAirportInformationById(@PathVariable Long id) throws Exception {
        AirportInformationDto airportInformationDto = airportInformationService.getAirportInformationById(id);
        return ResponseEntity.ok(airportInformationDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAirportInformation(@PathVariable Long id, @RequestBody AirportInformationDto airportInformationDto) throws Exception {
        String response = airportInformationService.updateAirportInformation(id, airportInformationDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAirportInformation(@PathVariable Long id) throws Exception {
        String response = airportInformationService.deleteAirportInformation(id);
        return ResponseEntity.ok(response);
    }
}