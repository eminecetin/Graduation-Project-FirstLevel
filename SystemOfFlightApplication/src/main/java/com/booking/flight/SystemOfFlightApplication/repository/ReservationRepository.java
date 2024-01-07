package com.booking.flight.SystemOfFlightApplication.repository;

import com.booking.flight.SystemOfFlightApplication.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
