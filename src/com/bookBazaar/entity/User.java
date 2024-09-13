package com.bookBazaar.entity;

import java.sql.Date;

public class User {
	private int id;
	private String name;
	private String email;
	private String password;
	private int role;
	private Date date;
	
	public User() {}
	
	public User(int id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	public User(int id, String name, String email, int role) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.role = role;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public void setUserDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return this.getId() + " " + this.getName() + " " + this.getRole();
	}
}
