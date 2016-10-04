package com.automate.dao;

import java.util.List;

import com.automate.model.User;

public interface UserDAOInterface {
	
	User getUserById(int userId);
	String addUser(User user);
	String updateUser(User user);
	String deleteUser(int userId);
	List<User> getMatches();
	boolean verifyPassword();
	
}