package com.booking.flight.SystemOfFlightApplication.controller;

import com.booking.flight.SystemOfFlightApplication.dto.AirlineDto;
import com.booking.flight.SystemOfFlightApplication.dto.PlaneDto;
import com.booking.flight.SystemOfFlightApplication.service.PlaneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000") // React uygulamasının çalıştığı adres
@RestController
@RequestMapping("/planes")
public class PlaneController {
    private final PlaneService planeService;

    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPlane(@RequestBody PlaneDto planeDto) {
        String response = planeService.createPlane(planeDto);
        return ResponseEntity.ok(response);
    }


}
