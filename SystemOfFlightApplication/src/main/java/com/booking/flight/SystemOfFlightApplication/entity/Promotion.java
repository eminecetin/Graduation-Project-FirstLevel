package com.booking.flight.SystemOfFlightApplication.entity;

import com.booking.flight.SystemOfFlightApplication.enums.PromotionType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="promotions")
@Data
@NoArgsConstructor
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "flight_number")
    private String flightNumber;

    @Column(name = "description")
    private String description;

    @Column(name = "valid_from")
    private Date validFrom;

    @Column(name = "valid_to")
    private Date validTo;

    @Column(name = "promotion_type")
    private PromotionType promotionType;

    @ManyToMany(mappedBy = "promotions")
    private List<User> users;

    public boolean isValidNow() {
        Date now = new Date();
        return now.after(validFrom) && now.before(validTo);
    }

    public Promotion(Long id, String name,
                     String flightNumber,
                     String description,
                     Date validFrom, Date validTo,
                     PromotionType promotionType, List<User> users) {
        this.id = id;
        this.name = name;
        this.flightNumber = flightNumber;
        this.description = description;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.promotionType = promotionType;
        this.users = users;
    }
}