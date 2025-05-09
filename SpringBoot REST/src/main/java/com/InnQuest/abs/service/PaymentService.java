package com.intellect.abs.service;

import org.springframework.stereotype.Service;

import com.intellect.abs.dto.PaymentDto;


@Service
public interface PaymentService {
	public PaymentDto createPayment(PaymentDto paymentDto);
	public PaymentDto updatePayment(int paymentId, String paymentStatus);
	 public PaymentDto getPayment(int paymentId);
}
