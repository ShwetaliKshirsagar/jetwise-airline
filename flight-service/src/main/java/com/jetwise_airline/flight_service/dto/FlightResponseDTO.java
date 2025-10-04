package com.jetwise_airline.flight_service.dto;

import com.jetwise_airline.flight_service.entity.FlightEntity;

import java.time.LocalDateTime;

public class FlightResponseDTO {
    private Long id;
    private String flightNumber;
    private String source;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int capacity;

    public Long getId() {
        return id;
    }

    public FlightResponseDTO() {
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    public static FlightResponseDTO fromEntity(FlightEntity entity) {
        FlightResponseDTO dto = new FlightResponseDTO();
        dto.setId(entity.getId());
        dto.setFlightNumber(entity.getFlightNumber());
        dto.setSource(entity.getSource());
        dto.setDestination(entity.getDestination());
        dto.setArrivalTime(entity.getArrivalTime());
        dto.setCapacity(entity.getCapacity());
        return dto;
    }


}
