package com.booking.flight.SystemOfFlightApplication.dto;

import com.booking.flight.SystemOfFlightApplication.entity.Airport;
import com.booking.flight.SystemOfFlightApplication.enums.FlightType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FlightDto {
    private Long id;
    private String flightNumber;
    private Date departureDateTime;
    private Date arrivalDateTime;
    private double ticketPrice;
    private int availableSeats;
    private FlightType flightType;
    private AirportDto departureAirport; // Kalkış havaalanı bilgileri
    private AirportDto arrivalAirport;   // Varış havaalanı bilgileri
    private PlaneDto plane;  //uçuşlar görüntülenince uçak bilgileri de görüntülenebilir.
    private Long airlineId;
    private List<Long> reservationId;
}
//  private AirportDto departureAirport;
//  private AirportDto arrivalAirport;
//  private PlaneDto plane;
//  private List<Reservation> reservations;
//  private AirlineDto airline;