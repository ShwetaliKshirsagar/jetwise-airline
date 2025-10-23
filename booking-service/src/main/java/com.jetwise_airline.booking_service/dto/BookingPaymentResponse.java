package com.jetwise_airline.booking_service.dto;

import com.jetwise_airline.booking_service.entity.Booking;
import jakarta.persistence.Column;

public class BookingPaymentResponse {
    private String bookingId;
    private String bookingStatus;
    private int selectedSeats;
    private long flightId;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public int getSelectedSeats() {
        return selectedSeats;
    }

    public void setSelectedSeats(int selectedSeats) {
        this.selectedSeats = selectedSeats;
    }

    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }
    public static BookingPaymentResponse fromEntityToDTO(Booking booking){
        BookingPaymentResponse bookingPaymentResponse = new BookingPaymentResponse();
        bookingPaymentResponse.setBookingId(booking.getBookingId());
        bookingPaymentResponse.setBookingStatus(booking.getBookingStatus());
        bookingPaymentResponse.setFlightId(booking.getFlightId());
        bookingPaymentResponse.setSelectedSeats(booking.getSelectedSeats());
        return bookingPaymentResponse;
    }
}
