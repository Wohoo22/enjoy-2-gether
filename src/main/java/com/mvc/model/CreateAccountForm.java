package com.mvc.model;

public class CreateAccountForm {
	private String email;
	private String password;
	private int idgroup;
	private int signedDay;
	private int signedMonth;
	private int signedYear;
	private int expiredDay;
	private int expiredMonth;
	private int expiredYear;

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

	public int getIdgroup() {
		return idgroup;
	}

	public void setIdgroup(int idgroup) {
		this.idgroup = idgroup;
	}

	public int getSignedDay() {
		return signedDay;
	}

	public void setSignedDay(int signedDay) {
		this.signedDay = signedDay;
	}

	public int getSignedMonth() {
		return signedMonth;
	}

	public void setSignedMonth(int signedMonth) {
		this.signedMonth = signedMonth;
	}

	public int getSignedYear() {
		return signedYear;
	}

	public void setSignedYear(int signedYear) {
		this.signedYear = signedYear;
	}

	public int getExpiredDay() {
		return expiredDay;
	}

	public void setExpiredDay(int expiredDay) {
		this.expiredDay = expiredDay;
	}

	public int getExpiredMonth() {
		return expiredMonth;
	}

	public void setExpiredMonth(int expiredMonth) {
		this.expiredMonth = expiredMonth;
	}

	public int getExpiredYear() {
		return expiredYear;
	}

	public void setExpiredYear(int expiredYear) {
		this.expiredYear = expiredYear;
	}

}
