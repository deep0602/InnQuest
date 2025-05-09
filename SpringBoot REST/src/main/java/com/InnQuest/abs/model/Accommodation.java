package com.intellect.abs.model;

import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accommodations")
public class Accommodation {
	   @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	   private Integer accommodationId;
	    private String accommodationName;
	    private String description;
	    private String location;
	    private String amenities;
	    private double pricePerNight;
	    private double distance;
	    private double rating;
	    private Date checkInDate;
	    private Date checkOutDate;
	    
	    
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "user_id")
	    private User user;
}
