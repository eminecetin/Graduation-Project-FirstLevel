package com.booking.flight.SystemOfFlightApplication.controller;

import com.booking.flight.SystemOfFlightApplication.dto.ReservationDto;
import com.booking.flight.SystemOfFlightApplication.enums.CustomerType;
import com.booking.flight.SystemOfFlightApplication.service.PromotionService;
import com.booking.flight.SystemOfFlightApplication.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000") // React uygulamasının çalıştığı adres
@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    // POST endpoint to calculate promotion and reservation
    // Rezervasyon bilgilerini alıp promosyon hesaplama endpoint'i
    @GetMapping("/apply-promotion/{reservationId}")
    public ResponseEntity<Double> getReservationPromotion(@PathVariable Long reservationId) {
        try {
            ReservationDto reservationDto = reservationService.getReservationById(reservationId);
            if (reservationDto != null) {
                double discountedPrice = reservationService.applyReservationPromotion(reservationDto);
                return ResponseEntity.ok(discountedPrice);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Hata yönetimi
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    // Endpoint to apply a reservation promotion
    @PostMapping("/apply-promotion")
    public ResponseEntity<Double> applyReservationPromotion(@RequestBody ReservationDto reservationDto) {
        try {
            double discountedPrice = reservationService.applyReservationPromotion(reservationDto);
            return ResponseEntity.ok(discountedPrice);
        } catch (Exception e) {
            // Hata yönetimi
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/calculate-promotion")
    public ResponseEntity<ReservationDto> calculatePromotion(@RequestBody ReservationDto reservationDto,
                                                             @RequestParam CustomerType customerType) {
        ReservationDto updatedReservation = reservationService.calculatePromotion(reservationDto, customerType);
        return ResponseEntity.ok(updatedReservation);
    }
    @PostMapping("/create")
    public ResponseEntity<String> createReservation(@RequestBody ReservationDto reservationDto) {
        reservationService.createReservation(reservationDto);
        return ResponseEntity.ok("Rezervasyon başarıyla oluşturuldu.");
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        List<ReservationDto> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable Long id) throws Exception {
        ReservationDto reservationDto = reservationService.getReservationById(id);
        return ResponseEntity.ok(reservationDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateReservation(@PathVariable Long id, @RequestBody ReservationDto reservationDto) throws Exception {
        String response = reservationService.updateReservation(id, reservationDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long id) throws Exception {
        String response = reservationService.deleteReservation(id);
        return ResponseEntity.ok(response);
    }
}