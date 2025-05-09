/**
 * 
 */
package com.intellect.abs.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intellect.abs.dto.UserDto;
import com.intellect.abs.dto.UserLoginRequestDto;
import com.intellect.abs.model.User;
import com.intellect.abs.model.Role;
import com.intellect.abs.exception.ResourceNotFoundException;
import com.intellect.abs.mapper.UserMapper;
import com.intellect.abs.repository.UserRepository;
import com.intellect.abs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class UserServiceImpl implements UserService 
{
	@Autowired
	private UserRepository userRepository;
	// provide implementation to the UserService interface
	
	@Override
	public UserDto createUser(UserDto userDto) 
	{	
		User user = UserMapper.mapToUser(userDto);
		user.setRoles(userDto.getRoles().stream()
				.map(role -> new Role(null, role, user))
				.collect(Collectors.toSet()));
	 	User savedUser = userRepository.save(user);
		return UserMapper.mapToUserDto(savedUser);
	}
	
	@Override
	@Transactional(readOnly = true)
	public UserDto loginUser(UserLoginRequestDto userRequest) 
	{
		User user = userRepository.findByEmailAndPassword(userRequest.getEmail(), userRequest.getPassword());
		if(user==null) {
				throw new ResourceNotFoundException("No user exist with the given credentials");
		}
		return UserMapper.mapToUserDto(user);
	}

	@Override
	@Transactional(readOnly = true)
	public User getUserById(int id) 
	{
		User user = userRepository.findById(id)
			.orElseThrow(()->
				new ResourceNotFoundException("No user exist with the given id : " + id));
		return user;
	}
	@Override
	@Transactional(readOnly = true)
	public UserDto getUserDtoById(int id) 
	{
		User user = userRepository.findById(id)
			.orElseThrow(()->
				new ResourceNotFoundException("No user exist with the given id : " + id));
		return UserMapper.mapToUserDto(user);
	}

	@Override

	public List<UserDto> getAllUsers() 
	{
		List<User>users = userRepository.findAll();
		Collections.sort(users, (user1, user2)->{
			return user1.getId()-user1.getId();
		});
		return users.stream().map((user) -> UserMapper.mapToUserDto(user))
			.collect(Collectors.toList());            
	}

	@Override
	public UserDto updateUser(int userId, UserDto updatedUser) {
		
		User user = userRepository.findById(userId).orElseThrow(()->
			new ResourceNotFoundException("No user exist with the given id : " + userId));
		user.setFirstName(updatedUser.getFirstName());
		user.setLastName(updatedUser.getLastName());
		user.setEmail(updatedUser.getEmail());
		User updatedUserObj = userRepository.save(user);
		return UserMapper.mapToUserDto(updatedUserObj);
	}
	
	@Override
	public void deleteUser(int userId) {
		User user = userRepository.findById(userId).orElseThrow(()->
		 	new ResourceNotFoundException("No user exist with the given id : " + userId));
		System.out.println(user.getId());
		userRepository.deleteById(userId);
	}
	
	@Override
	public void addUserWithRoles(User user, Set<Role> roles) {
        user.getRoles().addAll(roles);
        userRepository.save(user);
    }
	@Transactional
	 public void addRoleToUser(int userId, Role role) {
	        User user = getUserById(userId);
	        role.setUser(user);
	        user.getRoles().add(role);
	        userRepository.save(user);
	    }
	@Transactional
	public void removeRoleFromUser(int userId, Role role) {
	    User user = getUserById(userId);
	    role.setUser(user);
	    user.getRoles().remove(role);
	    userRepository.save(user);
	 }
	
	public UserDto checkEmail(String email) {
		Optional<User> isUser = userRepository.findByEmail(email);
		if(!isUser.isPresent()) {
			return null;
		}
		return UserMapper.mapToUserDto(isUser.get());
		 
		
	}
}