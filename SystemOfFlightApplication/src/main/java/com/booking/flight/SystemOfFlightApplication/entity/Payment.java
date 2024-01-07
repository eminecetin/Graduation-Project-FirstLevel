package com.booking.flight.SystemOfFlightApplication.entity;

import com.booking.flight.SystemOfFlightApplication.enums.PaymentStatus;
import com.booking.flight.SystemOfFlightApplication.enums.PaymentType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="payments")
@Data
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="ticked_no")
    private String ticketNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PaymentStatus status;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public Payment(Long id, String ticketNo,
                   PaymentStatus status,
                   LocalDateTime paymentDate,
                   PaymentType paymentType,
                   User user, Reservation reservation) {
        this.id = id;
        this.ticketNo = ticketNo;
        this.status = status;
        this.paymentDate = paymentDate;
        this.paymentType = paymentType;
        this.user = user;
        this.reservation = reservation;
    }
}