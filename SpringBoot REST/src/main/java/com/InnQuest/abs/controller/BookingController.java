package com.intellect.abs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intellect.abs.dto.BookingDto;
import com.intellect.abs.service.BookingService;


@RestController
@RequestMapping("/booking")
@CrossOrigin
public class BookingController {
	 @Autowired
	 private BookingService bookingService;

	    @GetMapping("/{id}")
	    public ResponseEntity<BookingDto> showBookingDetails(@PathVariable int id) {
	    	BookingDto foundBookingDto=bookingService.getBookingById(id);
			if(foundBookingDto==null)
				return new ResponseEntity<>(HttpStatus.valueOf(404));
			else
				return new ResponseEntity<>(foundBookingDto,HttpStatus.valueOf(200));
	    }

	    @PostMapping
	    public ResponseEntity<BookingDto> bookAccommodation(@RequestBody BookingDto bookingDto) {
	    	BookingDto savedDto=bookingService.saveBooking(bookingDto);
	        
	        return new ResponseEntity<>(savedDto,HttpStatus.valueOf(200));
 
	    }
	    @PatchMapping("/{id}")
	    public ResponseEntity<BookingDto> cancelBooking(@PathVariable int id) {
	    	try {
	    		BookingDto cancelledDto=bookingService.cancelBookingById(id);
	    		return new ResponseEntity<>(cancelledDto,HttpStatus.valueOf(200));
	    	}
	    	catch(Exception e) {
	    		return new ResponseEntity<>(HttpStatus.valueOf(404));
	    	}
	    }
	    @GetMapping("/{id}/history")
	    public ResponseEntity<List<BookingDto>> showBookingHistory(@PathVariable int id) {
	    	 List<BookingDto> bookings = bookingService.getBookingByUserId(id);
	         return ResponseEntity.ok(bookings);
	    }
	    	

}
