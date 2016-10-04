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
	public synchronized boolean addUser(User user) {
		userDAO.addUser(user);
		return true;
	}

	@Override
	public void updateUser(User user) {
		userDAO.updateUser(user);

	}

	@Override
	public void deleteUser(int userId) {
		userDAO.deleteUser(userId);

	}
	
}
