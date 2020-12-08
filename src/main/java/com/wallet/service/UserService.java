package com.wallet.service;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.wallet.entity.User;

@Component
public interface UserService {
	
  User save(User user);
  
  Optional<User> findByEmail(String email);
}
