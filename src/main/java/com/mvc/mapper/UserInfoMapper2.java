package com.mvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mvc.model.UserInfo;

public class UserInfoMapper2 implements RowMapper<UserInfo> {

	public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int iduser = rs.getInt("iduser");
		String userName = rs.getString("USERNAME");
		String password = rs.getString("PASSWORD");
		String role = rs.getString("role");
		String email = rs.getString("email");
		int balance = rs.getInt("balance");
		return new UserInfo(iduser, userName, password, role, email, balance);
	}
}
