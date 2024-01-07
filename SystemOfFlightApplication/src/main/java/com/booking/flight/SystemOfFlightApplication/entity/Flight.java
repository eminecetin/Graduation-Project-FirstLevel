package com.booking.flight.SystemOfFlightApplication.entity;


import com.booking.flight.SystemOfFlightApplication.enums.FlightType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="flights")
@Data
@NoArgsConstructor
public  class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "flight_number")
    private String flightNumber;


    @Column(name = "departure_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date departureDateTime;

    @Column(name = "arrival_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalDateTime;

    @Column(name = "ticket_price")
    private double ticketPrice;

    @Column(name = "available_seats")
    private int availableSeats;

    @Enumerated(EnumType.STRING)
    @Column(name = "flight_type")
    private FlightType flightType;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;

    @ManyToOne
    @JoinColumn(name = "plane_id")
    private Plane plane;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reservation> reservations;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;

    public Flight(Long id, String flightNumber,
                  Date departureDateTime,
                  Date arrivalDateTime,
                  double ticketPrice,
                  int availableSeats,
                  FlightType flightType,
                  Airport departureAirport,
                  Airport arrivalAirport,
                  Plane plane, List<Reservation> reservations,
                  Airline airline) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.ticketPrice = ticketPrice;
        this.availableSeats = availableSeats;
        this.flightType = flightType;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.plane = plane;
        this.reservations = reservations;
        this.airline = airline;
    }
}