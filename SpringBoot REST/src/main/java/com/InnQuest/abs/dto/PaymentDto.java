package com.intellect.abs.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
	private int paymentId;
	private int bookingId;
	private int userId;
	private double amount;
	private String paymentMethod;
	private String paymentStatus;
	private LocalDateTime paymentDateTime;
}
