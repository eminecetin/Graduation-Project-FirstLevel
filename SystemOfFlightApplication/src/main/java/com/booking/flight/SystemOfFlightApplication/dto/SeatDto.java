package com.booking.flight.SystemOfFlightApplication.dto;

import com.booking.flight.SystemOfFlightApplication.enums.SeatStatus;
import com.booking.flight.SystemOfFlightApplication.patterns.seat.SeatState;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
public class SeatDto {
    private Long id;
    private String seatNumber;
    private boolean isBooked;
    private String cabinClass;
    private String seatType;
    private double price;
    private String flightNumber;
    private String seatFeatures;
    private Long planeId;
    private Long reservationId;
    private String seatClass;
    private boolean hasLayFlatSeats;
    private int seatWidth;
    private int seatPitch;
    private boolean hasPersonalEntertainment;

    private SeatStatus status; // Enum tipinde
    public boolean isBusinessClass() {
        return "business".equalsIgnoreCase(this.seatClass);
    }
}

//  private FlightDto flight;
//  private ReservationDto reservation;
// @Transient  // Veritabanına kaydedilmeyecek, runtime yönetimi
 // private SeatState state;