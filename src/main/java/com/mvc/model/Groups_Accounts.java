package com.mvc.model;

import java.sql.Date;
import java.time.LocalDate;

public class Groups_Accounts {
	private int idgroup;
	private int idaccount;
	private String email;
	private String password;
	private Date expiredDate;

	public Groups_Accounts() {

	}

	public Groups_Accounts(int idgroup, int idaccount, String email, String password, Date expiredDate) {
		this.idgroup = idgroup;
		this.idaccount = idaccount;
		this.email = email;
		this.password = password;
		this.expiredDate = expiredDate;
	}

	public int getIdgroup() {
		return idgroup;
	}

	public void setIdgroup(int idgroup) {
		this.idgroup = idgroup;
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

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}

}
