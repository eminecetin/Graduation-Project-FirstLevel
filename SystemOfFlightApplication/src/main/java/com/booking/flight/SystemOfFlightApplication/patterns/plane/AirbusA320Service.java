package com.booking.flight.SystemOfFlightApplication.patterns.plane;

import com.booking.flight.SystemOfFlightApplication.dto.PlaneDto;
import com.booking.flight.SystemOfFlightApplication.dto.SeatDto;
import com.booking.flight.SystemOfFlightApplication.service.impl.SeatServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class AirbusA320Service implements PlaneFactory{
    private final SeatServiceImpl seatService;

    public AirbusA320Service(SeatServiceImpl seatService) {
        this.seatService = seatService;
    }

    @Override
    public PlaneDto setUpSeats(PlaneDto planeDto) {
        int totalSeats = 160;
        int businessClassSeats = 30;
        int economyClassSeats = totalSeats - businessClassSeats;

        planeDto.setTotalSeats(totalSeats);
        planeDto.setBusinessClassSeats(businessClassSeats);
        planeDto.setEconomyClassSeats(economyClassSeats);

        List<SeatDto> seats = new ArrayList<>();

        for (int i = 0; i < businessClassSeats; i++) {
            SeatDto seat = new SeatDto();
            configureBusinessClassSeat(seat);
            seats.add(seat);
           // seatService.save(seat); // Veritabanına kaydet
        }

        for (int i = 0; i < economyClassSeats; i++) {
            SeatDto seat = new SeatDto();
            configureEconomyClassSeat(seat);
            seats.add(seat);
           // seatService.save(seat); // Veritabanına kaydet
        }

        // Tüm koltukları topluca kaydet
        seatService.saveAll(seats);

        planeDto.setSeats(seats);

        return planeDto;
    }

    private void configureBusinessClassSeat(SeatDto seat) {
        seat.setSeatClass("business");
        seat.setSeatWidth(20); // Biraz daha geniş koltuklar
        seat.setSeatPitch(39); // Biraz daha geniş aralık
        seat.setHasPersonalEntertainment(true); // Aktarmalı uçuşlarda kişisel eğlence sistemi olabilir
        seat.setHasLayFlatSeats(false);
    }

    private void configureEconomyClassSeat(SeatDto seat) {
        seat.setSeatClass("economy");
        seat.setSeatWidth(17);
        seat.setSeatPitch(31); // Standart aralık
        seat.setHasPersonalEntertainment(false);
    }
}
