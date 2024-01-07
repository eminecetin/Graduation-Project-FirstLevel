package com.booking.flight.SystemOfFlightApplication.controller;

import com.booking.flight.SystemOfFlightApplication.dto.AirlineDto;
import com.booking.flight.SystemOfFlightApplication.service.AirlineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AirlineControllerTest {

    @Mock //Dependencyler için mockluyoruz
    private AirlineService airlineService;

    @InjectMocks  //dependencylere enjecte ediyor
    private AirlineController underTest;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getById() {

        // pepare given data



    }

    @Test
    void createAirline() {
    }

    @Test
    void getAllAirlines() {
        // prepare given data

        List<AirlineDto> airlineList=new ArrayList<>();
        airlineList.add(new AirlineDto());
        airlineList.add(new AirlineDto());

        //when
        Mockito.when(airlineService.getAllAirlines()).thenReturn(airlineList);
        ResponseEntity<List<AirlineDto>> response=(ResponseEntity<List<AirlineDto>>) underTest.getAllAirlines();
        assertEquals(airlineList, response.getBody());
        Mockito.verify(airlineService, Mockito.times(1)).getAllAirlines();
    }

    @Test
    void updateAirline() {
    }

    @Test
    void deleteAirline() {
    }
}