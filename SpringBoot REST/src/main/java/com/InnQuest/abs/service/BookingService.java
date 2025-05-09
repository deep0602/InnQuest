package com.intellect.abs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.intellect.abs.dto.BookingDto;


@Service
public interface BookingService {
	BookingDto saveBooking(BookingDto bookingDto);
	 BookingDto getBookingById(int id);
	 BookingDto cancelBookingById(int id);
	 List<BookingDto> getBookingByUserId(int id);
}
