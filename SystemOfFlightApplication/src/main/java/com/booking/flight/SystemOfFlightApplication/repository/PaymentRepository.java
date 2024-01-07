package com.booking.flight.SystemOfFlightApplication.repository;

import com.booking.flight.SystemOfFlightApplication.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
