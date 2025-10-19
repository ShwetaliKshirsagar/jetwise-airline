package com.jetwise_airline.booking_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="booking")
    private long bookingId;
    @Column(name="status")
    private String bookingStatus;
    @Column(name="bookedseats")
    private int selectedSeats;
    @Column(name="flightid")
    private long flightId;

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Column(name="emailid")
    private String emailId;

}
