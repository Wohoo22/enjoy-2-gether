package com.mvc.model;

public class Comment {
	private int idcomment;
	private int idpost;
	private String commentDetail;
	private String username;

	public Comment() {

	}

	public Comment(int idcomment, int idpost, String commentDetail, String username) {
		this.idcomment = idcomment;
		this.idpost = idpost;
		this.commentDetail = commentDetail;
		this.username = username;
	}

	public int getIdpost() {
		return idpost;
	}

	public void setIdpost(int idpost) {
		this.idpost = idpost;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getIdcomment() {
		return idcomment;
	}

	public void setIdcomment(int idcomment) {
		this.idcomment = idcomment;
	}

	public String getCommentDetail() {
		return commentDetail;
	}

	public void setCommentDetail(String commentDetail) {
		this.commentDetail = commentDetail;
	}
}
