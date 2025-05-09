package com.intellect.abs.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import com.intellect.abs.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	// Custom query to find user by email and password
    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
    
    Optional<User> findByEmail(@Param("email") String email);
    
}
