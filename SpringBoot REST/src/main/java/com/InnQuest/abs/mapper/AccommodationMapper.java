package com.intellect.abs.mapper;

import com.intellect.abs.dto.AccommodationDTO;
import com.intellect.abs.model.Accommodation;

public class AccommodationMapper {

    public static  AccommodationDTO toDTO(Accommodation accommodation) {
        if (accommodation == null) return null;
        AccommodationDTO dto = new AccommodationDTO();
        dto.setAccommodationId(accommodation.getAccommodationId());
        dto.setAccommodationName(accommodation.getAccommodationName());
        dto.setDescription(accommodation.getDescription());
        dto.setLocation(accommodation.getLocation());
        dto.setCheckInDate(accommodation.getCheckInDate());
        dto.setCheckOutDate(accommodation.getCheckOutDate());
        dto.setAmenities(accommodation.getAmenities());     
        dto.setUserId(accommodation.getUser().getId());
        dto.setPricePerNight(accommodation.getPricePerNight());
        dto.setDistance(accommodation.getDistance());
        dto.setRating(accommodation.getRating());
        return dto;
    }

    public static Accommodation toEntity(AccommodationDTO dto) {
        if (dto == null) return null;
        Accommodation accommodation = new Accommodation();
        accommodation.setAccommodationId(dto.getAccommodationId());
        accommodation.setAccommodationName(dto.getAccommodationName());
        accommodation.setDescription(dto.getDescription());
        accommodation.setLocation(dto.getLocation());
        accommodation.setAmenities(dto.getAmenities());
        accommodation.setUser(null);
        accommodation.setDistance(dto.getDistance());
        accommodation.setRating(dto.getRating());
        accommodation.setPricePerNight(dto.getPricePerNight());
        accommodation.setCheckInDate(dto.getCheckInDate());
        accommodation.setCheckOutDate(dto.getCheckOutDate());
        return accommodation;
    }
}
