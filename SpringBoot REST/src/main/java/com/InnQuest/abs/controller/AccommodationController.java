package com.intellect.abs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.intellect.abs.dto.AccommodationDTO;
import com.intellect.abs.service.AccommodationService;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/accommodations")
@CrossOrigin
public class AccommodationController {

    @Autowired
    private AccommodationService accommodationService;

    @PostMapping
    public ResponseEntity<AccommodationDTO> createAccommodation(@RequestBody AccommodationDTO accommodationDTO) {
        AccommodationDTO createdAccommodation = accommodationService.createAccommodation(accommodationDTO);
        return ResponseEntity.ok(createdAccommodation);
    }

    @GetMapping
    public ResponseEntity<List<AccommodationDTO>> getAllAccommodations() {
        List<AccommodationDTO> accommodations = accommodationService.getAllAccommodations();
        return ResponseEntity.ok(accommodations);
    }
    @GetMapping("/owner/{id}")
    public ResponseEntity<List<AccommodationDTO>> getAccommodationByOwner(@PathVariable int id){
        List<AccommodationDTO> accommodations = accommodationService.findAccommodationByUser(id);
        return ResponseEntity.ok(accommodations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccommodationDTO> getAccommodationById(@PathVariable("id") int accommodationId) {
        AccommodationDTO accommodation = accommodationService.getAccommodationById(accommodationId);
        return ResponseEntity.ok(accommodation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccommodation(@PathVariable("id") int accommodationId) {
        accommodationService.deleteAccommodation(accommodationId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccommodationDTO> updateAccommodation(
            @PathVariable("id") int accommodationId, 
            @RequestBody AccommodationDTO accommodationDTO) {
        AccommodationDTO updatedAccommodation = accommodationService.updateAccommodation(accommodationId, accommodationDTO);
        return ResponseEntity.ok(updatedAccommodation);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AccommodationDTO> updateAccommodationName(
            @PathVariable("id") int accommodationId, 
            @RequestParam("name") String name) {
        AccommodationDTO updatedAccommodation = accommodationService.updateAccommodationName(accommodationId, name);
        return ResponseEntity.ok(updatedAccommodation);
    }
    @GetMapping("/search")
    public ResponseEntity<List<AccommodationDTO>> getAvailableAccommodations(
            @RequestParam("checkinDate") Date checkinDate,
            @RequestParam("checkoutDate") Date checkoutDate) {
        
    	List<AccommodationDTO> accommodations = accommodationService.findAvailableAccommodations(checkinDate, checkoutDate);
        return ResponseEntity.ok(accommodations);
    }
}
