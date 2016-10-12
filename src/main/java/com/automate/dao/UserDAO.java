package com.automate.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.automate.model.User;

@Repository
public class UserDAO implements UserDAOInterface {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;
	
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
		record.setHomeLat(user.getHomeLat());
		record.setHomeLng(user.getHomeLng());
		record.setWorkLat(user.getWorkLat());
		record.setWorkLng(user.getWorkLng());
		record.setUserName(user.getUserName());
		record.setPassword(user.getPassword());

		hibernateTemplate.update(record);
	}

	@Override
	public void deleteUser(int userId) {
		hibernateTemplate.delete(getUserById(userId));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getHomeMatches(String userHomeLat, String userHomeLng) {
		
		String hql = "SELECT U.firstName, U.lastName, U.email, ( 3959 * acos( cos( radians(" + userHomeLat + ") ) * cos( radians( homeLat ) ) * cos( radians( homeLng ) - radians(" + userHomeLng + ") ) + sin( radians(" + userHomeLat + ") ) * sin( radians( homeLat ) ) ) ) AS distance FROM User U ORDER BY distance";
		return (List<User>) hibernateTemplate.find(hql);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getWorkMatches(String userWorkLat, String userWorkLng) {
		
		String hql = "SELECT userId, ( 3959 * acos( cos( radians(" + userWorkLat + ") ) * cos( radians( workLat ) ) * cos( radians( workLng ) - radians(" + userWorkLng + ") ) + sin( radians(" + userWorkLat + ") ) * sin( radians( workLat ) ) ) ) AS distance FROM User ORDER BY distance";
		return (List<User>) hibernateTemplate.find(hql);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> verifyPassword(String userName, String password) {
		String hql = "FROM User where userName = '" +  userName + "' AND password = '" + password + "'";
		return (List<User>) hibernateTemplate.find(hql);
	}
	
}
