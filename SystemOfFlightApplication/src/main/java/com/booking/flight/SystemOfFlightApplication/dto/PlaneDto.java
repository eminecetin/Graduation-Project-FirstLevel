package com.booking.flight.SystemOfFlightApplication.dto;

import com.booking.flight.SystemOfFlightApplication.enums.PlaneType;
import lombok.Data;

import java.util.List;

@Data
public class PlaneDto {
    private Long id;
    private String model;
    private String manufacturing;
    private PlaneType planeType;
    private List<Long> flightId;
    private List<SeatDto> seats; //uçak görüntülenince koltuk bilgileri de gelsin mi
    private boolean longHaul; // Uçuşun uzun mesafe olup olmadığını belirtir
    private int totalSeats;
    private int businessClassSeats;
    private int economyClassSeats;
    private int premiumEconomySeats;
}

// private List<SeatDto> seats;
// private List<FlightDto> flights;