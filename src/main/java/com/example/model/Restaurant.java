package com.example.model;

public class Restaurant {

	private int id;
	private String name;
	private String login;
	private String passwordHash;
	

	public Restaurant() {
		super();
	}

	public Restaurant(int id, String name, String login, String passwordHash) {
		super();
		this.id = id;
		this.name = name;
		this.login = login;
		this.passwordHash = passwordHash;
	}
	
	public Restaurant(TestRequest request) {
		this.id=request.getRestaurantid();
		this.login=request.getLogin();
		this.name=request.getRestaurantName();
		this.passwordHash=request.getPasswordHash();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", login=" + login + ", passwordHash=" + passwordHash + "]";
	}
	
	
	
	
	
	
	
	
}
