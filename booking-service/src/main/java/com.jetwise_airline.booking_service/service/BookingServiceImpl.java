package com.jetwise_airline.booking_service.service;

import com.jetwise_airline.booking_service.dto.BookingPaymentResponse;
import com.jetwise_airline.booking_service.dto.BookingRequest;
import com.jetwise_airline.booking_service.dto.FlightDTO;
import com.jetwise_airline.booking_service.entity.Booking;
import com.jetwise_airline.booking_service.exception.SeatsUnvailableException;
import com.jetwise_airline.booking_service.repository.BookingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public void createBooking(BookingRequest bookingRequest) {
        FlightDTO flight = restTemplate.getForObject("http://localhost:8082/api/flights/getFlight/" + bookingRequest.getFlightId(), FlightDTO.class);
        if(flight.getCapacity()<bookingRequest.getSelectedSeats()){
            throw new SeatsUnvailableException("NO.SEATS.AVAILABLE");
        }
        else {
            Booking booking = modelMapper.map(bookingRequest, Booking.class);
            booking.setBookingStatus("PENDING");
            booking.setBookingId(null);
            booking.setEmailId("dummy@gmail.com");// this need to extract from token
            bookingRepository.save(booking);

        }
    }
    @Override
    public BookingPaymentResponse getBookingDetails(String bookingId){
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("BOOKING.NOT.FOUND"));
        return modelMapper.map(booking, BookingPaymentResponse.class);
    }
    @Override
    public void updateBookingStatus(String bookingId, String status){
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("BOOKING.NOT.FOUND"));
        booking.setBookingStatus(status);
        bookingRepository.save(booking);
    }
}
