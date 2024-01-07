package com.booking.flight.SystemOfFlightApplication.patterns.plane;

import com.booking.flight.SystemOfFlightApplication.dto.PlaneDto;
import com.booking.flight.SystemOfFlightApplication.dto.SeatDto;
import com.booking.flight.SystemOfFlightApplication.service.impl.SeatServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirbusA380Service implements PlaneFactory {
    private final SeatServiceImpl seatService;

    public AirbusA380Service(SeatServiceImpl seatService) {
        this.seatService = seatService;
    }

    @Override
    public PlaneDto setUpSeats(PlaneDto planeDto) {
        int totalSeats = 200; // Örnek olarak 200 koltuk kapasitesi
        int businessClassSeats = 50; // Örneğin, toplam koltukların %25'i
        int economyClassSeats = planeDto.getTotalSeats() - businessClassSeats;

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
          //  seatService.save(seat); // Veritabanına kaydet
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

    private void configureEconomyClassSeat(SeatDto seat) {
        seat.setSeatClass("economy");
        seat.setSeatWidth(17); // Standart genişlik
        seat.setSeatPitch(31); // Standart aralık
        seat.setHasPersonalEntertainment(false);
    }
}
