package com.booking.flight.SystemOfFlightApplication.patterns.seat;

import com.booking.flight.SystemOfFlightApplication.dto.SeatDto;
import com.booking.flight.SystemOfFlightApplication.enums.SeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
@Service
public class SeatStateImpl {

    private final AvailableSeatService availableSeatService;
    private final SelectedSeatService selectedSeatService;
    private final ReservedSeatService reservedSeatService;

    @Autowired
    public SeatStateImpl(@Lazy AvailableSeatService availableSeatService,
                         @Lazy SelectedSeatService selectedSeatService,
                         @Lazy ReservedSeatService reservedSeatService) {
        this.availableSeatService = availableSeatService;
        this.selectedSeatService = selectedSeatService;
        this.reservedSeatService = reservedSeatService;
    }

    public void changeSeatState(SeatDto seatDto, SeatStatus newStatus) {
        switch (newStatus) {
            case AVAILABLE:
                availableSeatService.freeSeat(seatDto);
                break;
            case SELECTED:
                availableSeatService.selectSeat(seatDto);
                break;
            case RESERVED:
                selectedSeatService.reserveSeat(seatDto);
                break;
            default:
                throw new IllegalStateException("Invalid seat status");
        }
    }
}
