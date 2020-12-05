package com.mvc.model;

import java.sql.Date;

public class UserInfo {
	private int iduser;
	private String userName;
	private String password;
	private String role;
	private String email;
	private Date dateJoinGroup;
	private int balance;

	public UserInfo() {

	}

	public UserInfo(String userName, String password, String role, String email) {
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.email = email;
	}

	public UserInfo(int iduser, String userName, String password, String role, String email, int balance) {
		this.iduser = iduser;
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.email = email;
		this.balance = balance;
	}

	public UserInfo(String username, int balance) {
		this.userName = username;
		this.balance = balance;
	}

	public Date getDateJoinGroup() {
		return dateJoinGroup;
	}

	public void setDateJoinGroup(Date dateJoinGroup) {
		this.dateJoinGroup = dateJoinGroup;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
