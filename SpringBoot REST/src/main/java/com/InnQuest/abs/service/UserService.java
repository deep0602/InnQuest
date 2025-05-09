/**
 * 
 */
package com.intellect.abs.service;

import java.util.Set;
import java.util.List;

import org.springframework.stereotype.Service;

import com.intellect.abs.dto.UserDto;
import com.intellect.abs.dto.UserLoginRequestDto;
import com.intellect.abs.model.Role;
import com.intellect.abs.model.User;

@Service
public interface UserService 
{
	UserDto createUser(UserDto userDto);
	UserDto loginUser(UserLoginRequestDto userRequest);
	User getUserById(int id);
	UserDto getUserDtoById(int id);
	List<UserDto> getAllUsers();
	UserDto updateUser(int userId, UserDto updatedUser);
	void deleteUser(int userId);
	void addUserWithRoles(User user, Set<Role> roles);
	void addRoleToUser(int userId, Role role);
	void removeRoleFromUser(int userId, Role role);
	UserDto checkEmail(String email);
}
