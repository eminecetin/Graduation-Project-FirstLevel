package com.booking.flight.SystemOfFlightApplication.service.impl;

import com.booking.flight.SystemOfFlightApplication.dto.FlightDto;
import com.booking.flight.SystemOfFlightApplication.dto.PromotionDto;
import com.booking.flight.SystemOfFlightApplication.entity.Promotion;
import com.booking.flight.SystemOfFlightApplication.enums.CustomerType;
import com.booking.flight.SystemOfFlightApplication.patterns.promotion.PromotionStrategy;
import com.booking.flight.SystemOfFlightApplication.patterns.promotion.PromotionStrategyFactory;
import com.booking.flight.SystemOfFlightApplication.repository.PromotionRepository;
import com.booking.flight.SystemOfFlightApplication.service.PromotionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;
    private final ModelMapper modelMapper;

    public PromotionServiceImpl(PromotionRepository promotionRepository, ModelMapper modelMapper) {
        this.promotionRepository = promotionRepository;
        this.modelMapper = modelMapper;
    }
    // Bu metod, müşteri tipine göre uygun promosyon stratejisini uygular.
    @Override
    public double applyCustomerPromotion(double originalPrice, CustomerType customerType) {
        PromotionStrategy promotionStrategy = PromotionStrategyFactory.getPromotionStrategy(customerType);
        return promotionStrategy.calculateDiscount(originalPrice);
    }

    @Override
    public String createPromotion(PromotionDto promotionDto) {
        Promotion promotion = modelMapper.map(promotionDto, Promotion.class);
        promotionRepository.save(promotion);
        return "Promotion created successfully";
    }


    @Override
    public List<PromotionDto> getAllPromotions() {
        return promotionRepository.findAll()
                .stream()
                .map(promotion -> modelMapper.map(promotion, PromotionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PromotionDto getPromotionById(Long id) throws Exception {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new Exception("Promotion not found with ID: " + id));
        return modelMapper.map(promotion, PromotionDto.class);
    }

    @Override
    public String updatePromotion(Long id, PromotionDto promotionDto) throws Exception {
        Promotion existingPromotion = promotionRepository.findById(id)
                .orElseThrow(() -> new Exception("Promotion not found with ID: " + id));

        modelMapper.map(promotionDto, existingPromotion);
        promotionRepository.save(existingPromotion);
        return "Promotion updated successfully";
    }

    @Override
    public String deletePromotion(Long id) throws Exception {
        if (!promotionRepository.existsById(id)) {
            throw new Exception("Promotion not found with ID: " + id);
        }
        promotionRepository.deleteById(id);
        return "Promotion deleted successfully";
    }
}
