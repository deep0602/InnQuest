package com.intellect.abs.repository;


import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.intellect.abs.model.Accommodation;

import jakarta.transaction.Transactional;

public interface AccommodationRepository extends JpaRepository<Accommodation, Integer> {
	@Query("SELECT a FROM Accommodation a " +
	           "WHERE a.checkInDate <= :checkinDate AND a.checkOutDate >= :checkoutDate")
	 List<Accommodation> findAvailableAccommodations(@Param("checkinDate") Date checkinDate, 
			 @Param("checkoutDate") Date checkoutDate);
	List<Accommodation> findAllByUserId(int userId);
	
	@Transactional
    @Modifying
    @Query("DELETE FROM Accommodation a WHERE a.id = :accommodationId")
    void deleteByAccommodationId(int accommodationId);
}
