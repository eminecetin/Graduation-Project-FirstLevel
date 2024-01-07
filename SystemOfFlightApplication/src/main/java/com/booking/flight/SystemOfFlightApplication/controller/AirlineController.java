package com.booking.flight.SystemOfFlightApplication.controller;

import com.booking.flight.SystemOfFlightApplication.dto.AirlineDto;
import com.booking.flight.SystemOfFlightApplication.service.AirlineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000") // React uygulamasının çalıştığı adres
@RestController
@RequestMapping("/airlines")
public class AirlineController {

    private final AirlineService airlineService;

    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<AirlineDto> getById(@PathVariable Long id) throws Exception {
        AirlineDto airlineDto = airlineService.getAirlineById(id);
        return ResponseEntity.ok(airlineDto);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAirline(@RequestBody AirlineDto airlineDto) {
        String response = airlineService.createAirline(airlineDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AirlineDto>> getAllAirlines() {
        List<AirlineDto> airlines = airlineService.getAllAirlines();
        return ResponseEntity.ok(airlines);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAirline(@PathVariable Long id, @RequestBody AirlineDto airlineDto) throws Exception {
        String response = airlineService.updateAirline(id, airlineDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAirline(@PathVariable Long id) throws Exception {
        String response = airlineService.deleteAirline(id);
        return ResponseEntity.ok(response);
    }
}
