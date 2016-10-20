package com.automate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.automate.dao.UserDAOInterface;
import com.automate.model.User;


@Service
@Transactional
public class UserService implements UserServiceInterface {
	
	@Autowired
	private UserDAOInterface userDAO;
	
	@Override
	public User getUserById(int userId) {
		User obj = userDAO.getUserById(userId);
		return obj;
	}
	
	@Override
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

	@Override
	public synchronized int addUser(User user) {
		return userDAO.addUser(user);
		
	}

	@Override
	public void updateUser(User user) {
		userDAO.updateUser(user);

	}

	@Override
	public void deleteUser(int userId) {
		userDAO.deleteUser(userId);

	}

	@Override
	public List<User> getMatches(String userHomeLat, String userHomeLng, String userWorkLat, String userWorkLng) {
		return userDAO.getMatches(userHomeLat, userHomeLng, userWorkLat, userWorkLng);
		
	}
	
	
	@Override
	public List<User> verifyPassword(String userName, String password) {
		return userDAO.verifyPassword(userName, password);
	}

}
