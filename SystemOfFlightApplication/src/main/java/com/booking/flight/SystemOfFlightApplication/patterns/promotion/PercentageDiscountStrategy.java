package com.booking.flight.SystemOfFlightApplication.patterns.promotion;

public class PercentageDiscountStrategy implements PromotionStrategy {
    private final double percentage;

    public PercentageDiscountStrategy(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public double calculateDiscount(double originalPrice) {
        return originalPrice * (percentage / 100.0);
    }
}
