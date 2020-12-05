package com.mvc.dao;

import java.sql.Date;

import com.mvc.model.UserInfo;

public interface UserInfoDao {
	public UserInfo findUserInfo(String username);

	// [USER,AMIN,..]
	public String getUserRoles(String username);

	public int addUser(UserInfo user);

	public UserInfo findUserByEmail(String email);

	public String getUsernameByUsername(String username);

	public String getUserEmailByEmail(String email);

	public int getUserIdByUsername(String username);

	public UserInfo getUsernameAndBalanceByIdUser(int iduser);

	public void insertUserDateJoinGroup(int iduser, Date date);

	public int getVerifyCodeOfEmailByEmail(String email);

	public void saveEmailAndVerifyCode(String email, int code);

	public void updatePasswordByUserEmail(String encodedPassword, String email);

	public void deleteEmailAndVerifyCode(String email);

	public Date getUserDateJoinGroup(int iduser);

	public int getUserBalanceByUsername(String username);

	public void updateUserBalanceByUsername(int balance, String username);

	public void truncateEmailVerifyCodeTable();
}
