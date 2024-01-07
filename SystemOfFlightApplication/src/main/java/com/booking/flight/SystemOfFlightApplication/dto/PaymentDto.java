package com.booking.flight.SystemOfFlightApplication.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PaymentDto {
    private Long id;
    private String ticketNo;
    private boolean success;
    private int amount;
    private String currency;
    private String paymentMethod;
    private Long userId; // User entity'sini UserDto'ya dönüştürmek için UserMapper kullanılmalı.
    private Long reservationId;
    private String method; // "creditCard" veya "paypal"
}