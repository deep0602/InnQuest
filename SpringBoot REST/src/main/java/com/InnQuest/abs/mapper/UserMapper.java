/**
 * 
 */
package com.intellect.abs.mapper;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.intellect.abs.dto.UserDto;
import com.intellect.abs.model.User;

public class UserMapper 
{
	// implement the dto to entity_model & vice-versa here
	public static UserDto mapToUserDto(User user) {
		
		return new UserDto(
			user.getId(),
			user.getFirstName(),
			user.getLastName(),
			user.getPassword(),
			user.getEmail(),
			user.getPhoneNumber(),
			user.getRoles().stream().map(role -> role.getRoleName()).collect(Collectors.toSet()),
			user.getAccomodations().stream().map(accommodation -> accommodation.getAccommodationId()).collect(Collectors.toList()),
			user.getBookings().stream().map(booking -> booking.getBookingId()).collect(Collectors.toList()),
			user.getPayments().stream().map(payment -> payment.getPaymentId()).collect(Collectors.toList())
		);
	}
	public static User mapToUser(UserDto userDto) {
		
		return new User(
			userDto.getId(),
			userDto.getFirstName(),
			userDto.getLastName(),
			userDto.getPassword(),
			userDto.getEmail(),
			userDto.getPhoneNumber(),
			new TreeSet<>((r1, r2) -> r1.getRoleName().compareTo(r2.getRoleName())),
			new ArrayList<>(),
			new ArrayList<>(),
			new ArrayList<>());
	}
}
