package com.mvc.model;

import java.time.LocalDate;
import java.util.Date;

public class Account {
	private int idaccount;
	private String email;
	private String password;
	private java.sql.Date signedDate;
	private java.sql.Date expiredDate;
	private String service;
	private int idgroup;

	public Account() {

	}

	public Account(String email, String password, java.sql.Date expiredDate) {
		this.email = email;
		this.password = password;
		this.expiredDate = expiredDate;
	}

	public Account(int idaccount, String email, String password, java.sql.Date signedDate, java.sql.Date expiredDate,
			String service) {
		this.idaccount = idaccount;
		this.email = email;
		this.password = password;
		this.signedDate = signedDate;
		this.expiredDate = expiredDate;
		this.service = service;
	}

	public int getIdaccount() {
		return idaccount;
	}

	public void setIdaccount(int idaccount) {
		this.idaccount = idaccount;
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

	public java.sql.Date getSignedDate() {
		return signedDate;
	}

	public void setSignedDate(java.sql.Date signedDate) {
		this.signedDate = signedDate;
	}

	public java.sql.Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(java.sql.Date expiredDate) {
		this.expiredDate = expiredDate;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public int getIdgroup() {
		return idgroup;
	}

	public void setIdgroup(int idgroup) {
		this.idgroup = idgroup;
	}

}
