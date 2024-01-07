package com.booking.flight.SystemOfFlightApplication.controller;

import com.booking.flight.SystemOfFlightApplication.dto.FlightDto;
import com.booking.flight.SystemOfFlightApplication.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000") // React uygulamasının çalıştığı adres
@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }


    @GetMapping
    public List<FlightDto> getAllFlights() {
        return flightService.getAllFlights();
    }

    // Uçuş detaylarını getiren endpoint
    @GetMapping("/{flightId}")
    public ResponseEntity<FlightDto> getFlightDetails(@PathVariable Long flightId) {
        FlightDto flightDto = flightService.getFlightDetails(flightId);
        return ResponseEntity.ok(flightDto);
    }

  /*  @GetMapping("/{id}")
    public FlightDto getFlightById(@PathVariable Long id) throws Exception{
        return flightService.getFlightById(id);
    }*/

    @PostMapping("/create")
    public ResponseEntity<Long> createFlight(@RequestBody FlightDto flightDto)throws Exception{
        return ResponseEntity.ok(flightService.createFlight(flightDto));
    }


    @PutMapping("/update/{id}")
    public String updateFlight(@PathVariable Long id, @RequestBody FlightDto flightDto) throws Exception {
        return flightService.updateFlight(id,flightDto);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteFlight(@PathVariable Long id) throws Exception {
        return flightService.deleteFlight(id);
    }
}