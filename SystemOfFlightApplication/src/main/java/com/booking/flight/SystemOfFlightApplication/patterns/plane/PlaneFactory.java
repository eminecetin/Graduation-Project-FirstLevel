package com.booking.flight.SystemOfFlightApplication.patterns.plane;

import com.booking.flight.SystemOfFlightApplication.dto.PlaneDto;

public interface PlaneFactory {
    PlaneDto setUpSeats(PlaneDto planeDto);
}
