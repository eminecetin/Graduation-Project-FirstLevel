package com.booking.flight.SystemOfFlightApplication.patterns.reservation.reservationStrategy;

import com.booking.flight.SystemOfFlightApplication.dto.ReservationDto;

public class EarlyBirdReservation implements ReservationStrategy {

    private static final double EARLY_BIRD_DISCOUNT_RATE = 0.15; // %15 indirim
    @Override
    public void makeReservation(ReservationDto reservationDto) {
        double originalPrice = reservationDto.getTicketPrice();
        double discountAmount = originalPrice * EARLY_BIRD_DISCOUNT_RATE;

        double discountedPrice = originalPrice - discountAmount;
        reservationDto.setTicketPrice(discountedPrice);

        // Erken rezervasyon ile ilgili diğer işlemler...
        // Örneğin, rezervasyon detaylarını kaydetmek, müşteriye bilgi vermek vs.
    }
}
