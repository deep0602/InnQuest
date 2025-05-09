package com.intellect.abs.mapper;

import com.intellect.abs.dto.BookingDto;
import com.intellect.abs.model.Booking;

public class BookingMapper {
	public static BookingDto mapToBookingDto(Booking booking) {
			return new BookingDto(
					booking.getBookingId(),
					booking.getUser().getId(),
					booking.getAccommodationId(),
					booking.getBookingDate(),
					booking.getCheckInDate(),
					booking.getCheckOutDate(),
					booking.getTotalPeople(),
					booking.getTotalPrice(),
					booking.getStatus()
			);
		}
	
	public static Booking mapToBooking(BookingDto bookingDto) {

		Booking booking = new Booking();
		booking.setBookingId(bookingDto.getBookingId());
		booking.setUser(null);
		booking.setAccommodationId(bookingDto.getAccommodationId());
		booking.setBookingDate(bookingDto.getBookingDate());
		booking.setCheckInDate(bookingDto.getCheckInDate());
		booking.setCheckOutDate(bookingDto.getCheckOutDate());
		booking.setTotalPeople(bookingDto.getTotalPeople());
		booking.setStatus(bookingDto.getStatus());
		booking.setTotalPrice(bookingDto.getTotalPrice());

		return booking;
	}
}
		

