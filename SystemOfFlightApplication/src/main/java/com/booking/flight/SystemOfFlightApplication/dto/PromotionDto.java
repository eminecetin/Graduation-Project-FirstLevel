package com.booking.flight.SystemOfFlightApplication.dto;

import com.booking.flight.SystemOfFlightApplication.enums.PromotionType;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PromotionDto {
    private Long id;
    private long flightNumber;
    private int discountRate;
    private PromotionType promotionType;
    private Date validFrom;
    private Date validTo;
    private String description;
    private List<Long> userId;
}

//   private List<UserDto> users;
