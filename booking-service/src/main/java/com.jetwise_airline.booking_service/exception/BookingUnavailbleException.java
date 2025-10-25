package com.jetwise_airline.booking_service.exception;

public class BookingUnavailbleException extends RuntimeException {
    public BookingUnavailbleException(String message){
        super(message);
    }
}
