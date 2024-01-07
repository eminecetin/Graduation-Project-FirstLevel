package com.booking.flight.SystemOfFlightApplication.entity;

import com.booking.flight.SystemOfFlightApplication.enums.PlaneType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="planes")
@Data
@NoArgsConstructor
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="model")
    private String model;

    @Column(name="manufacturing")
    private String manufacturing;

    @Column(name="long_haul")
    private boolean longHaul; // Uçuşun uzun mesafe olup olmadığını belirtir

    @Column(name="total_seats")
    private int totalSeats;

    @Column(name="business_class_seats")
    private int businessClassSeats;

    @Column(name="economy_class_seats")
    private int economyClassSeats;

    @Enumerated(EnumType.STRING)
    @Column(name="plane_type")
    private PlaneType planeType;

    @OneToMany(mappedBy = "plane", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Seat> seats;

    @OneToMany(mappedBy = "plane", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Flight> flights;

    public Plane(Long id, String model,
                 String manufacturing,
                 boolean longHaul,
                 int totalSeats,
                 int businessClassSeats,
                 int economyClassSeats,
                 PlaneType planeType,
                 List<Seat> seats, List<Flight> flights) {
        this.id = id;
        this.model = model;
        this.manufacturing = manufacturing;
        this.longHaul = longHaul;
        this.totalSeats = totalSeats;
        this.businessClassSeats = businessClassSeats;
        this.economyClassSeats = economyClassSeats;
        this.planeType = planeType;
        this.seats = seats;
        this.flights = flights;
    }
}
