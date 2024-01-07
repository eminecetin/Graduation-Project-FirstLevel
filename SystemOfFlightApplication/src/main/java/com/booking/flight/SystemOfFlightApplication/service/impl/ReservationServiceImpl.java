package com.booking.flight.SystemOfFlightApplication.service.impl;

import com.booking.flight.SystemOfFlightApplication.dto.FlightDto;
import com.booking.flight.SystemOfFlightApplication.dto.ReservationDto;
import com.booking.flight.SystemOfFlightApplication.entity.Reservation;
import com.booking.flight.SystemOfFlightApplication.enums.CustomerType;
import com.booking.flight.SystemOfFlightApplication.patterns.promotion.PromotionStrategy;
import com.booking.flight.SystemOfFlightApplication.patterns.promotion.PromotionStrategyFactory;
import com.booking.flight.SystemOfFlightApplication.patterns.reservation.reservationStrategy.ReservationStrategy;
import com.booking.flight.SystemOfFlightApplication.patterns.reservation.reservationStrategy.ReservationStrategyFactory;
import com.booking.flight.SystemOfFlightApplication.repository.ReservationRepository;
import com.booking.flight.SystemOfFlightApplication.service.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ModelMapper modelMapper;

    public ReservationServiceImpl(ReservationRepository reservationRepository, ModelMapper modelMapper) {
        this.reservationRepository = reservationRepository;
        this.modelMapper = modelMapper;
    }
    // Bu metod, rezervasyon tarihine göre uygun rezervasyon stratejisini uygular.
    @Override
    public double applyReservationPromotion(ReservationDto reservationDto) {
        ReservationStrategy reservationStrategy = ReservationStrategyFactory.getStrategy(reservationDto);
        reservationStrategy.makeReservation(reservationDto);
        return reservationDto.getTicketPrice();
    }

    @Override
    public ReservationDto calculatePromotion(ReservationDto reservationDto, CustomerType customerType) {
        // Müşteri tipine göre promosyon uygula
        PromotionStrategy promotionStrategy = PromotionStrategyFactory.getPromotionStrategy(customerType);
        double priceAfterCustomerPromotion = promotionStrategy.calculateDiscount(reservationDto.getTicketPrice());

        // Rezervasyon stratejisine göre ek indirimleri uygula
        ReservationStrategy reservationStrategy = ReservationStrategyFactory.getStrategy(reservationDto);
        reservationStrategy.makeReservation(reservationDto);

        // Müşteri tipine göre uygulanan indirim sonrası fiyatı güncelle
        reservationDto.setTicketPrice(priceAfterCustomerPromotion);

        return reservationDto;
    }
    @Override
    public String createReservation(ReservationDto reservationDto) {
        Reservation reservation = modelMapper.map(reservationDto, Reservation.class);
        reservationRepository.save(reservation);
        return "Reservation created successfully";
    }

    @Override
    public List<ReservationDto> getAllReservations() {
        return reservationRepository.findAll()
                .stream()
                .map(reservation -> modelMapper.map(reservation, ReservationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDto getReservationById(Long id) throws Exception {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new Exception("Reservation not found with ID: " + id));
        return modelMapper.map(reservation, ReservationDto.class);
    }

    @Override
    public String updateReservation(Long id, ReservationDto reservationDto) throws Exception {
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new Exception("Reservation not found with ID: " + id));

        modelMapper.map(reservationDto, existingReservation);
        reservationRepository.save(existingReservation);
        return "Reservation updated successfully";
    }

    @Override
    public String deleteReservation(Long id) throws Exception {
        if (!reservationRepository.existsById(id)) {
            throw new Exception("Reservation not found with ID: " + id);
        }
        reservationRepository.deleteById(id);
        return "Reservation deleted successfully";
    }
}
