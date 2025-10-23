package com.jetwise_airline.payment_service.dto;

import com.jetwise_airline.payment_service.entity.Payment;
import jakarta.persistence.Column;

public class PaymentRequestDTO {
    private String bookingId;
    private double amount;
    private String status;
    private String paymentMethod;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public static Payment fromDTOToEntity(PaymentRequestDTO dto){
        Payment payment = new Payment();
        payment.setBookingId(dto.getBookingId());
        payment.setAmount(dto.getAmount());
        payment.setPaymentMethod(dto.getPaymentMethod());
        return payment;
    }
}
