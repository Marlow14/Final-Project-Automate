package com.automate.service;

import java.util.List;

import com.automate.model.User;

public interface UserServiceInterface {
	
	List<User>getMatches();
	User getUserByID(int user_id);
	User addUser(User user);
	User updateUser(User user);
	User deleteUser(int user_id);
	
}
