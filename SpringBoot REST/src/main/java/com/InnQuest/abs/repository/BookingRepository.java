package com.intellect.abs.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



import com.intellect.abs.model.Booking;

public interface BookingRepository extends JpaRepository<Booking,Integer>{
	 List<Booking> findByUserId(int id);
}
