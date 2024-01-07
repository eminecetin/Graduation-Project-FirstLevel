package com.booking.flight.SystemOfFlightApplication.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TicketDto {
    private Long id;
    private String ticketNo;
    private double ticketPrice;
    private Boolean ticketState;
    private String flightNumber;
    private ReservationDto reservation;
    private Long userId;
    private Long ticketId;
    private Long flightId;
    private String passengerName;
    private String seatNumber;
    private Double price;
    private Date flightDate;

    public static class Builder {
        private Long id;
        private String ticketNo;
        private double ticketPrice;
        private Boolean ticketState;
        private String flightNumber;
        private ReservationDto reservation;
        private Long userId;
        private Long ticketId;
        private Long flightId;
        private String passengerName;
        private String seatNumber;
        private Double price;
        private Date flightDate;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder ticketNo(String ticketNo) {
            this.ticketNo = ticketNo;
            return this;
        }

        // Diğer setter metodları...

        public Builder ticketPrice(double ticketPrice) {
            this.ticketPrice = ticketPrice;
            return this;
        }

        public Builder ticketState(Boolean ticketState) {
            this.ticketState = ticketState;
            return this;
        }

        public Builder flightNumber(String flightNumber) {
            this.flightNumber = flightNumber;
            return this;
        }

        public Builder reservation(ReservationDto reservation) {
            this.reservation = reservation;
            return this;
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder ticketId(Long ticketId) {
            this.ticketId = ticketId;
            return this;
        }

        public Builder flightId(Long flightId) {
            this.flightId = flightId;
            return this;
        }

        public Builder passengerName(String passengerName) {
            this.passengerName = passengerName;
            return this;
        }

        public Builder seatNumber(String seatNumber) {
            this.seatNumber = seatNumber;
            return this;
        }

        public Builder price(Double price) {
            this.price = price;
            return this;
        }

        public Builder flightDate(Date flightDate) {
            this.flightDate = flightDate;
            return this;
        }

        public TicketDto build() {
            TicketDto ticketDto = new TicketDto();
            ticketDto.id = this.id;
            ticketDto.ticketNo = this.ticketNo;
            ticketDto.ticketPrice = this.ticketPrice;
            ticketDto.ticketState = this.ticketState;
            ticketDto.flightNumber = this.flightNumber;
            ticketDto.reservation = this.reservation;
            ticketDto.userId = this.userId;
            ticketDto.ticketId = this.ticketId;
            ticketDto.flightId = this.flightId;
            ticketDto.passengerName = this.passengerName;
            ticketDto.seatNumber = this.seatNumber;
            ticketDto.price = this.price;
            ticketDto.flightDate = this.flightDate;
            return ticketDto;
        }
    }

}

// private ReservationDto reservation;
//  private UserDto user;