package com.booking.flight.SystemOfFlightApplication.repository;

import com.booking.flight.SystemOfFlightApplication.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
