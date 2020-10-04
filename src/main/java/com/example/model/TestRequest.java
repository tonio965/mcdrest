package com.example.model;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

@Validated
public class TestRequest {
	
	private int restaurantid;
	
	private
	@NotNull(message="name is empty")
	String restaurantName;
	
	private
	@NotNull(message="password is null")
	String passwordHash;
	
	private
	@NotNull(message="login is null")
	String login;

	public int getRestaurantid() {
		return restaurantid;
	}

	public void setRestaurantid(int restaurantid) {
		this.restaurantid = restaurantid;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public String toString() {
		return "TestRequest [restaurantid=" + restaurantid + ", restaurantName=" + restaurantName + ", passwordHash="
				+ passwordHash + "]";
	}
	
	

	
	
}
