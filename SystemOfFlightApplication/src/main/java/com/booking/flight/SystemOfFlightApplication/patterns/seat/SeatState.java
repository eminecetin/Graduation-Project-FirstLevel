package com.booking.flight.SystemOfFlightApplication.patterns.seat;

import com.booking.flight.SystemOfFlightApplication.dto.SeatDto;
import com.booking.flight.SystemOfFlightApplication.entity.Seat;

public interface SeatState {
    void selectSeat(SeatDto seat);
    void reserveSeat(SeatDto seat);
    void freeSeat(SeatDto seat);
}
