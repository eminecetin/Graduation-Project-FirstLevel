package com.booking.flight.SystemOfFlightApplication.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="airlines")
@Data
@NoArgsConstructor
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="airline_code")
    private String airlineCode;

    @OneToMany(mappedBy = "airline")
    private List<Flight> flights;

    public Airline(Long id, String name,
                   String airlineCode,
                   List<Flight> flights) {
        this.id = id;
        this.name = name;
        this.airlineCode = airlineCode;
        this.flights = flights;
    }
}