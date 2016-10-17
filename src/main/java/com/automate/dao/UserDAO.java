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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		
		String hql = "FROM User as u ORDER BY u.userId";
		return (List<User>) hibernateTemplate.find(hql);
	}
	
	@Override
	public int addUser(User user) {
		int savedId = (int) hibernateTemplate.save(user);
		return savedId;
	}

	@Override
	public void updateUser(User user) {
		User record = getUserById(user.getUserId());

		record.setFirstName(user.getFirstName());
		record.setLastName(user.getLastName());
//		record.setGender(user.getGender());
		record.setCellPhone(user.getCellPhone());
		record.setEmail(user.getEmail());
		record.setHomeAddress(user.getHomeAddress());
		record.setWorkAddress(user.getWorkAddress());
		record.setHomeLat(user.getHomeLat());
		record.setHomeLng(user.getHomeLng());
		record.setWorkLat(user.getWorkLat());
		record.setWorkLng(user.getWorkLng());
//		record.setUserName(user.getUserName());
		record.setPassword(user.getPassword());

		hibernateTemplate.update(record);
	}

	@Override
	public void deleteUser(int userId) {
		hibernateTemplate.delete(getUserById(userId));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getHomeMatches(String userHomeLat, String userHomeLng, String userWorkLat, String userWorkLng) {
		
		String hql = "SELECT U.userName, U.firstName, U.gender, U.cellPhone, ROUND(( 3959 * acos( cos( radians(" + userHomeLat + ") ) * cos( radians( homeLat ) ) * cos( radians( homeLng ) - radians(" + userHomeLng + ") ) + sin( radians(" + userHomeLat + ") ) * sin( radians( homeLat ) ) ) ), 2) AS home_distance, ROUND(( 3959 * acos( cos( radians(" + userWorkLat + ") ) * cos( radians( workLat ) ) * cos( radians( workLng ) - radians(" + userWorkLng + ") ) + sin( radians(" + userWorkLat + ") ) * sin( radians( workLat ) ) ) ), 2) AS work_distance, (ROUND(( 3959 * acos( cos( radians(" + userHomeLat + ") ) * cos( radians( homeLat ) ) * cos( radians( homeLng ) - radians(" + userHomeLng + ") ) + sin( radians(" + userHomeLat + ") ) * sin( radians( homeLat ) ) ) ), 2) + ROUND(( 3959 * acos( cos( radians(" + userWorkLat + ") ) * cos( radians( workLat ) ) * cos( radians( workLng ) - radians(" + userWorkLng + ") ) + sin( radians(" + userWorkLat + ") ) * sin( radians( workLat ) ) ) ), 2)) AS total_distance, U.email FROM User U ORDER BY total_distance";
		return (List<User>) hibernateTemplate.find(hql);
	}
	
/*	@SuppressWarnings("unchecked")
	@Override
	public List<User> getWorkMatches(String userWorkLat, String userWorkLng) {
		
		String hql = "SELECT userId, ( 3959 * acos( cos( radians(" + userWorkLat + ") ) * cos( radians( workLat ) ) * cos( radians( workLng ) - radians(" + userWorkLng + ") ) + sin( radians(" + userWorkLat + ") ) * sin( radians( workLat ) ) ) ) AS distance FROM User ORDER BY distance";
		return (List<User>) hibernateTemplate.find(hql);
	}*/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> verifyPassword(String userName, String password) {
		String hql = "FROM User where userName = '" +  userName + "'";
		return (List<User>) hibernateTemplate.find(hql);
	}
	
}
