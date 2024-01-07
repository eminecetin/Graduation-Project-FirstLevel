package com.booking.flight.SystemOfFlightApplication.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="tickets")
@Data
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="ticket_no")
    private String ticketNo;

    @Column(name="ticket_price")
    private double ticketPrice;

    @Column(name="ticket_state")
    private Boolean ticketState;

    @Column(name="flight_number")
    private String flightNumber;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    private Long userId;
    private Long ticketId;
    private Long flightId;
    private String passengerName;
    private String seatNumber;
    private Double price;
    private Date flightDate;

    public Ticket(Long id, String ticketNo,
                  double ticketPrice,
                  Boolean ticketState,
                  String flightNumber,
                  Reservation reservation) {
        this.id = id;
        this.ticketNo = ticketNo;
        this.ticketPrice = ticketPrice;
        this.ticketState = ticketState;
        this.flightNumber = flightNumber;
        this.reservation = reservation;
    }
}