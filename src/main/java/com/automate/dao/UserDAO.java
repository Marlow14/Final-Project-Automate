package com.automate.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.automate.model.User;

@Repository
public class UserDAO implements IUserDAO {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public User getUserById(int userId) {
		
		return hibernateTemplate.get(User.class, userId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		
		String hql = "FROM User as u ORDER BY u.userId";
		return (List<User>) hibernateTemplate.find(hql);
	}
	
	@Override
	public boolean addUser(User user) {
		hibernateTemplate.save(user);
		return true;
	}
	
	@Override
	public void updateUser(User user) {
		User record = getUserById(user.getUserId());

		record.setFirstName(user.getFirstName());
		record.setLastName(user.getLastName());
		record.setGender(user.getGender());
		record.setCellPhone(user.getCellPhone());
		record.setEmail(user.getEmail());
		record.setHomeAddress(user.getHomeAddress());
		record.setWorkAddress(user.getWorkAddress());
		record.setUserName(user.getUserName());
		record.setPassword(user.getPassword());

		hibernateTemplate.update(record);
	}

	@Override
	public void deleteUser(int userId) {

		hibernateTemplate.delete(getUserById(userId));

	}

}
