package com.booking.flight.SystemOfFlightApplication.controller;

import com.booking.flight.SystemOfFlightApplication.dto.AirportDto;
import com.booking.flight.SystemOfFlightApplication.service.AirportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000") // React uygulamasının çalıştığı adres
@RestController
@RequestMapping("/airports")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAirport(@RequestBody AirportDto airportDto) {
        String response = airportService.createAirport(airportDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AirportDto>> getAllAirports() {
        List<AirportDto> airports = airportService.getAllAirports();
        return ResponseEntity.ok(airports);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportDto> getAirportById(@PathVariable Long id) throws Exception {
        AirportDto airportDto = airportService.getAirportById(id);
        return ResponseEntity.ok(airportDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAirport(@PathVariable Long id, @RequestBody AirportDto airportDto) throws Exception {
        String response = airportService.updateAirport(id, airportDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAirport(@PathVariable Long id) throws Exception {
        String response = airportService.deleteAirport(id);
        return ResponseEntity.ok(response);
    }
}
