package com.automate.service;

import java.util.List;

import com.automate.model.User;

public interface UserServiceInterface {
	
	List<User> getAllUsers();
	User getUserById(int userId);
	boolean addUser(User user);
	void updateUser(User user);
	void deleteUser(int userId);

}
