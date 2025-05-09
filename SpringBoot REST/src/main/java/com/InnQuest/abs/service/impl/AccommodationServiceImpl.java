package com.intellect.abs.service.impl;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intellect.abs.dto.AccommodationDTO;
import com.intellect.abs.mapper.AccommodationMapper;
import com.intellect.abs.model.Accommodation;
import com.intellect.abs.model.User;
import com.intellect.abs.repository.AccommodationRepository;
import com.intellect.abs.repository.UserRepository;
import com.intellect.abs.service.AccommodationService;

import jakarta.transaction.Transactional;


@Service
public class AccommodationServiceImpl implements AccommodationService {

    @Autowired
    private AccommodationRepository accommodationRepository;
    @Autowired
    private UserRepository UserRepository;

    @Override
    public AccommodationDTO createAccommodation(AccommodationDTO accommodationDTO) {
        Accommodation accommodation = AccommodationMapper.toEntity(accommodationDTO);
      User user=  UserRepository.findById(accommodationDTO.getUserId()).get();
        accommodation.setUser(user);
        Accommodation savedAccommodation = accommodationRepository.save(accommodation);
        return AccommodationMapper.toDTO(savedAccommodation);
    }

    @Override
    public List<AccommodationDTO> getAllAccommodations() {
        List<Accommodation> accommodations = accommodationRepository.findAll();
        return accommodations.stream()
                             .map(AccommodationMapper::toDTO)
                             .collect(Collectors.toList());
    }

    @Override
    public AccommodationDTO getAccommodationById(int accommodationId) {
        Optional<Accommodation> accommodation = accommodationRepository.findById(accommodationId);
        return accommodation.map(AccommodationMapper::toDTO)
                            .orElseThrow(() -> new RuntimeException("Accommodation not found"));
    }
    
    @Transactional
    @Override
    public void deleteAccommodation(int accommodationId) {
       Accommodation accommodation = accommodationRepository.findById(accommodationId)
            .orElseThrow(() -> new RuntimeException("Accommodation not found"));
        accommodationRepository.deleteByAccommodationId(accommodationId);
    }

    @Override
    public AccommodationDTO updateAccommodation(int accommodationId, AccommodationDTO accommodationDTO) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId)
            .orElseThrow(() -> new RuntimeException("Accommodation not found"));
        User user=  UserRepository.findById(accommodationDTO.getUserId()).get();
        accommodation.setUser(user);
        accommodation.setAccommodationName(accommodationDTO.getAccommodationName());
        accommodation.setDescription(accommodationDTO.getDescription());
        accommodation.setDistance(accommodationDTO.getDistance());
        accommodation.setLocation(accommodationDTO.getLocation());
        accommodation.setRating(accommodationDTO.getRating());
        accommodation.setPricePerNight(accommodationDTO.getPricePerNight());
        accommodation.setCheckInDate(accommodationDTO.getCheckInDate());
        accommodation.setCheckOutDate(accommodationDTO.getCheckOutDate());
        Accommodation updatedAccommodation = accommodationRepository.save(accommodation);
        return AccommodationMapper.toDTO(updatedAccommodation);
    }

    @Override
    public AccommodationDTO updateAccommodationName(int accommodationId, String name) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId)
            .orElseThrow(() -> new RuntimeException("Accommodation not found"));
        accommodation.setAccommodationName(name);
        Accommodation updatedAccommodation = accommodationRepository.save(accommodation);
        return AccommodationMapper.toDTO(updatedAccommodation);
    }
    public List<AccommodationDTO> findAvailableAccommodations(Date checkinDate, Date checkoutDate) {;
        List<Accommodation> accommodations=accommodationRepository.findAvailableAccommodations(checkinDate, checkoutDate);
        return accommodations.stream()
                .map(AccommodationMapper::toDTO)
                .collect(Collectors.toList());
    }

	@Override
	public List<AccommodationDTO> findAccommodationByUser(int id) {
		  List<Accommodation> accommodations = accommodationRepository.findAllByUserId(id);
	        return accommodations.stream()
	                             .map(AccommodationMapper::toDTO)
	                             .collect(Collectors.toList());
	}
}
