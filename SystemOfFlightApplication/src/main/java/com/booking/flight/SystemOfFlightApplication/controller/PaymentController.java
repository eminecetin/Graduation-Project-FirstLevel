package com.booking.flight.SystemOfFlightApplication.controller;

import com.booking.flight.SystemOfFlightApplication.beans.PaymentRequest;
import com.booking.flight.SystemOfFlightApplication.dto.PaymentDto;
import com.booking.flight.SystemOfFlightApplication.patterns.payment.CreditCardPaymentService;
import com.booking.flight.SystemOfFlightApplication.patterns.payment.CreditCardPaymentStrategy;
import com.booking.flight.SystemOfFlightApplication.patterns.payment.PaymentStrategy;
import com.booking.flight.SystemOfFlightApplication.patterns.payment.PaypalPaymentStrategy;
import com.booking.flight.SystemOfFlightApplication.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import com.stripe.exception.StripeException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import com.booking.flight.SystemOfFlightApplication.beans.StripeChargeDto;
import com.booking.flight.SystemOfFlightApplication.beans.StripeTokenDto;
@CrossOrigin(origins = "http://localhost:3000") // React uygulamasının çalıştığı adres
@RestController
@RequestMapping("/payments")
@AllArgsConstructor
@Validated // Validasyon için
public class PaymentController {

    private final CreditCardPaymentService creditCardPaymentService;
    private final PaymentService paymentService;



    @PostMapping("/process")
    public ResponseEntity<String> processPayment(@RequestBody PaymentRequest paymentRequest) {
        try {
            paymentService.processPayment(paymentRequest.getAmount(), paymentRequest.getPaymentType());
            return ResponseEntity.ok("Payment of " + paymentRequest.getAmount() + " using " + paymentRequest.getPaymentType() + " was successful.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment processing failed.");
        }
    }

    @PostMapping("/processler")
    public ResponseEntity<String> processPayment(@RequestBody PaymentDto paymentDto) {
        PaymentStrategy strategy;

        // Ödeme yöntemine göre strateji belirleme
        if (paymentDto.getMethod().equals("creditCard")) {
            strategy = new CreditCardPaymentStrategy();
        } else if (paymentDto.getMethod().equals("paypal")) {
            strategy = new PaypalPaymentStrategy();
        } else {
            return ResponseEntity.badRequest().body("Geçersiz ödeme yöntemi.");
        }

        // Ödeme işlemini gerçekleştir
        String result = paymentService.processPayment(paymentDto.getAmount(), strategy.toString());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/card/token")
    public ResponseEntity<StripeTokenDto> createCardToken(@Validated @RequestBody StripeTokenDto model) {
        try {
            StripeTokenDto response = creditCardPaymentService.createCardToken(model);
            return ResponseEntity.ok(response);
        } catch (StripeException e) {
            // Burada daha spesifik hata işleme yapabilirsiniz
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/charge")
    public ResponseEntity<StripeChargeDto> charge(@Validated @RequestBody StripeChargeDto chargeRequest) {
        try {
            StripeChargeDto response = creditCardPaymentService.charge(chargeRequest);
            return ResponseEntity.ok(response);
        } catch (StripeException e) {
            // Burada daha spesifik hata işleme yapılacak
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    // Hata işleme için ek yöntemler burada tanımlanabilir
}
