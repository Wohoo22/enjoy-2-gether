package com.mvc.model;

import java.sql.Date;

public class Groups_Users {
	private int idgroup;
	private int iduser;
	private String username;
	private int balance;
	private Date dateJoinGroup;

	public Groups_Users() {

	}

	public Groups_Users(int idgroup, int iduser) {
		this.idgroup = idgroup;
		this.iduser = iduser;
	}

	public Groups_Users(int idgroup, int iduser, String username, int balance) {
		this.idgroup = idgroup;
		this.iduser = iduser;
		this.username = username;
		this.balance = balance;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getIdgroup() {
		return idgroup;
	}

	public void setIdgroup(int idgroup) {
		this.idgroup = idgroup;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public Date getDateJoinGroup() {
		return dateJoinGroup;
	}

	public void setDateJoinGroup(Date dateJoinGroup) {
		this.dateJoinGroup = dateJoinGroup;
	}

}
