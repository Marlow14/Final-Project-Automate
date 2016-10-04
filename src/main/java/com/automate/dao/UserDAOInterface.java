package com.automate.dao;

import java.util.List;

import com.automate.model.User;

public interface UserDAOInterface {
	
	List<User> getAllUsers();
	User getUserById(int userId);
	boolean addUser(User user);
	void updateUser(User user);
	void deleteUser(int userId);
	public List<User> getMatches();
	public boolean verifyPassword();
}
