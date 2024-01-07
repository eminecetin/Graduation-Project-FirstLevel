package com.booking.flight.SystemOfFlightApplication.repository;

import com.booking.flight.SystemOfFlightApplication.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
