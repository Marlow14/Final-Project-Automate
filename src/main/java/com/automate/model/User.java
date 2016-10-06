package com.automate.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id")
	private int userId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "gender")
	private String gender;

	@Column(name = "cell_phone")
	private String cellPhone;

	@Column(name = "home_address")
	private String homeAddress;

	@Column(name = "work_address")
	private String workAddress;
	
	@Column(name = "home_lat")
	private String homeLat;
	
	@Column(name = "home_lng")
	private String homeLng;

	@Column(name = "work_lat")
	private String workLat;
	
	@Column(name = "work_lng")
	private String workLng;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	private String email;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	
	public String getHomeLat() {
		return homeLat;
	}

	public void setHomeLat(String homeLat) {
		this.homeLat = homeLat;
	}
	
	public String getHomeLng() {
		return homeLng;
	}

	public void setHomeLng(String homeLng) {
		this.homeLng = homeLng;
	}

	public String getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}
	
	public String getWorkLat() {
		return workLat;
	}

	public void setWorkLat(String workLat) {
		this.workLat = workLat;
	}
	
	public String getWorkLng() {
		return workLng;
	}

	public void setWorkLng(String workLng) {
		this.workLng = workLng;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
