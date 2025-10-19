package com.jetwise_airline.booking_service.service;

import com.jetwise_airline.booking_service.dto.BookingRequest;
import com.jetwise_airline.booking_service.dto.BookingResponse;
import com.jetwise_airline.booking_service.dto.FlightDTO;
import com.jetwise_airline.booking_service.entity.Booking;
import com.jetwise_airline.booking_service.exception.SeatsUnvailableException;
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
    public void createBooking(BookingRequest bookingRequest) throws SeatsUnvailableException{
        FlightDTO flight = restTemplate.getForObject("http://localhost:8082/api/flights/getFlight/" + bookingRequest.getFlightId(), FlightDTO.class);
        if(flight.getCapacity()<bookingRequest.getSelectedSeats()){
            throw new SeatsUnvailableException("NO.SEATS.AVAILABLE");
        }
        else {
            Booking booking = BookingRequest.fromDtotoEntity(bookingRequest);
            booking.setBookingStatus("PENDING");
            booking.setEmailId("dummy@gmail.com");// this need to extract from token
            bookingRepository.save(booking);
        }
    }
}
