package com.mvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mvc.model.Post;
import com.mvc.model.UserInfo;

public class PostMapper implements org.springframework.jdbc.core.RowMapper<Post> {

	public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		int idpost = rs.getInt("idpost");
		String postDetail = rs.getString("postDetail");
		String username = rs.getString("username");

		return new Post(idpost, postDetail, username);
	}

}
