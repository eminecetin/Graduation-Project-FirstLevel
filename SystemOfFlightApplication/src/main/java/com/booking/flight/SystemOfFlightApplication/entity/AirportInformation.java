package com.booking.flight.SystemOfFlightApplication.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="airport_service_informations")
@Data
@NoArgsConstructor
public class AirportInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;


    @ElementCollection
    @CollectionTable(name = "restaurant_names", joinColumns = @JoinColumn(name = "service_information_id"))
    @Column(name = "restaurant_name")
    private List<String> restaurantNames;

    @ElementCollection
    @CollectionTable(name = "store_names", joinColumns = @JoinColumn(name = "service_information_id"))
    @Column(name = "store_name")
    private List<String> storeNames;

    @Column(name = "car_park")
    private String carPark;

    @Column(name = "luggage_service")
    private String luggageService;

    @OneToOne
    @JoinColumn(name = "airport_id")
    private Airport airport;

    public AirportInformation(Long id, List<String> restaurantNames,
                              List<String> storeNames, String carPark,
                              String luggageService, Airport airport) {
        this.id = id;
        this.restaurantNames = restaurantNames;
        this.storeNames = storeNames;
        this.carPark = carPark;
        this.luggageService = luggageService;
        this.airport = airport;
    }
}