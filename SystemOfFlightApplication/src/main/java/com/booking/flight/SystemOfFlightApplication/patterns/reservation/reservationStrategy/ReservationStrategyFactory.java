package com.booking.flight.SystemOfFlightApplication.patterns.reservation.reservationStrategy;

import com.booking.flight.SystemOfFlightApplication.dto.ReservationDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
@Service
public class ReservationStrategyFactory {
    public static ReservationStrategy getStrategy(ReservationDto reservationDTO) {
        LocalDate today = LocalDate.now();
        long daysBetween = ChronoUnit.DAYS.between(today, reservationDTO.getFlightDate());

        if (daysBetween > 30) {
            return new EarlyBirdReservation();
        } else if (daysBetween <= 30 && daysBetween > 7) {
            return new StandardReservation();
        } else {
            return new LastMinuteReservation();
        }
    }
}
