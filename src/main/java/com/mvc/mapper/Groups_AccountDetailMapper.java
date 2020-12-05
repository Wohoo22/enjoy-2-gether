package com.mvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.mvc.model.Groups_Accounts;

public class Groups_AccountDetailMapper implements RowMapper<Groups_Accounts> {

	public Groups_Accounts mapRow(ResultSet rs, int num) throws SQLException {
		// TODO Auto-generated method stub
		int idgroup = rs.getInt("idgroup");
		int idaccount = rs.getInt("idaccount");
		String email = rs.getString("email");
		String password = rs.getString("password");
		java.sql.Date expiredDate = rs.getDate("expiredDate");

		return new Groups_Accounts(idgroup, idaccount, email, password, expiredDate);
	}

}
