package com.booking.flight.SystemOfFlightApplication.repository;

import com.booking.flight.SystemOfFlightApplication.entity.AirportInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportInformationRepository extends JpaRepository<AirportInformation, Long> {
}
