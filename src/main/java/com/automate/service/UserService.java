package com.automate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.automate.model.User;
import com.automate.dao.IUserDAO;

@Service
@Transactional
public class UserService implements UserServiceInterface {
	
	@Autowired
	public IUserDAO userDAO;

	@Override
	public List<User> getMatches() {
		// TODO Auto-generated method stub
		return userDAO.getUserQuery();
	}

	@Override
	public User getUserByID(int user_id) {
		// TODO Auto-generated method stub
		User obj = userDAO.getUserByID(user_id);
		return obj;
	}

	@Override
	public synchronize boolean addUser(User user) {
		// TODO Auto-generated method stub
		userDAO.addUser(user)
		return true;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userDAO.updateUser(user)
	}

	@Override
	public void deleteUser(int user_id) {
		// TODO Auto-generated method stub
		userDAO.deleteUser(user_id);
	}

}
