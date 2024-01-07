package com.booking.flight.SystemOfFlightApplication.controller;
import com.booking.flight.SystemOfFlightApplication.dto.SeatDto;
import com.booking.flight.SystemOfFlightApplication.enums.SeatStatus;
import com.booking.flight.SystemOfFlightApplication.service.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000") // React uygulamasının çalıştığı adres
@RestController
@RequestMapping("/seats")
public class SeatController {
    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createSeat(@RequestBody SeatDto seatDto) {
        String response = String.valueOf(seatService.createSeat(seatDto));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SeatDto>> getAllSeats() {
        List<SeatDto> seats = seatService.getAllSeats();
        return ResponseEntity.ok(seats);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatDto> getSeatById(@PathVariable Long id) throws Exception {
        SeatDto seatDto = seatService.getSeatById(id);
        return ResponseEntity.ok(seatDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateSeat(@PathVariable Long id, @RequestBody SeatDto seatDto) throws Exception {
        String response = seatService.updateSeat(id, seatDto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{seatId}/status/{newStatus}")
    public ResponseEntity<String> updateSeatStatus(@PathVariable Long seatId, @PathVariable SeatStatus newStatus) {
        seatService.updateSeatStatus(seatId, newStatus);
        return ResponseEntity.ok("Seat status updated successfully.");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSeat(@PathVariable Long id) throws Exception {
        String response = seatService.deleteSeat(id);
        return ResponseEntity.ok(response);
    }
}