package com.booking.flight.SystemOfFlightApplication.patterns.seat;

import com.booking.flight.SystemOfFlightApplication.dto.SeatDto;
import com.booking.flight.SystemOfFlightApplication.entity.Seat;
import com.booking.flight.SystemOfFlightApplication.enums.SeatStatus;
import com.booking.flight.SystemOfFlightApplication.repository.SeatRepository;
import com.booking.flight.SystemOfFlightApplication.service.SeatService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*Koltuk bir kullanıcı tarafından seçildiğinde bu durum geçerlidir. Bu durumda, koltuğun rezerve edilmesi veya serbest bırakılması
(boşa çıkarılması) işlemleri gerçekleştirilebilir.*/
@Service
public class SelectedSeatService implements SeatState {

    private final ModelMapper modelMapper;
    private final SeatRepository seatRepository;

    @Autowired
    public SelectedSeatService( ModelMapper modelMapper, SeatRepository seatRepository) {
        this.modelMapper = modelMapper;
        this.seatRepository = seatRepository;
    }

    @Override
    public void selectSeat(SeatDto seatDto) {
        // Koltuk zaten seçilmiş durumda, bu yüzden ek bir işlem yapmaya gerek yok
    }

    @Override
    public void reserveSeat(SeatDto seatDto) {
        if (seatDto.getStatus().equals(SeatStatus.SELECTED)) {
            seatDto.setStatus(SeatStatus.RESERVED);
            save(seatDto);
        } else {
            throw new IllegalStateException("Seat is not selected or cannot be reserved");
        }
    }

    @Override
    public void freeSeat(SeatDto seatDto) {
        if (seatDto.getStatus().equals(SeatStatus.SELECTED)) {
            seatDto.setStatus(SeatStatus.AVAILABLE);
            save(seatDto);
        } else {
            throw new IllegalStateException("Seat is not selected or cannot be freed");
        }
    }
    private void save(SeatDto seatDto) {
        Seat seat = convertToEntity(seatDto);
        seatRepository.save(seat);
    }
    private Seat convertToEntity(SeatDto seatDto) {
        return modelMapper.map(seatDto, Seat.class);
    }
}
   /* @Override
    public void selectSeat(Seat seat) {
        // Koltuk zaten seçili durumda
        System.out.println("Seat " + seat.getSeatNumber() + " is already selected.");
    }

    @Override
    public void reserveSeat(Seat seat) {
        // Koltuğu rezerve eder
        System.out.println("Seat " + seat.getSeatNumber() + " is now reserved.");
        seat.setState(new ReservedSeatService());
    }

    @Override
    public void freeSeat(Seat seat) {
        // Seçimi iptal edip koltuğu serbest bırakır
        System.out.println("Seat " + seat.getSeatNumber() + " is now available.");
        seat.setState(new AvailableSeatService());
    }*/