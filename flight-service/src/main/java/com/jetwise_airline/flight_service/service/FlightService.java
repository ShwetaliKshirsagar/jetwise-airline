package com.jetwise_airline.flight_service.service;

import com.jetwise_airline.flight_service.dto.FlightRequestDTO;
import com.jetwise_airline.flight_service.dto.FlightResponseDTO;
import com.jetwise_airline.flight_service.dto.FlightSearchDTO;
import com.jetwise_airline.flight_service.entity.FlightEntity;
import com.jetwise_airline.flight_service.exceptions.FlightAlreadyExists;
import com.jetwise_airline.flight_service.exceptions.FlightNotFoundException;

import java.lang.reflect.Executable;
import java.util.List;

public interface FlightService {
    void addFlight(FlightRequestDTO flightRequest) throws FlightAlreadyExists;
    FlightResponseDTO updateFlight(FlightRequestDTO flightRequest) throws FlightNotFoundException;
    void deleteFlight(String flightNumber) throws FlightNotFoundException;
    List<FlightResponseDTO> searchFlights(String source, String destination) throws FlightNotFoundException;
}