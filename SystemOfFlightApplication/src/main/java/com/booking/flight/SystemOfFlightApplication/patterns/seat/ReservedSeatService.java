package com.booking.flight.SystemOfFlightApplication.patterns.seat;

import com.booking.flight.SystemOfFlightApplication.dto.SeatDto;
import com.booking.flight.SystemOfFlightApplication.enums.SeatStatus;
import com.booking.flight.SystemOfFlightApplication.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*Koltuk rezerve edildiğinde bu durum geçerlidir. Bu durumda, genellikle koltuğun
 serbest bırakılması işlemi gerçekleştirilebilir.*/
@Service
public class ReservedSeatService implements SeatState {

    private final SeatService seatService;

    @Autowired
    public ReservedSeatService(SeatService seatService) {
        this.seatService = seatService;
    }

    @Override
    public void selectSeat(SeatDto seatDto) {
        // Rezerve edilmiş bir koltuğun doğrudan seçilmesi genellikle mümkün değildir
        throw new IllegalStateException("Reserved seat cannot be directly selected");
    }

    @Override
    public void reserveSeat(SeatDto seatDto) {
        // Koltuk zaten rezerve edilmiş
        throw new IllegalStateException("Seat is already reserved");
    }

    @Override
    public void freeSeat(SeatDto seatDto) {
        if (seatDto.getStatus().equals(SeatStatus.RESERVED)) {
            // Koltuğu serbest bırak ve kullanılabilir olarak işaretle
            seatDto.setStatus(SeatStatus.AVAILABLE);
            seatService.save(seatDto);
        } else {
            throw new IllegalStateException("Seat is not reserved or cannot be freed");
        }
    }
}

 /* @Override
    public void selectSeat(Seat seat) {
        // Rezerve edilmiş koltuk genellikle seçilemez
        System.out.println("Seat " + seat.getSeatNumber() + " is already reserved and cannot be selected.");
    }

    @Override
    public void reserveSeat(Seat seat) {
        // Koltuk zaten rezerve edilmiş
        System.out.println("Seat " + seat.getSeatNumber() + " is already reserved.");
    }

    @Override
    public void freeSeat(Seat seat) {
        // Rezervasyonu iptal edip koltuğu serbest bırakır
        System.out.println("Seat " + seat.getSeatNumber() + " reservation is cancelled, now it is available.");
        seat.setState(new AvailableSeatService());
    }*/