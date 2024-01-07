package com.booking.flight.SystemOfFlightApplication.patterns.reservation.reservationStrategy;

import com.booking.flight.SystemOfFlightApplication.dto.ReservationDto;

public interface ReservationStrategy {
    void makeReservation(ReservationDto reservationDto);
}
