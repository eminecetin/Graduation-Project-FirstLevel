package com.booking.flight.SystemOfFlightApplication.patterns.promotion;

import com.booking.flight.SystemOfFlightApplication.enums.CustomerType;

public class PromotionStrategyFactory {
    public static PromotionStrategy getPromotionStrategy(CustomerType customerType) {
        switch (customerType) {
            case NEW_CUSTOMER:
                return new PercentageDiscountStrategy(10); // Yeni müşterilere %10 indirim
            case REGISTERED_CUSTOMER:
                return new FixedDiscountStrategy(5); // Kayıtlı müşterilere sabit 5 birim indirim
            case GUEST:
            default:
                return originalPrice -> originalPrice; // Misafirler için indirim yok
        }
    }
}