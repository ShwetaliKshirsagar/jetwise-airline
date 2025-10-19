package com.jetwise_airline.booking_service.dto;


import com.jetwise_airline.booking_service.entity.Booking;

public class BookingRequest {
    private int selectedSeats;
    private long flightId;
    private String emailId;

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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public static Booking fromDtotoEntity(BookingRequest bookingRequest){
        Booking booking = new Booking();
        booking.setFlightId(bookingRequest.getFlightId());
        booking.setSelectedSeats(bookingRequest.getSelectedSeats());
        return booking;
    }

}

