package com.booking.flight.SystemOfFlightApplication.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="reservations")
@Data
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id; // Unique reservation identifier

    @Column(name = "reservation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reservationDate;

    @Column(name = "seat_information")
    private String seatInformation;

    @Column(name = "payment_information")
    private String paymentInformation;

    @Column(name = "reservation_state")
    private Boolean reservationState;

    @Column(name = "passenger_count")
    private long passengerCount;

    @Column(name = "passenger_name")
    private String passengerName;

    @Column(name = "seat_number")
    private String seatNumber;

    @Column(name = "flight_date")
    private LocalDate flightDate;

    @Column(name = "ticket_price")
    private double ticketPrice;

    @OneToOne
    @JoinColumn(name="ticket_id")
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Seat> seats;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payment> payments;

    public Reservation(Long id, Date reservationDate,
                       String seatInformation,
                       String paymentInformation,
                       Boolean reservationState,
                       long passengerCount,
                       Ticket ticket, User user,
                       Flight flight, List<Seat> seats,
                       List<Payment> payments) {
        this.id = id;
        this.reservationDate = reservationDate;
        this.seatInformation = seatInformation;
        this.paymentInformation = paymentInformation;
        this.reservationState = reservationState;
        this.passengerCount = passengerCount;
        this.ticket = ticket;
        this.user = user;
        this.flight = flight;
        this.seats = seats;
        this.payments = payments;
    }
}