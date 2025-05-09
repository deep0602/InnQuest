package com.intellect.abs.service;

import java.sql.Date;
import java.util.List;

import com.intellect.abs.dto.AccommodationDTO;

public interface AccommodationService {

    AccommodationDTO createAccommodation(AccommodationDTO accommodationDTO);
    
    List<AccommodationDTO> getAllAccommodations();
    
    AccommodationDTO getAccommodationById(int accommodationId);
    
    void deleteAccommodation(int accommodationId);
   
    AccommodationDTO updateAccommodation(int accommodationId, AccommodationDTO accommodationDTO);
    
    AccommodationDTO updateAccommodationName(int accommodationId, String name);
   List<AccommodationDTO> findAvailableAccommodations(Date checkinDate, Date checkoutDate);
   List<AccommodationDTO> findAccommodationByUser(int id);
}
