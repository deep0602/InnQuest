package com.intellect.abs.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intellect.abs.dto.BookingDto;
import com.intellect.abs.exception.ResourceNotFoundException;
import com.intellect.abs.mapper.AccommodationMapper;
import com.intellect.abs.mapper.BookingMapper;
import com.intellect.abs.model.Accommodation;
import com.intellect.abs.model.Booking;
import com.intellect.abs.model.Payment;
import com.intellect.abs.repository.BookingRepository;
import com.intellect.abs.repository.PaymentRepository;
import com.intellect.abs.repository.UserRepository;
import com.intellect.abs.service.BookingService;
import com.intellect.abs.repository.AccommodationRepository;





@Service
public class BookingServiceImpl implements BookingService{
	@Autowired
    private BookingRepository bookingRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccommodationRepository accommodationRepository;
	
	@Override
    public BookingDto saveBooking(BookingDto bookingDto) {
		 double price=0;
	     long diffInMillies = Math.abs(bookingDto.getCheckOutDate().getTime() - bookingDto.getCheckInDate().getTime());
	    long diff=TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    price=diff*accommodationRepository.findById(bookingDto.getAccommodationId()).get().getPricePerNight();
		bookingDto.setTotalPrice(price);
		bookingDto.setBookingDate(LocalDateTime.now() );
		bookingDto.setStatus("success");
		Booking booking=BookingMapper.mapToBooking(bookingDto);
		if(userRepository.findById(bookingDto.getUserId()).isPresent())
			booking.setUser(userRepository.findById(bookingDto.getUserId()).get());
		bookingRepository.save(booking);
		Payment payment = new Payment();
        payment.setBookingId(booking.getBookingId());
        payment.setAmount(booking.getTotalPrice());
        payment.setPaymentStatus("PENDING");
        payment.setPaymentDateTime(LocalDateTime.now());
        paymentRepository.save(payment);
		return BookingMapper.mapToBookingDto(booking);
        
    }
	
	
	@Override
	public BookingDto getBookingById(int id) {
		try {
			return BookingMapper.mapToBookingDto(bookingRepository.findById(id).get());
		}
		catch(Exception e) {
			throw new ResourceNotFoundException("Does not exist");
		}
	}
    @Override
    public BookingDto cancelBookingById(int id) {
    	Optional<Booking> booking=bookingRepository.findById(id);
    	try {

				booking.get().setStatus("Cancelled");
				return BookingMapper.mapToBookingDto(bookingRepository.findById(id).get());			
    	}
    	catch(Exception e) {
    		throw new ResourceNotFoundException("Does not exist");
    	}	
    }


	@Override
	public List<BookingDto> getBookingByUserId(int id) {
		List<Booking>bookings = bookingRepository.findByUserId(id);
        return bookings.stream()
                             .map(BookingMapper::mapToBookingDto)
                             .collect(Collectors.toList());
    
}
}