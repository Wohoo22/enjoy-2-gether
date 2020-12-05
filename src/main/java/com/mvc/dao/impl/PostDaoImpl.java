package com.mvc.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.PostDao;
import com.mvc.mapper.PostMapper;
import com.mvc.model.Post;

@Service
@Transactional
public class PostDaoImpl extends JdbcDaoSupport implements PostDao {
	@Autowired
	public PostDaoImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public List<Post> getGroupPosts(int idgroup, int rowNum) {
		// TODO Auto-generated method stub
		String sql = "SELECT groups_posts.idgroup, groups_posts.idpost, posts.postDetail, users_posts.iduser, users.username FROM groups_posts INNER JOIN posts ON groups_posts.idpost=posts.idpost INNER JOIN users_posts ON groups_posts.idpost=users_posts.idpost INNER JOIN users ON  users_posts.iduser = users.iduser AND idgroup=? ORDER BY idpost DESC LIMIT 0,?";
		Object[] param = new Object[] { idgroup, rowNum };
		PostMapper mapper = new PostMapper();
		try {
			List<Post> list = this.getJdbcTemplate().query(sql, param, mapper);
			return list;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void createPost(String postDetail) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO posts (postDetail) VALUES (?)";
		Object[] params = new Object[] { postDetail };
		try {
			this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getNextAutoIncrementPostId() {
		// TODO Auto-generated method stub
		String sql = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'loginmvc' AND TABLE_NAME = 'posts'";
		try {
			int id = this.getJdbcTemplate().queryForObject(sql, Integer.class);
			return id;
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}

	public void mapPostIdWithGroupId(int idpost, int idgroup) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO groups_posts (idpost, idgroup) VALUES (?,?)";
		Object[] params = new Object[] { idpost, idgroup };
		try {
			this.getJdbcTemplate().update(sql, params);

		} catch (Exception e) {

		}
	}

	public void mapPostIdWithUserId(int idpost, int iduser) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO users_posts (idpost, iduser) VALUES (?,?)";
		Object[] params = new Object[] { idpost, iduser };
		try {
			this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {

		}
	}

	public int GetGroupIdByPostId(int idpost) {
		// TODO Auto-generated method stub
		String sql = "SELECT idgroup FROM groups_posts WHERE idpost=?";
		Object[] params = new Object[] { idpost };
		try {
			int id = this.getJdbcTemplate().queryForObject(sql, params, Integer.class);
			return id;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public void deletePost(int idpost) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM posts WHERE idpost=?";
		Object[] params = new Object[] { idpost };
		try {
			this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
		}

	}

	public void deletePostIdAndGroupIdMap(int idpost) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM groups_posts WHERE idpost=?";
		Object[] params = new Object[] { idpost };
		try {
			this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
		}
	}

	public void deletePostIdAndUserIdMap(int idpost) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM users_posts WHERE idpost=?";
		Object[] params = new Object[] { idpost };
		try {
			this.getJdbcTemplate().update(sql, params);
		} catch (Exception e) {
		}
	}

	public int GetPostUserIdByPostId(int idpost) {
		// TODO Auto-generated method stub
		String sql = "SELECT iduser FROM users_posts WHERE idpost=?";
		Object[] params = new Object[] { idpost };
		try {
			int id = this.getJdbcTemplate().queryForObject(sql, params, Integer.class);
			return id;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public List<Integer> getAllCommentIdOfPost(int idpost) {
		// TODO Auto-generated method stub
		String sql = "SELECT idcomment FROM posts_comments WHERE idpost = ?";
		Object[] param = new Object[] { idpost };
		try {
			List<Integer> list = this.getJdbcTemplate().queryForList(sql, Integer.class, param);
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	public String getPostDetailByPostId(int idpost) {
		// TODO Auto-generated method stub
		String sql = "SELECT postDetail FROM posts WHERE idpost = ?";
		Object[] param = new Object[] { idpost };

		try {
			String post = this.getJdbcTemplate().queryForObject(sql, String.class, param);
			return post;
		} catch (Exception e) {
			return null;
		}
	}

}
