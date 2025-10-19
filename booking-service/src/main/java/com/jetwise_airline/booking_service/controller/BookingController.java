package com.jetwise_airline.booking_service.controller;

import com.jetwise_airline.booking_service.dto.BookingRequest;
import com.jetwise_airline.booking_service.service.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingServiceImpl bookingService;
    @PostMapping("create")
    public ResponseEntity<String> creatBooking(@RequestBody BookingRequest bookingRequest) {
        bookingService.createBooking(bookingRequest);
        return new ResponseEntity<>("Please proceed for payment to confirm booking", HttpStatus.CREATED);
    }
}
