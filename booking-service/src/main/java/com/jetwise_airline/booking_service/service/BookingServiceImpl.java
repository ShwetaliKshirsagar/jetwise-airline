package com.jetwise_airline.booking_service.service;

import com.jetwise_airline.booking_service.dto.BookingRequest;
import com.jetwise_airline.booking_service.dto.BookingResponse;
import com.jetwise_airline.booking_service.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public BookingResponse createBooking(BookingRequest bookingRequest) {

        return null;
    }
}
