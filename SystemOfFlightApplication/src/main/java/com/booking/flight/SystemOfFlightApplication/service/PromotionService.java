package com.booking.flight.SystemOfFlightApplication.service;

import com.booking.flight.SystemOfFlightApplication.dto.FlightDto;
import com.booking.flight.SystemOfFlightApplication.dto.PromotionDto;
import com.booking.flight.SystemOfFlightApplication.enums.CustomerType;

import java.util.List;

public interface PromotionService {
    double applyCustomerPromotion(double originalPrice, CustomerType customerType);
    String createPromotion(PromotionDto promotionDto);
    List<PromotionDto> getAllPromotions();
    PromotionDto getPromotionById(Long id) throws Exception;
    String updatePromotion(Long id, PromotionDto promotionDto) throws Exception;
    String deletePromotion(Long id) throws Exception;
}
