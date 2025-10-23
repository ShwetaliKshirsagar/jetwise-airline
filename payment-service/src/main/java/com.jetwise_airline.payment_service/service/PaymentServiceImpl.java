package com.jetwise_airline.payment_service.service;

import com.jetwise_airline.payment_service.client.BookingClient;
import com.jetwise_airline.payment_service.dto.BookingResponse;
import com.jetwise_airline.payment_service.dto.PaymentRequestDTO;
import com.jetwise_airline.payment_service.entity.Payment;
import com.jetwise_airline.payment_service.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private BookingClient bookingClient;
    @Override
    public String processPayment(PaymentRequestDTO request) {
        boolean success = Math.random() > 0.2; // 80% success rate

        String status = success ? "SUCCESS" : "FAILED";

        Payment payment = PaymentRequestDTO.fromDTOToEntity(request);
        payment.setStatus(status);

        String s = bookingClient.updateBookingStatus(request.getBookingId(), status);
        return status;
    }

}
