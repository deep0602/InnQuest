package com.intellect.abs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationDTO {
	private Integer accommodationId;
    private String accommodationName;
    private String description;
    private String location;
    private Date checkInDate;
    private Date checkOutDate;
    private String amenities;
    private double pricePerNight;
    private double distance;
    private double rating;
    private Integer userId;
}
