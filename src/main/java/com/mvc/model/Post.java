package com.mvc.model;

public class Post {
	private int idpost;
	private String postDetail;
	private String username;

	public Post() {

	}

	public Post(int idpost, String postDetail, String username) {
		this.idpost = idpost;
		this.postDetail = postDetail;
		this.username = username;
	}

	public int getIdpost() {
		return idpost;
	}

	public void setIdpost(int idpost) {
		this.idpost = idpost;
	}

	public String getPostDetail() {
		return postDetail;
	}

	public void setPostDetail(String postDetail) {
		this.postDetail = postDetail;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
