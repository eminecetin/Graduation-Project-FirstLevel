package com.booking.flight.SystemOfFlightApplication.service.impl;

import com.booking.flight.SystemOfFlightApplication.dto.PaymentDto;
import com.booking.flight.SystemOfFlightApplication.dto.ReservationDto;
import com.booking.flight.SystemOfFlightApplication.dto.TicketDto;
import com.booking.flight.SystemOfFlightApplication.entity.Reservation;
import com.booking.flight.SystemOfFlightApplication.entity.Ticket;
import com.booking.flight.SystemOfFlightApplication.patterns.reservation.reservationStrategy.ReservationStrategy;
import com.booking.flight.SystemOfFlightApplication.patterns.reservation.reservationStrategy.ReservationStrategyFactory;
import com.booking.flight.SystemOfFlightApplication.repository.TicketRepository;
import com.booking.flight.SystemOfFlightApplication.service.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;

    public TicketServiceImpl(TicketRepository ticketRepository, ModelMapper modelMapper) {
        this.ticketRepository = ticketRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TicketDto createTicket(ReservationDto reservationDto, double ticketPrice) {
        // DTO'dan Entity'ye dönüşüm
        Ticket ticketEntity = modelMapper.map(reservationDto, Ticket.class);

        // Entity üzerinde gerekli değişiklikler
        ticketEntity.setTicketNo("TCK" + new Random().nextInt(10000));
        ticketEntity.setTicketPrice(ticketPrice);
        ticketEntity.setTicketState(true);
        ticketEntity.setFlightNumber("FL" + reservationDto.getFlightId());
        ticketEntity.setFlightId(reservationDto.getFlightId());
        ticketEntity.setPassengerName(reservationDto.getPassengerName());
        ticketEntity.setSeatNumber(reservationDto.getSeatNumber());

        // Entity'yi veritabanına kaydet
        ticketEntity = ticketRepository.save(ticketEntity);

        // Entity'den DTO'ya dönüşüm
        TicketDto ticketDto = modelMapper.map(ticketEntity, TicketDto.class);

        return ticketDto;
    }


    @Override
    public String createTicket(TicketDto ticketDto) {
        Ticket ticket = modelMapper.map(ticketDto, Ticket.class);
        ticketRepository.save(ticket);
        return "Ticket created successfully";
    }
    @Override
    public TicketDto create_Ticket(ReservationDto reservationDto) {
        // ReservationStrategy'den dönen fiyatı kullan
        double promotedPrice = applyReservationPromotion(reservationDto);

        TicketDto ticketDto = new TicketDto.Builder()
                .reservation(reservationDto)
                .ticketPrice(promotedPrice)
                // Diğer gerekli alanlar...
                .build();

        // ModelMapper kullanarak DTO'dan entity'e dönüşüm yapılıyor
        Ticket ticket = modelMapper.map(ticketDto, Ticket.class);

        // Ticket numarası ve diğer gerekli bilgileri ayarla
        ticket.setTicketNo(generateTicketNumber());
        ticket.setTicketState(true);

        // Veritabanına kaydet
        Ticket savedTicket = ticketRepository.save(ticket);

        // Kaydedilen entity'i DTO'ya dönüştür ve geri döndür
        return modelMapper.map(savedTicket, TicketDto.class);
    }

    private double applyReservationPromotion(ReservationDto reservationDto) {
        ReservationStrategy reservationStrategy = ReservationStrategyFactory.getStrategy(reservationDto);
        reservationStrategy.makeReservation(reservationDto);
        return reservationDto.getTicketPrice();
    }

    private String generateTicketNumber() {
        // Ticket numarası oluşturmak için bir mantık
        return "TKT" + System.currentTimeMillis(); // Basit bir örnek
    }

    @Override
    public List<TicketDto> getAllTickets() {
        return ticketRepository.findAll()
                .stream()
                .map(ticket -> modelMapper.map(ticket, TicketDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TicketDto getTicketById(Long id) throws Exception {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new Exception("Ticket not found with ID: " + id));
        return modelMapper.map(ticket, TicketDto.class);
    }

    @Override
    public void save(TicketDto ticketDto) {
        Ticket ticket = modelMapper.map(ticketDto, Ticket.class);
        ticketRepository.save(ticket);
    }

    @Override
    public String updateTicket(Long id, TicketDto ticketDto) throws Exception {
        Ticket existingTicket = ticketRepository.findById(id)
                .orElseThrow(() -> new Exception("Ticket not found with ID: " + id));

        modelMapper.map(ticketDto, existingTicket);
        ticketRepository.save(existingTicket);
        return "Ticket updated successfully";
    }

    @Override
    public String deleteTicket(Long id) throws Exception {
        if (!ticketRepository.existsById(id)) {
            throw new Exception("Ticket not found with ID: " + id);
        }
        ticketRepository.deleteById(id);
        return "Ticket deleted successfully";
    }
}
