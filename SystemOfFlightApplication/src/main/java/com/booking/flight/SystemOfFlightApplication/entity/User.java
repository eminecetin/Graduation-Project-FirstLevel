package com.booking.flight.SystemOfFlightApplication.entity;

import com.booking.flight.SystemOfFlightApplication.enums.CustomerType;
import com.booking.flight.SystemOfFlightApplication.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Getter
    @Column(name="first_name")
    private String firstName;

    @Getter
    @Column(name="last_name")
    private String lastName;

    @Getter
    @Column(name="date_of_birth")
    private String dateOfBirth;

    @Getter
    @Column(name="identification_number")
    private long identificationNumber;

    @Getter
    @Column(name="gender")
    private String gender;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="address")
    private String address;

    @ManyToMany
    @JoinTable(
            name = "user_promotion",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "promotion_id")
    )
    private List<Promotion> promotions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payment> payments;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "customer_type")
    private CustomerType customerType;
}