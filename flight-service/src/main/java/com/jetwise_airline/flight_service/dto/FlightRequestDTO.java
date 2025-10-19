package com.jetwise_airline.flight_service.dto;

import com.jetwise_airline.flight_service.entity.FlightEntity;

import java.time.LocalDateTime;

public class FlightRequestDTO {
    private String flightNumber;
    private String source;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int capacity;
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public static FlightEntity toEntity(FlightRequestDTO request){
        FlightEntity entity = new FlightEntity();
        entity.setFlightNumber(request.getFlightNumber());
        entity.setSource(request.getSource());
        entity.setDestination(request.getDestination());
        entity.setArrivalTime(request.getArrivalTime());
        entity.setArrivalTime(request.getArrivalTime());
        entity.setDepartureTime(request.getDepartureTime());
        entity.setCapacity(request.getCapacity());
        entity.setPrice(request.getPrice());
        return entity;
    }

}
