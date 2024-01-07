package com.booking.flight.SystemOfFlightApplication.dto;

import com.booking.flight.SystemOfFlightApplication.enums.CustomerType;
import com.booking.flight.SystemOfFlightApplication.enums.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private long identificationNumber;
    private String gender;
    private String phoneNumber;
    private String address;
    private CustomerType customerType;
    private Role role;
    private List<Long> promotionId;
    private List<Long> paymentId;
    private List<Long> reservationId;
}


// private List<PromotionDto> promotions;
// private List<PaymentDto> payments;
// private List<ReservationDto> reservations; // Reservation entity'sini ReservationDto'ya dönüştür;
//