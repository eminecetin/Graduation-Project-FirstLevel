package com.booking.flight.SystemOfFlightApplication.patterns.seat;

import com.booking.flight.SystemOfFlightApplication.dto.SeatDto;
import com.booking.flight.SystemOfFlightApplication.entity.Seat;
import com.booking.flight.SystemOfFlightApplication.enums.SeatStatus;
import com.booking.flight.SystemOfFlightApplication.repository.SeatRepository;
import com.booking.flight.SystemOfFlightApplication.service.impl.SeatServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*Koltuk boş olduğunda bu durum geçerlidir. Koltuğun seçilmesiveya rezerve
edilmesi gibi işlemler bu durumda gerçekleştirilebilir.*/
@Service
public class AvailableSeatService implements SeatState {
    private final ModelMapper modelMapper;
    private final SeatRepository seatRepository; // Varsayım: Seat entity'sini yöneten repository


    @Autowired
    public AvailableSeatService(ModelMapper modelMapper, SeatRepository seatRepository) {
        this.modelMapper = modelMapper;
        this.seatRepository = seatRepository;
    }

    @Override
    public void selectSeat(SeatDto seatDto) {
        if (seatDto.getStatus().equals(SeatStatus.AVAILABLE)) {
            seatDto.setStatus(SeatStatus.SELECTED);
           save(seatDto); // Veritabanına kaydet
        } else {
            throw new IllegalStateException("Seat is not available");
        }
    }

    @Override
    public void reserveSeat(SeatDto seatDto) {
        if (seatDto.getStatus().equals(SeatStatus.AVAILABLE)) {
            seatDto.setStatus(SeatStatus.RESERVED);
            save(seatDto); // Veritabanına kaydet
        } else {
            throw new IllegalStateException("Seat is not available for reservation");
        }
    }

    @Override
    public void freeSeat(SeatDto seatDto) {
        // Boş durumda bir işlem yapılması gerekmez
    }
    private void save(SeatDto seatDto) {
        Seat seat = convertToEntity(seatDto);
        seatRepository.save(seat);
    }
    private Seat convertToEntity(SeatDto seatDto) {
        return modelMapper.map(seatDto, Seat.class);
    }
}


    /*@Override
    public void selectSeat(Seat seat) {
        // Koltuk seçildiğinde yapılacak işlemler
        System.out.println("Seat " + seat.getSeatNumber() + " is now selected.");
        seat.setState(new SelectedSeatService());
    }

    @Override
    public void reserveSeat(Seat seat) {
        // Müsait bir koltuk direkt olarak rezerve edilebilir
        System.out.println("Seat " + seat.getSeatNumber() + " is now reserved.");
        seat.setState(new ReservedSeatService());
    }

    @Override
    public void freeSeat(Seat seat) {
        // Koltuk zaten müsait durumda olduğundan, bu durumda yapılacak bir şey yok
        System.out.println("Seat " + seat.getSeatNumber() + " is already available.");
    }*/