package com.mvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mvc.model.Groups_Users;

public class Groups_UsersMapper implements RowMapper<Groups_Users> {

	public Groups_Users mapRow(ResultSet rs, int num) throws SQLException {
		// TODO Auto-generated method stub
		int iduser = rs.getInt("iduser");
		int idgroup = rs.getInt("idgroup");

		return new Groups_Users(idgroup, iduser);
	}

}
