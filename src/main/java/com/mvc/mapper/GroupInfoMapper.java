package com.mvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mvc.model.GroupInfo;

public class GroupInfoMapper implements RowMapper<GroupInfo> {

	public GroupInfo mapRow(ResultSet rs, int numRow) throws SQLException {
		// TODO Auto-generated method stub
		int idgroup = rs.getInt("idgroup");
		String groupname = rs.getString("groupname");
		String service = rs.getString("service");
		int memberQuantity = rs.getInt("memberQuantity");
		return new GroupInfo(idgroup, groupname, service, memberQuantity);
	}

}
