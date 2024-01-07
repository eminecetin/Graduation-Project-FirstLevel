package com.booking.flight.SystemOfFlightApplication.patterns.reservation;

public interface ReservationState {
    void handleRequest(ReservationContext context);

}
