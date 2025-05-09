package com.intellect.abs.mapper;

import com.intellect.abs.dto.PaymentDto;
import com.intellect.abs.model.Payment;

public class PaymentMapper {

	public static PaymentDto toDto(Payment payment) {
        if (payment == null) {
            return null;
        }

        return new PaymentDto(
                payment.getPaymentId(),
                payment.getBookingId(),
                payment.getUser().getId(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getPaymentStatus(),
                payment.getPaymentDateTime()
        );
    }

	public static Payment toEntity(PaymentDto paymentDto) {
        if (paymentDto == null) {
            return null;
        }
        Payment payment = new Payment();
        payment.setPaymentId(paymentDto.getPaymentId());
        payment.setBookingId(paymentDto.getBookingId());
        payment.setUser(null);
        payment.setAmount(paymentDto.getAmount());
        payment.setPaymentMethod(paymentDto.getPaymentMethod());
        payment.setPaymentStatus(paymentDto.getPaymentStatus());
        payment.setPaymentDateTime(paymentDto.getPaymentDateTime());

        return payment;
    }
}
