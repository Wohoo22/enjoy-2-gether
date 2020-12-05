package com.mvc.dao;

import java.util.List;

import com.mvc.model.Post;

public interface PostDao {
	List<Post> getGroupPosts(int idgroup, int rowNum);

	void createPost(String postDetail);

	int getNextAutoIncrementPostId();

	void mapPostIdWithGroupId(int idpost, int idgroup);

	void mapPostIdWithUserId(int idpost, int iduser);

	int GetGroupIdByPostId(int idpost);

	void deletePost(int idpost);

	void deletePostIdAndGroupIdMap(int idpost);

	void deletePostIdAndUserIdMap(int idpost);

	int GetPostUserIdByPostId(int idpost);

	List<Integer> getAllCommentIdOfPost(int idpost);

	String getPostDetailByPostId(int idpost);
}
