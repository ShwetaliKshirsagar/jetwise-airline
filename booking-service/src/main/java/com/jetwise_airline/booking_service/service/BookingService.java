package com.jetwise_airline.booking_service.service;

import com.jetwise_airline.booking_service.dto.BookingRequest;
import com.jetwise_airline.booking_service.dto.BookingResponse;

public interface BookingService {
    public BookingResponse createBooking(BookingRequest bookingRequest);
}
