package com.mvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mvc.model.UserInfo;

public class UserInfoBalanceMapper implements org.springframework.jdbc.core.RowMapper<UserInfo> {
	public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		String username = rs.getString("USERNAME");
		int balance = rs.getInt("balance");
		return new UserInfo(username, balance);
	}
}
