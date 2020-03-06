package com.inventoryapi.service;


import com.inventoryapi.entity.User;
import com.inventoryapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

	@Autowired
	UserRepository repo;
	
	public User getUser(Long id) {
		return repo.findOne(id);
	}
	
	public Iterable<User> getUsers() {
		return repo.findAll();
	}
	
	public void deleteUser(Long id) {
		repo.delete(id);
	}
	
	public User updateUser(Long id, User user) {
		User foundUser = repo.findOne(id);
		if (foundUser != null) {
			foundUser.setUsername(user.getUsername());
			repo.save(foundUser);
		}
		return foundUser;
	}

	
	public User updateProfilePicture(Long userId, String url) throws Exception {
		User user = repo.findOne(userId);
		if (user == null) {
			throw new Exception("User does not exsist.");
		}
		user.setProfilePictureUrl(url);
		return repo.save(user);
	}

}
