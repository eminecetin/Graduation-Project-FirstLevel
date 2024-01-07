package com.booking.flight.SystemOfFlightApplication.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class ReservationDto {
    private Long id; // Unique reservation identifier
    private Date rezervationDate;
    private String seatInformation;
    private String passengerInformation;
    private String paymentInformation;
    private Boolean reservationState;
    private long passengerCount;
    private String passengerName;
    private String seatNumber;
    private LocalDate flightDate;
    private double ticketPrice; // Bilet fiyatı
    private Long flightId;
    private Long userId;
    private List<Long> paymentId; //rezervasyonda ödeme idsi gelse yeterli
    private Long ticketId;
    private List<Long> seatId;  //rezervasyonda koltuk idsi gelse yeterli

  /*  @Override
    public String toString() {
        return "ReservationDto{" +
                "id=" + id +
                ", rezervationDate=" + rezervationDate +
                ", passengerInformation='" + passengerInformation + '\'' +
                ", seatInformation='" + seatInformation + '\'' +
                ", paymentInformation='" + paymentInformation + '\'' +
                ", flightDate=" + flightDate +
                ", reservationState=" + reservationState +
                ", passengerCount=" + passengerCount +
                ", flightId=" + flightId +
                ", userId=" + userId +
                ", paymentId=" + paymentId +
                ", ticketId=" + ticketId +
                ", seatId=" + seatId +
                '}';
    }*/
}

// private List<SeatDto> seat;
// private TicketDto ticket;
// private UserDto user;
// private FlightDto flight;
// private List<PaymentDto> payments;