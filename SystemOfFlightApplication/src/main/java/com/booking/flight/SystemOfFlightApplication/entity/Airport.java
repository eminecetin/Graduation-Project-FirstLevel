package com.booking.flight.SystemOfFlightApplication.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="airports")
@Data
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="country_name")
    private String countryName;

    @Column(name="city_name")
    private String cityName;

    @Column(name="capacity")
    private String capacity;

    @Column(name="communication_information")
    private String communicationInformation;

    @OneToOne(mappedBy = "airport")
    private AirportInformation airportInformation;

    @OneToMany(mappedBy = "departureAirport")
    private List<Flight> departureFlights;

    @OneToMany(mappedBy = "arrivalAirport")
    private List<Flight> arrivalFlights;
}