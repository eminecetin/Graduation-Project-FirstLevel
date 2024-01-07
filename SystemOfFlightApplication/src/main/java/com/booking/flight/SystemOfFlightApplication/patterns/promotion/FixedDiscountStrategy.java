package com.booking.flight.SystemOfFlightApplication.patterns.promotion;

public class FixedDiscountStrategy implements PromotionStrategy {
    private final double discountAmount;

    public FixedDiscountStrategy(double discountAmount) {
        this.discountAmount = discountAmount;
    }
    @Override
    public double calculateDiscount(double originalPrice) {
        return Math.max(0, originalPrice - discountAmount);
    }
}
