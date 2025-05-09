package com.intellect.abs.model;
import java.sql.Date;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
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
import lombok.ToString;


@Entity
@Table(name = "bookings")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookingId;

    @Column(name = "accommodationid")
    private int accommodationId;

    @Column(name = "bookingdate")
    private LocalDateTime bookingDate;
    
    @Column(name = "checkindate")
    private Date checkInDate;

    @Column(name = "checkoutdate")
    private Date checkOutDate;

	@Column(name = "totalpeople")
    private int totalPeople;

    @Column(name = "status")
    private String status;

    @Column(name = "totalprice")
    private double totalPrice;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    
   }
