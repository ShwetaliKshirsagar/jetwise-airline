package com.jetwise_airline.booking_service.service;

import com.jetwise_airline.booking_service.dto.BookingRequest;
import com.jetwise_airline.booking_service.dto.BookingResponse;
import com.jetwise_airline.booking_service.exception.SeatsUnvailableException;

public interface BookingService {
    public void createBooking(BookingRequest bookingRequest) throws SeatsUnvailableException;
}
