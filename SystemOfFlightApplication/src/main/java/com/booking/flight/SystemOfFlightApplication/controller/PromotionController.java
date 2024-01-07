package com.booking.flight.SystemOfFlightApplication.controller;
import com.booking.flight.SystemOfFlightApplication.dto.PromotionDto;
import com.booking.flight.SystemOfFlightApplication.enums.CustomerType;
import com.booking.flight.SystemOfFlightApplication.service.PromotionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000") // React uygulamasının çalıştığı adres
@RestController
@RequestMapping("/promotions")
public class PromotionController {
    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    // Endpoint to apply a customer promotion
    @GetMapping("/apply")
    public ResponseEntity<Double> applyCustomerPromotion(@RequestParam double originalPrice, @RequestParam String customerTypeString) {
        try {
            CustomerType customerType = CustomerType.valueOf(customerTypeString.toUpperCase());
            double discountedPrice = promotionService.applyCustomerPromotion(originalPrice, customerType);
            return ResponseEntity.ok(discountedPrice);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<String> createPromotion(@RequestBody PromotionDto promotionDto) {
        String response = promotionService.createPromotion(promotionDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PromotionDto>> getAllPromotions() {
        List<PromotionDto> promotions = promotionService.getAllPromotions();
        return ResponseEntity.ok(promotions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromotionDto> getPromotionById(@PathVariable Long id) throws Exception {
        PromotionDto promotionDto = promotionService.getPromotionById(id);
        return ResponseEntity.ok(promotionDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePromotion(@PathVariable Long id, @RequestBody PromotionDto promotionDto) throws Exception {
        String response = promotionService.updatePromotion(id, promotionDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePromotion(@PathVariable Long id) throws Exception {
        String response = promotionService.deletePromotion(id);
        return ResponseEntity.ok(response);
    }
}