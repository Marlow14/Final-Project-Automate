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
	
	@Override
	public User getUserTestById(int userId) {
		
		return hibernateTemplate.get(User.class, userId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getMatches(String homeLat, String homeLng) {
		
		String hql = "SELECT userId, ( 3959 * acos( cos( radians(" + homeLat + ") ) * cos( radians( homeLat ) ) * cos( radians( homeLng ) - radians(" + homeLng + ") ) + sin( radians(" + homeLat + ") ) * sin( radians( homeLat ) ) ) ) AS distance FROM User ORDER BY distance";
		return (List<User>) hibernateTemplate.find(hql);
	}
	
	@Override
	public boolean verifyPassword() {
		//How do I even do this?
		return false;
	}
	
//	Commetted out the code below that query the user locations and arrange them in order of distance from a specific location - 10/5/16
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<User> getMatches() {
//		
//		
////		String hql = "SELECT id, ( 3959 * acos( cos( radians(39) ) * cos( radians( homeLat ) ) * cos( radians( homeLng ) - radians(-77) ) + sin( radians(39) ) * sin( radians( homeLat ) ) ) ) AS distance FROM User HAVING distance < 16 ORDER BY distance LIMIT 0 , 20";
//		
//		String hql = "SELECT userId, ( 3959 * acos( cos( radians(39) ) * cos( radians( homeLat ) ) * cos( radians( homeLng ) - radians(-77) ) + sin( radians(39) ) * sin( radians( homeLat ) ) ) ) AS distance FROM User ORDER BY distance";
//		
////		String hql = "SELECT userId, ( 3959 * acos( cos( radians(39) ) * cos( radians( homeLat ) ) * cos( radians( homeLng ) - radians(-77) ) + sin( radians(39) ) * sin( radians( homeLat ) ) ) ) AS distance FROM User HAVING distance < 16 ORDER BY distance";
//		
//		
//		
//		return (List<User>) hibernateTemplate.find(hql);
//	}
	
}
