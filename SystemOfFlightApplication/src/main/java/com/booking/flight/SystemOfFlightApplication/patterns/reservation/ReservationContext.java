package com.booking.flight.SystemOfFlightApplication.patterns.reservation;

public class ReservationContext {
    private ReservationState state;

    public ReservationContext(ReservationState state) {
        this.state = state;
    }

    public void setState(ReservationState state) {
        this.state = state;
    }

    public void applyState() {
        state.handleRequest(this);
    }

    // Rezervasyonu iptal etmek için yeni bir metod
    public void cancelReservation() {
        setState(new CancelledState());
        applyState();
    }
}
