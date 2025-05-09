package com.intellect.abs.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class UserDto{

    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phoneNumber;
    
    
    private Set<String> roles = new HashSet<>();
    private List<Integer> accomodationIds = new ArrayList<>();
    private List<Integer> bookingIds = new ArrayList<>(); 
    private List<Integer> paymentIds = new ArrayList<>();
}
