package com.booking.flight.SystemOfFlightApplication.beans;


import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class StripeTokenDto {
    private String cardNumber;
    private String expirationMonth;
    private String expirationYear;
    private String message;
    private String cvv;
    private String stripeToken;
    private String userName;
    private boolean success;
    private String chargeId;
    private Map<String, Object> additionalInfo=new HashMap<>();


 /*   public static boolean isValidCreditCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.trim().isEmpty()) {
            return false;
        }
        //regex silme
        String cleanedCardNumber = cardNumber.replaceAll("\\D", "");

        // Luhn algoritması
        int sum = 0;
        boolean alternate = false;
        for (int i = cleanedCardNumber.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(cleanedCardNumber.charAt(i));
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit = digit - 9;
                }
            }
            sum += digit;
            alternate = !alternate;
        }

        return (sum % 10 == 0);
    }
    public static boolean isValidExpirationDate(String expirationDate) {
        // Check if the expiration date is in the correct format (MM/YYYY)
        if (!expirationDate.matches("\\d{2}/\\d{4}")) {
            return false;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
        dateFormat.setLenient(false);

        try {
            Date parsedDate = dateFormat.parse(expirationDate);

            Calendar cal = Calendar.getInstance();
            cal.setTime(parsedDate);

            Calendar now = Calendar.getInstance();
            now.set(Calendar.DAY_OF_MONTH, 1);

            return cal.after(now);
        } catch (ParseException e) {
            return false;
        }
    }
    public static boolean isValidCvv(String cvv) {
        if (!cvv.matches("\\d{3}")) {
            return false;
        }
        return true;
    }*/

}
