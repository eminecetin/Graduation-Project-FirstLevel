package com.booking.flight.SystemOfFlightApplication.service;

import com.booking.flight.SystemOfFlightApplication.dto.PaymentDto;
import com.booking.flight.SystemOfFlightApplication.dto.ReservationDto;
import com.booking.flight.SystemOfFlightApplication.dto.TicketDto;

import java.util.List;

public interface TicketService {
    TicketDto create_Ticket(ReservationDto reservationDto);
    TicketDto createTicket(ReservationDto reservationDto, double ticketPrice);
    //TicketDto createTickett(ReservationDto reservationDto, PaymentDto paymentDto);
    String createTicket(TicketDto ticketDto);
    List<TicketDto> getAllTickets();
    TicketDto getTicketById(Long id) throws Exception;
    void save(TicketDto ticketDto);
    String updateTicket(Long id, TicketDto ticketDto) throws Exception;
    String deleteTicket(Long id) throws Exception;
}
