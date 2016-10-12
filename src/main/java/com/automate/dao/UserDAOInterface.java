package com.automate.dao;

import java.util.List;

import com.automate.model.User;

public interface UserDAOInterface {
	
	List<User> getAllUsers();
	User getUserById(int userId);
	public int addUser(User user);
	void updateUser(User user);
	void deleteUser(int userId);
	public List<User> getHomeMatches(String userHomeLat, String userHomeLng);
//	public List<User> getWorkMatches(String userWorkLat, String userWorkLng);
	public List<User> verifyPassword(String userName, String password);
}
