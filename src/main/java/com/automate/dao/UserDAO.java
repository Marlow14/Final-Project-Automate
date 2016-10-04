package com.automate.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.automate.model.User;

@Repository
public class UserDAO implements UserDAOInterface {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public User getUserById(int userId) {
		return hibernateTemplate.get(User.class, userId);
	}

	@Override
	public String addUser(User user) {
		hibernateTemplate.save(user);
		return null;
	}

	@Override
	public String updateUser(User user) {
		User record = getUserById(user.getUserId());

		record.setFirstName(user.getFirstName());
		record.setLastName(user.getLastName());
		record.setGender(user.getGender());
		record.setCellPhone(user.getCellPhone());
		record.setHomeAddress(user.getHomeAddress());
		record.setWorkAddress(user.getWorkAddress());
		record.setUserName(user.getUserName());
		record.setPassword(user.getPassword());

		return null;
	}

	@Override
	public String deleteUser(int userId) {
		hibernateTemplate.delete(getUserById(userId));
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getMatches() {
		int distance = 10000;
		int userLat = 0;
		int userLng = 0;
		String hql = "SELECT id, ( 3959 * acos( cos( radians(" + userLat
				+ ") ) * cos( radians( lat ) ) * cos( radians( lng ) - radians(" + userLng + ") ) + sin( radians("
				+ userLat + ") ) * sin( radians( lat ) ) ) ) AS distance FROM users HAVING distance < " + distance
				+ " ORDER BY distance LIMIT 0 , 20;";
		
		return (List<User>) hibernateTemplate.find(hql);
	}

	@Override
	public boolean verifyPassword() {
		//How do I even do this?
		return false;
	}
}
