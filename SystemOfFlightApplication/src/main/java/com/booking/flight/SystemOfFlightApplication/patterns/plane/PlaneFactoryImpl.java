package com.booking.flight.SystemOfFlightApplication.patterns.plane;

import com.booking.flight.SystemOfFlightApplication.dto.FlightDto;
import com.booking.flight.SystemOfFlightApplication.dto.PlaneDto;
import com.booking.flight.SystemOfFlightApplication.enums.PlaneType;
import com.booking.flight.SystemOfFlightApplication.service.impl.SeatServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PlaneFactoryImpl {

    private final SeatServiceImpl seatService;

    public PlaneFactoryImpl(SeatServiceImpl seatService) {
        this.seatService = seatService;
    }

    public PlaneDto createPlaneConfiguration(PlaneType planeType, PlaneDto existingPlaneDto) {
        switch (planeType) {
            case AIRBUS_A320:
                return new AirbusA320Service(seatService).setUpSeats(existingPlaneDto);
            case BOEING_737:
                return new Boeing737Service(seatService).setUpSeats(existingPlaneDto);
            case AIRBUS_A380:
                return new AirbusA380Service(seatService).setUpSeats(existingPlaneDto);
            case BOEING_747:
                return new Boeing747Service(seatService).setUpSeats(existingPlaneDto);
            default:
                throw new IllegalArgumentException("Invalid plane type");
        }
    }
}





/*
*
*  private final SeatServiceImpl seatService;

    public PlaneFactoryImpl(SeatServiceImpl seatService) {
        this.seatService = seatService;
    }


    public PlaneDto createPlane(FlightDto flightDto) {
        switch (flightDto.getFlightType()) {
            case DOMESTICFLIGHT:
                return new Boeing737Service(seatService).setUpSeats(flightDto.getPlane());
            case DOMESTICCONNECTING:
                return new AirbusA320Service(seatService).setUpSeats(flightDto.getPlane());
            case INTERNATIONALFLIGHT:
                return new Boeing747ervice(seatService).setUpSeats(flightDto.getPlane());
            case INTERNATIONALCONNECTING:
                return new AirbusA380Service(seatService).setUpSeats(flightDto.getPlane());
            default:
                throw new IllegalArgumentException("Invalid flight type");
        }
    }
*  */