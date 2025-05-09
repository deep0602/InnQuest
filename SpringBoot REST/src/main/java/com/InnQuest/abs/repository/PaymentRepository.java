package com.intellect.abs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.intellect.abs.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Integer>{
	
}
