package com.booking.flight.SystemOfFlightApplication.service;

import com.booking.flight.SystemOfFlightApplication.dto.SeatDto;
import com.booking.flight.SystemOfFlightApplication.enums.SeatStatus;

import java.util.List;

public interface SeatService {
    public void updateSeatStatus(Long seatId, SeatStatus newStatus);
    public void saveAll(List<SeatDto> seatDtos);
    SeatDto createSeat(SeatDto seatDto);
    List<SeatDto> getAllSeats();
    SeatDto getSeatById(Long id);
    String updateSeat(Long id, SeatDto seatDto) throws Exception;
    String deleteSeat(Long id) throws Exception;
    public List<SeatDto> findByPlaneId(Long planeId);
    public void save(SeatDto seatDto);
}
