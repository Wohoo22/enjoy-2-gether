package com.mvc.dao;

import java.util.List;

import com.mvc.model.Comment;

public interface CommentDao {
	List<Comment> getPostComments(int idpost);

	void createComment(String commentDetail);

	int getNextAutoIncrementCommentId();

	void mapCommentIdWithPostId(int idcomment, int idpost);

	void mapCommentIdWithUserId(int idcomment, int iduser);

	void deleteCommentByCommentId(int idcomment);

	void deleteCommentIdAndPostIdMap(int idcomment);

	void deleteCommentIdAndUserIdMap(int idcomment);

	int getCommentOwnerIdByIdComment(int idcomment);
}
