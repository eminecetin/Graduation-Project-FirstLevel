package com.booking.flight.SystemOfFlightApplication.service;

import com.booking.flight.SystemOfFlightApplication.dto.FlightDto;
import com.booking.flight.SystemOfFlightApplication.dto.ReservationDto;
import com.booking.flight.SystemOfFlightApplication.enums.CustomerType;

import java.util.List;

public interface ReservationService {
    double applyReservationPromotion(ReservationDto reservationDto);
    ReservationDto calculatePromotion(ReservationDto reservationDto, CustomerType customerType);
    String createReservation(ReservationDto reservationDto);
    List<ReservationDto> getAllReservations();
    ReservationDto getReservationById(Long id) throws Exception;
    String updateReservation(Long id, ReservationDto reservationDto) throws Exception;
    String deleteReservation(Long id) throws Exception;
}
