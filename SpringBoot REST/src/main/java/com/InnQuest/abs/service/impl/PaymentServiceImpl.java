package com.intellect.abs.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intellect.abs.dto.PaymentDto;
import com.intellect.abs.mapper.PaymentMapper;
import com.intellect.abs.model.Payment;
import com.intellect.abs.repository.PaymentRepository;
import com.intellect.abs.repository.UserRepository;
import com.intellect.abs.service.PaymentService;
@Service
public class PaymentServiceImpl implements PaymentService {
	
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
	private UserRepository userRepository;
    @Override
    public PaymentDto createPayment(PaymentDto paymentDto) {
           Payment payment = PaymentMapper.toEntity(paymentDto);
//            payment.setBookingId(paymentDto.getBookingId());
//            payment.setUserId(paymentDto.getUserId());
//            payment.setAmount(paymentDto.getAmount());
//            payment.setPaymentMethod(paymentDto.getPaymentMethod());
     payment.setPaymentStatus("Success");
         payment.setPaymentDateTime(LocalDateTime.now());
           payment.setUser(userRepository.findById(paymentDto.getUserId()).get());
            payment = paymentRepository.save(payment);
            return PaymentMapper.toDto(payment);
    }


    @Override
    public PaymentDto updatePayment(int paymentId, String paymentStatus) {
        try {
            Optional<Payment> paymentOptional = paymentRepository.findById(paymentId);
            if (paymentOptional.isPresent()) {
                Payment payment = paymentOptional.get();
                payment.setPaymentStatus(paymentStatus);

                payment = paymentRepository.save(payment);
                return PaymentMapper.toDto(payment);
            } else {
         
                System.err.println("Payment not found with ID: " + paymentId);
                return null; // or throw a custom exception
            }
        } catch (Exception e) {
            System.err.println("Error updating payment: " + e.getMessage());
            return null; 
        }
    }

    @Override
    public PaymentDto getPayment(int paymentId) {
        try {
            Optional<Payment> paymentOptional = paymentRepository.findById(paymentId);
            if (paymentOptional.isPresent()) {
                Payment payment = paymentOptional.get();
                return PaymentMapper.toDto(payment);
            } else {
                System.err.println("Payment not found with ID: " + paymentId);
                return null; 
            }
        } catch (Exception e) {
            System.err.println("Error retrieving payment: " + e.getMessage());
            return null; 
        }
    }

}
