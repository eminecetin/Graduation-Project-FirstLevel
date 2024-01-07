package com.booking.flight.SystemOfFlightApplication.controller;

import com.booking.flight.SystemOfFlightApplication.dto.ReservationDto;
import com.booking.flight.SystemOfFlightApplication.dto.TicketDto;
import com.booking.flight.SystemOfFlightApplication.service.ReservationService;
import com.booking.flight.SystemOfFlightApplication.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000") // React uygulamasının çalıştığı adres
@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;
    private final ReservationService reservationService;

    public TicketController(TicketService ticketService, ReservationService reservationService) {
        this.ticketService = ticketService;
        this.reservationService = reservationService;
    }
    @PostMapping("/create")
    public ResponseEntity<?> createTicketWithPromotion(@RequestBody ReservationDto reservationDto) {
        try {
            // Promosyon uygulanmış fiyatı hesapla
            double discountedPrice = reservationService.applyReservationPromotion(reservationDto);

            // Bu fiyatla bilet oluştur
            TicketDto createdTicket = ticketService.createTicket(reservationDto, discountedPrice);

            // Oluşturulan bilet bilgilerini geri dön
            return ResponseEntity.ok(createdTicket);
        } catch (Exception e) {
            // Hata durumunda uygun hata mesajı gönder
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ticket creation failed: " + e.getMessage());
        }
    }

    @PostMapping("/createee")
    public ResponseEntity<String> createTicket(@RequestBody TicketDto ticketDto) {
        String response = ticketService.createTicket(ticketDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketDto> getTicket(@PathVariable Long ticketId) throws Exception {
        TicketDto ticket = ticketService.getTicketById(ticketId);
        if (ticket != null) {
            return ResponseEntity.ok(ticket);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<TicketDto>> getAllTickets() {
        List<TicketDto> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable Long id) throws Exception {
        TicketDto ticketDto = ticketService.getTicketById(id);
        return ResponseEntity.ok(ticketDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTicket(@PathVariable Long id, @RequestBody TicketDto ticketDto) throws Exception {
        String response = ticketService.updateTicket(id, ticketDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable Long id) throws Exception {
        String response = ticketService.deleteTicket(id);
        return ResponseEntity.ok(response);
    }
}