package com.booking.flight.SystemOfFlightApplication.service.impl;

import com.booking.flight.SystemOfFlightApplication.dto.SeatDto;
import com.booking.flight.SystemOfFlightApplication.entity.Seat;
import com.booking.flight.SystemOfFlightApplication.enums.SeatStatus;
import com.booking.flight.SystemOfFlightApplication.repository.SeatRepository;
import com.booking.flight.SystemOfFlightApplication.service.SeatService;
import org.modelmapper.ModelMapper;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;
    private final ModelMapper modelMapper;
    private final SimpMessagingTemplate messagingTemplate;

    //private final SeatMapperImpl seatMapper;
    public SeatServiceImpl(
            SeatRepository seatRepository,
            ModelMapper modelMapper, SimpMessagingTemplate messagingTemplate) {
        this.seatRepository = seatRepository;
        this.modelMapper = modelMapper;
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void updateSeatStatus(Long seatId, SeatStatus newStatus) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("ID'li koltuk bulunamadı: " + seatId));
        changeSeatState(modelMapper.map(seat, SeatDto.class), newStatus);

        // Koltuk durumu değiştiğinde, WebSocket üzerinden bu değişikliği yayınla
        messagingTemplate.convertAndSend("/topic/seatUpdate", modelMapper.map(seat, SeatDto.class));
    }

    public void changeSeatState(SeatDto seatDto, SeatStatus newStatus) {
        seatDto.setStatus(newStatus);
        save(seatDto);
    }

    @Override
    public void saveAll(List<SeatDto> seatDtos) {
        List<Seat> seats = seatDtos.stream()
                .map(dto -> modelMapper.map(dto, Seat.class))
                .collect(Collectors.toList());
        seatRepository.saveAll(seats);
    }

    @Override
    public SeatDto createSeat(SeatDto seatDto) {
        Seat savedSeat = seatRepository.save(modelMapper.map(seatDto, Seat.class));
        SeatDto savedSeatDto = modelMapper.map(savedSeat, SeatDto.class);

        // WebSocket yayını
        messagingTemplate.convertAndSend("/topic/seatCreated", savedSeatDto);

        return savedSeatDto;
    }

    @Override
    public List<SeatDto> getAllSeats() {
        return seatRepository.findAll()
                .stream()
                .map(seat -> modelMapper.map(seat, SeatDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public SeatDto getSeatById(Long id) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID'li koltuk bulunamadı: " + id));
        return modelMapper.map(seat, SeatDto.class);
    }

    @Override
    public String updateSeat(Long id, SeatDto seatDto) throws Exception {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID'li koltuk bulunamadı: " + id));
        modelMapper.map(seatDto, seat);
        seatRepository.save(seat);
        return "Koltuk başarıyla güncellendi: " + id;
    }
    @Override
    public String deleteSeat(Long id) throws Exception {
        Optional<Seat> seatOptional = seatRepository.findById(id);
        // Koltuk bulunamazsa hata fırlat
        if (!seatOptional.isPresent()) {
            throw new RuntimeException("ID'li koltuk bulunamadı: " + id);
        }
        // Koltuk bulunursa, silme işlemini yap
        seatRepository.deleteById(id);

        // Koltuk silindiğini WebSocket üzerinden yayınla
        messagingTemplate.convertAndSend("/topic/seatDeleted", id);

        return "Koltuk silindi: " + id; }

    @Override
    public List<SeatDto> findByPlaneId(Long planeId) {
        List<Seat> seats = seatRepository.findByPlaneId(planeId);
        return seats.stream()
                .map(seat -> modelMapper.map(seat, SeatDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void save(SeatDto seatDto) {
        Seat seat = modelMapper.map(seatDto, Seat.class);
        Seat savedSeat = seatRepository.save(seat);

        // Koltuk kaydedildikten sonra, WebSocket üzerinden bu değişikliği yayınla
        messagingTemplate.convertAndSend("/topic/seatSaved", modelMapper.map(savedSeat, SeatDto.class));
    }
}
