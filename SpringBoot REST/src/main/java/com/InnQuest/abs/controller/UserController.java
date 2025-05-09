/**
 * 
 */
package com.intellect.abs.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.intellect.abs.dto.UserDto;
import com.intellect.abs.dto.UserLoginRequestDto;
import com.intellect.abs.model.Role;
import com.intellect.abs.service.UserService;


@RestController
@RequestMapping("/")
@CrossOrigin
public class UserController 
{	
	@Autowired
	private UserService UserService;
	
	// create User REST API
	@PostMapping("/register")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto UserDto) {
		// call the create User service here
		UserDto savedUserDto = UserService.createUser(UserDto);
		return new ResponseEntity<UserDto>(savedUserDto, HttpStatus.CREATED) ;
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserDto> loginUser(@RequestBody UserLoginRequestDto loginRequest) {
		// call the create User service here
		UserDto UserDto = UserService.loginUser(loginRequest);
		return ResponseEntity.ok(UserDto);
	}
	
	// read User REST API
	// if both path variable & parameter have same name, no need of pathVariable
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") int UserId) {
		// call the get User by id service here
		UserDto user = UserService.getUserDtoById(UserId);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("check-email/{email}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("email") String userEmail) {
		// call the get User by id service here
		UserDto user = UserService.checkEmail(userEmail);
		return ResponseEntity.ok(user);
	}
	
	// read all Users
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers(){
		// call the get all Users service here
		List<UserDto> Users = UserService.getAllUsers();
		return ResponseEntity.ok(Users);
	}
	
	//update the User by id
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable int id, @RequestBody UserDto updatedUser) 
	{
		// call the update User service here
		UserDto UserDto = UserService.updateUser(id, updatedUser);
		return ResponseEntity.ok(UserDto);
	}
	
	//delete the User by id
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable int id) {
		// call the delete User service here
		UserService.deleteUser(id);
		return ResponseEntity.ok("User deleted successfully");
	}
	
	@PostMapping("/{userId}/roles")
    public ResponseEntity<Void> addRoleToUser(@PathVariable int userId, @RequestBody Role role) {
        UserService.addRoleToUser(userId, role);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/roles")
    public ResponseEntity<Void> removeRoleFromUser(@PathVariable int userId, @RequestBody Role role) {
        UserService.removeRoleFromUser(userId, role);
        return ResponseEntity.ok().build();
    }
}