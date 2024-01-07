package com.booking.flight.SystemOfFlightApplication.entity;

import com.booking.flight.SystemOfFlightApplication.enums.SeatStatus;
import com.booking.flight.SystemOfFlightApplication.patterns.seat.AvailableSeatService;
import com.booking.flight.SystemOfFlightApplication.patterns.seat.SeatState;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "seats")
@Data
@NoArgsConstructor // Lombok'tan gelen Boş Constructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_number")
    private String seatNumber;

    @Column(name = "seat_class")
    private String seatClass;

    @Column(name = "seat_width")
    private int seatWidth;

    @Column(name = "seat_pitch")
    private int seatPitch;

    @Column(name = "has_personal_entertainment")
    private boolean hasPersonalEntertainment;

    @Column(name = "has_lay_flat_seats")
    private boolean hasLayFlatSeats; // Yatay konuma gelebilen koltuklar için

    @Column(name = "is_booking")
    private String isBooking;

    @Column(name = "cabin_class")
    private String cabinClass;

    @Column(name = "seat_type")
    private String seatType;

    @Column(name = "price")
    private double price;

    @Column(name = "seat_features")
    private String seatFeatures;

    @ManyToOne
    @JoinColumn(name = "plane_id")
    private Plane plane;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private SeatStatus status;
    public boolean isBusinessClass() {
        return "business".equalsIgnoreCase(this.seatClass);
    }

    public Seat(Long id, String seatNumber, String seatClass,
                int seatWidth, int seatPitch,
                boolean hasPersonalEntertainment,
                boolean hasLayFlatSeats, String isBooking,
                String cabinClass, String seatType,
                double price, String seatFeatures,
                Plane plane,
                Reservation reservation,
                SeatStatus status) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.seatClass = seatClass;
        this.seatWidth = seatWidth;
        this.seatPitch = seatPitch;
        this.hasPersonalEntertainment = hasPersonalEntertainment;
        this.hasLayFlatSeats = hasLayFlatSeats;
        this.isBooking = isBooking;
        this.cabinClass = cabinClass;
        this.seatType = seatType;
        this.price = price;
        this.seatFeatures = seatFeatures;
        this.plane = plane;
        this.reservation = reservation;
        this.status = status;
    }

}
