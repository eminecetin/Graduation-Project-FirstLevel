package com.booking.flight.SystemOfFlightApplication.patterns.payment;

import com.booking.flight.SystemOfFlightApplication.beans.StripeChargeDto;
import com.booking.flight.SystemOfFlightApplication.beans.StripeTokenDto;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class CreditCardPaymentService {

    @Value("${api.stripe.key}") // Dikkat: Doğru property anahtarını kullanın
    private String stripeApiKey;

    @PostConstruct
    public void init(){
        Stripe.apiKey = stripeApiKey;
    }

    public StripeTokenDto createCardToken(StripeTokenDto model) throws StripeException {
        try {
            Map<String, Object> card = new HashMap<>();
            card.put("number", model.getCardNumber());
            card.put("exp_month", Integer.parseInt(model.getExpirationMonth())); // String'i Integer'a çevir
            card.put("exp_year", Integer.parseInt(model.getExpirationYear())); // String'i Integer'a çevir
            card.put("cvc", model.getCvv());
            Map<String, Object> params = new HashMap<>();
            params.put("card", card);
            Token token = Token.create(params);
            if (token != null && token.getId() != null) {
                model.setStripeToken(token.getId());
                model.setSuccess(true);
            }
            return model;
        } catch (StripeException e) {
            log.error("StripeService (createCardToken)", e);
            throw new RuntimeException("Stripe hatası: " + e.getMessage(), e);
        }
    }

    public StripeChargeDto charge(StripeChargeDto chargeRequest) throws StripeException {
        try {
            chargeRequest.setSuccess(false);
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", (int) (chargeRequest.getAmount() * 100));
            chargeParams.put("currency", "usd");
            chargeParams.put("source", chargeRequest.getStripeToken());
            chargeParams.put("description", "Payment for id " + chargeRequest.getAdditionalInfo().getOrDefault("ID_TAG", ""));
            Map<String, Object> metaData = new HashMap<>();
            metaData.put("id",chargeRequest.getChargeId());
            metaData.putAll(chargeRequest.getAdditionalInfo()); // Sadece gerekli bilgileri ekleyin
            chargeParams.put("metadata", metaData);
            Charge charge = Charge.create(chargeParams);
            chargeRequest.setMessage(charge.getOutcome().getSellerMessage());

            if (charge.getPaid()) {
                chargeRequest.setChargeId(charge.getId());
                chargeRequest.setSuccess(true);
            }
            return chargeRequest;
        } catch (StripeException e) {
            log.error("StripeService (charge)", e);
            throw new RuntimeException("Stripe hatası: " + e.getMessage(), e);
        }
    }
}
