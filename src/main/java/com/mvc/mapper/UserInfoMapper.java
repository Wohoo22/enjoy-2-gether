package com.mvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.tree.RowMapper;

import com.mvc.model.UserInfo;

public class UserInfoMapper implements org.springframework.jdbc.core.RowMapper<UserInfo> {

	public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		String userName = rs.getString("USERNAME");
		String password = rs.getString("PASSWORD");
		String role = rs.getString("role");
		String email = rs.getString("email");
		return new UserInfo(userName, password, role, email);
	}

}
