package com.booking.flight.SystemOfFlightApplication.patterns.reservation.reservationStrategy;

import com.booking.flight.SystemOfFlightApplication.dto.ReservationDto;

public class StandardReservation implements ReservationStrategy {
    private static final double STANDARD_DISCOUNT_RATE = 0.05; // %5 indirim

    @Override
    public void makeReservation(ReservationDto reservationDto) {
        double originalPrice = reservationDto.getTicketPrice();
        double discountAmount = originalPrice * STANDARD_DISCOUNT_RATE;

        double discountedPrice = originalPrice - discountAmount;
        reservationDto.setTicketPrice(discountedPrice);

        // Rezervasyon ile ilgili diğer işlemler...
        // Örneğin, rezervasyon detaylarını kaydetmek, müşteriye bilgi vermek vs.
    }
}
