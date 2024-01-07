package com.booking.flight.SystemOfFlightApplication.service.impl;

import com.booking.flight.SystemOfFlightApplication.dto.PlaneDto;
import com.booking.flight.SystemOfFlightApplication.entity.Plane;
import com.booking.flight.SystemOfFlightApplication.repository.PlaneRepository;
import com.booking.flight.SystemOfFlightApplication.service.PlaneService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class PlaneServiceImpl implements PlaneService {

    private final PlaneRepository planeRepository;
    private final ModelMapper modelMapper;

    public PlaneServiceImpl(PlaneRepository planeRepository, ModelMapper modelMapper) {
        this.planeRepository = planeRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public String createPlane(PlaneDto planeDto) {
        Plane plane = modelMapper.map(planeDto, Plane.class);
        planeRepository.save(plane);
        return "Uçak başarıyla oluşturuldu: " + plane.getId();
    }

    @Override
    public List<PlaneDto> getAllPlane() {
        return planeRepository.findAll()
                .stream()
                .map(plane -> modelMapper.map(plane, PlaneDto.class))
                .collect(Collectors.toList());    }


    @Override
    public PlaneDto getPlaneById(Long id) throws Exception {
        Plane plane = planeRepository.findById(id)
                .orElseThrow(() -> new Exception("ID'li uçak bulunamadı: " + id));
        return modelMapper.map(plane, PlaneDto.class);
    }

    @Override
    public void save(PlaneDto planeDto) {
        Plane plane = modelMapper.map(planeDto, Plane.class);
        planeRepository.save(plane);
    }
    @Override
    public String updatePlane(Long id, PlaneDto planeDto) throws Exception {
        Plane plane = planeRepository.findById(id)
                .orElseThrow(() -> new Exception("ID'li uçak bulunamadı: " + id));
        modelMapper.map(planeDto, plane);
        planeRepository.save(plane);
        return "Uçak başarıyla güncellendi: " + id;
    }

    @Override
    public String deletePlane(Long id) throws Exception {
        Plane plane = planeRepository.findById(id)
                .orElseThrow(() -> new Exception("ID'li uçak bulunamadı: " + id));
        planeRepository.delete(plane);
        return "Uçak başarıyla silindi: " + id;
    }
}
