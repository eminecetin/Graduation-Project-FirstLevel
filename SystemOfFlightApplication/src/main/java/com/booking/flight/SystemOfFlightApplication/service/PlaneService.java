package com.booking.flight.SystemOfFlightApplication.service;

import com.booking.flight.SystemOfFlightApplication.dto.PlaneDto;

import java.util.List;

public interface PlaneService {
    String createPlane(PlaneDto seatDto);
    List<PlaneDto> getAllPlane();
    PlaneDto getPlaneById(Long id) throws Exception;
     void save(PlaneDto planeDto);
    String updatePlane(Long id, PlaneDto seatDto) throws Exception;
    String deletePlane(Long id) throws Exception;
}
