package com.mvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mvc.model.Comment;
import com.mvc.model.Post;

public class CommentMapper implements org.springframework.jdbc.core.RowMapper<Comment> {

	public Comment mapRow(ResultSet rs, int num) throws SQLException {
		// TODO Auto-generated method stub
		int idcomment = rs.getInt("idcomment");
		int idpost = rs.getInt("idpost");
		String commentDetail = rs.getString("commentDetail");
		String username = rs.getString("username");
		return new Comment(idcomment, idpost, commentDetail, username);
	}

}
