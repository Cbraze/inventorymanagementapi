package com.inventoryapi.service;


import com.inventoryapi.entity.Credentials;
import com.inventoryapi.entity.User;
import com.inventoryapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;


@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;

	public User register(Credentials cred) throws AuthenticationException {
		User user = new User();
		user.setUsername(cred.getUsername());
		user.setHash(BCrypt.hashpw(cred.getPassword(), BCrypt.gensalt()));
		user.setEmail(cred.getEmail());
		try {
			userRepository.save(user);
			return user;
		} catch (DataIntegrityViolationException e) {
			throw new AuthenticationException("Username not available.");
		}
	}

	public User login(Credentials cred) throws AuthenticationException {
		User foundUser = userRepository.findByUsername(cred.getUsername());
		if (foundUser != null && BCrypt.checkpw(cred.getPassword(), foundUser.getHash())) {
			return foundUser;
		}
		throw new AuthenticationException("Incorrect username or password.");
		
	}

}
