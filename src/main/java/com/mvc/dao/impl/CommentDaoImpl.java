package com.mvc.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.CommentDao;
import com.mvc.mapper.CommentMapper;
import com.mvc.model.Comment;
import com.mvc.model.Post;

@Service
@Transactional
public class CommentDaoImpl extends JdbcDaoSupport implements CommentDao {
    @Autowired
    public CommentDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Comment> getPostComments(int idpost) {
        // TODO Auto-generated method stub
        String sql = "SELECT posts_comments.idpost, posts_comments.idcomment, comments.commentDetail,users_comments.iduser, users.username FROM posts_comments INNER JOIN comments ON posts_comments.idcomment=comments.idcomment INNER JOIN users_comments ON posts_comments.idcomment=users_comments.idcomment INNER JOIN users ON  users_comments.iduser = users.iduser and idpost=? ORDER BY posts_comments.idcomment ASC ";
        Object[] param = new Object[]{idpost};
        CommentMapper mapper = new CommentMapper();
        try {
            List<Comment> list = this.getJdbcTemplate().query(sql, param, mapper);
            return list;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void createComment(String commentDetail) {
        // TODO Auto-generated method stub
        String sql = "INSERT INTO comments (commentDetail) values (?)";
        Object[] params = new Object[]{commentDetail};
        try {
            this.getJdbcTemplate().update(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getNextAutoIncrementCommentId() {
        // TODO Auto-generated method stub
        String sql = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'loginmvc' AND TABLE_NAME = 'comments'";
        try {
            int id = this.getJdbcTemplate().queryForObject(sql, Integer.class);
            return id;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    public void mapCommentIdWithPostId(int idcomment, int idpost) {
        // TODO Auto-generated method stub
        String sql = "INSERT INTO posts_comments (idcomment, idpost) VALUES (?,?)";
        Object[] params = new Object[]{idcomment, idpost};
        try {
            this.getJdbcTemplate().update(sql, params);

        } catch (Exception e) {

        }
    }

    public void mapCommentIdWithUserId(int idcomment, int iduser) {
        // TODO Auto-generated method stub
        String sql = "INSERT INTO users_comments (idcomment, iduser) VALUES (?,?)";
        Object[] params = new Object[]{idcomment, iduser};
        try {
            this.getJdbcTemplate().update(sql, params);
        } catch (Exception e) {

        }
    }

    public void deleteCommentByCommentId(int idcomment) {
        // TODO Auto-generated method stub
        String sql = "DELETE FROM comments WHERE idcomment=?";
        Object[] params = new Object[]{idcomment};
        try {
            this.getJdbcTemplate().update(sql, params);
        } catch (Exception e) {
        }
    }

    public void deleteCommentIdAndPostIdMap(int idcomment) {
        // TODO Auto-generated method stub
        String sql = "DELETE FROM posts_comments WHERE idcomment=?";
        Object[] params = new Object[]{idcomment};
        try {
            this.getJdbcTemplate().update(sql, params);
        } catch (Exception e) {
        }
    }

    public void deleteCommentIdAndUserIdMap(int idcomment) {
        // TODO Auto-generated method stub
        String sql = "DELETE FROM users_comments WHERE idcomment=?";
        Object[] params = new Object[]{idcomment};
        try {
            this.getJdbcTemplate().update(sql, params);
        } catch (Exception e) {
        }

    }

    public int getCommentOwnerIdByIdComment(int idcomment) {
        // TODO Auto-generated method stub
        String sql = "SELECT iduser FROM users_comments WHERE idcomment=?";
        Object[] params = new Object[]{idcomment};
        try {
            int id = this.getJdbcTemplate().queryForObject(sql, params, Integer.class);
            return id;
        } catch (Exception e) {
            return 0;
        }
    }

}
