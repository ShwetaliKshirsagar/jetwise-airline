package com.jetwise_airline.payment_service.client;

import com.jetwise_airline.payment_service.dto.BookingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "booking-service" ,url="http://localhost:8083/api")
public interface BookingClient {

    @GetMapping("/booking/fetch/{bookingId}")
    BookingResponse getFlightById(@PathVariable ("bookingId") String bookingId);

    @PutMapping("/booking/update-status/{bookingId}/{status}")
    String updateBookingStatus(@PathVariable ("bookingId") String bookingId, @PathVariable("status") String status);


}
