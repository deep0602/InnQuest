package com.intellect.abs.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookingDto {
	private int bookingId;
	private int userId;
	private int accommodationId;
	private LocalDateTime bookingDate;
    private Date checkInDate;
    private Date checkOutDate;
    private int totalPeople;
    private double totalPrice=0;
    private String status;
}
