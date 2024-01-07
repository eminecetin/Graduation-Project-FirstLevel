package com.booking.flight.SystemOfFlightApplication.patterns.plane;

import com.booking.flight.SystemOfFlightApplication.dto.PlaneDto;
import com.booking.flight.SystemOfFlightApplication.dto.SeatDto;
import com.booking.flight.SystemOfFlightApplication.service.impl.SeatServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Boeing747Service implements PlaneFactory{
    private final SeatServiceImpl seatService;

    public Boeing747Service(SeatServiceImpl seatService) {
        this.seatService = seatService;
    }

    @Override
    public PlaneDto setUpSeats(PlaneDto planeDto) {
        int totalSeats = 300;
        int businessClassSeats = 60;
        int premiumEconomySeats = 60;
        int economyClassSeats = totalSeats - businessClassSeats - premiumEconomySeats;

        planeDto.setTotalSeats(totalSeats);
        planeDto.setBusinessClassSeats(businessClassSeats);
        planeDto.setPremiumEconomySeats(premiumEconomySeats);
        planeDto.setEconomyClassSeats(economyClassSeats);

        List<SeatDto> seats = new ArrayList<>();

        for (int i = 0; i < businessClassSeats; i++) {
            SeatDto seat = new SeatDto();
            configureBusinessClassSeat(seat);
            seats.add(seat);
           // seatService.save(seat); // Veritabanına kaydet
        }

        for (int i = 0; i < premiumEconomySeats; i++) {
            SeatDto seat = new SeatDto();
            configurePremiumEconomyClassSeat(seat);
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
        seat.setSeatWidth(22); // Geniş koltuklar
        seat.setSeatPitch(60); // Geniş aralık
        seat.setHasPersonalEntertainment(true);
        seat.setHasLayFlatSeats(true); // Yatak olabilen koltuklar
    }

    private void configurePremiumEconomyClassSeat(SeatDto seat) {
        seat.setSeatClass("premium_economy");
        seat.setSeatWidth(19); // Premium ekonomi için daha geniş koltuklar
        seat.setSeatPitch(38); // Ekstra aralık
        seat.setHasPersonalEntertainment(true);
    }

    private void configureEconomyClassSeat(SeatDto seat) {
        seat.setSeatClass("economy");
        seat.setSeatWidth(17);
        seat.setSeatPitch(31); // Standart aralık
        seat.setHasPersonalEntertainment(false);
    }
}
