package com.mvc.dao.impl;

import java.sql.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.UserInfoDao;
import com.mvc.mapper.UserInfoBalanceMapper;
import com.mvc.mapper.UserInfoMapper;
import com.mvc.mapper.UserInfoMapper2;
import com.mvc.model.UserInfo;
import com.mvc.ultis.BcryptEncoder;

@Service
@Transactional
public class UserInfoDaoImpl extends JdbcDaoSupport implements UserInfoDao {
    @Autowired
    public UserInfoDaoImpl(DataSource dataSource) {
        // TODO Auto-generated constructor stub
        this.setDataSource(dataSource);
    }

    public UserInfo findUserInfo(String username) {
        // TODO Auto-generated method stub
        String sql = "Select u.iduser,u.username,u.password,u.role, u.email, u.balance "//
                + " from users u where u.username = ? ";

        Object[] params = new Object[]{username};
        UserInfoMapper2 mapper = new UserInfoMapper2();
        try {
            UserInfo userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return userInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public String getUserRoles(String username) {
        // TODO Auto-generated method stub
        String sql = "Select u.role from users u where u.username = ?";
        Object[] params = new Object[]{username};
        String role = this.getJdbcTemplate().queryForObject(sql, params, String.class);
        return role;
    }

    public int addUser(UserInfo user) {
        // TODO Auto-generated method stub
        String sql = "INSERT INTO users (username,password,role,email) VALUES (?,?,?,?)";

        BcryptEncoder encode = new BcryptEncoder();
        String pw = encode.BcryptEncoder(user.getPassword());
        Object[] us = new Object[]{user.getUserName(), pw, "USER", user.getEmail()};
        try {
            int counter = this.getJdbcTemplate().update(sql, us);
            return counter;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    public UserInfo findUserByEmail(String email) {
        // TODO Auto-generated method stub
        String sql = "Select u.username,u.password,u.role, u.email "//
                + " from users u where u.email = ?";
        Object[] params = new Object[]{email};
        UserInfoMapper mapper = new UserInfoMapper();
        try {
            UserInfo userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return userInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public String getUsernameByUsername(String username) {
        // TODO Auto-generated method stub
        String sql = "SELECT username FROM users WHERE username=?";
        Object[] params = new Object[]{username};
        try {
            String name = this.getJdbcTemplate().queryForObject(sql, params, String.class);
            return name;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public String getUserEmailByEmail(String email) {
        // TODO Auto-generated method stub
        String sql = "SELECT email FROM users WHERE email = ?";
        Object[] params = new Object[]{email};
        try {
            String em = this.getJdbcTemplate().queryForObject(sql, params, String.class);
            return em;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int getUserIdByUsername(String username) {
        // TODO Auto-generated method stub
        String sql = "SELECT iduser FROM users WHERE username= ?";
        Object[] params = new Object[]{username};

        try {
            int id = this.getJdbcTemplate().queryForObject(sql, params, Integer.class);
            return id;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    public UserInfo getUsernameAndBalanceByIdUser(int iduser) {
        // TODO Auto-generated method stub
        String sql = "SELECT username,balance FROM users WHERE iduser=?";
        Object[] param = new Object[]{iduser};
        UserInfoBalanceMapper mapper = new UserInfoBalanceMapper();
        try {
            UserInfo us = this.getJdbcTemplate().queryForObject(sql, param, mapper);
            return us;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void insertUserDateJoinGroup(int iduser, Date date) {
        // TODO Auto-generated method stub
        String sql = "UPDATE users SET dateJoinGroup = ? WHERE iduser=?";
        Object[] param = new Object[]{date, iduser};
        try {
            this.getJdbcTemplate().update(sql, param);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int getVerifyCodeOfEmailByEmail(String email) {
        // TODO Auto-generated method stub
        String sql = "SELECT code FROM email_verifycode WHERE email=?";
        Object[] param = new Object[]{email};
        try {
            int code = this.getJdbcTemplate().queryForObject(sql, Integer.class, param);
            return code;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    public void saveEmailAndVerifyCode(String email, int code) {
        // TODO Auto-generated method stub
        String sql = "INSERT INTO email_verifycode (email,code) VALUES (?,?)";
        Object[] param = new Object[]{email, code};
        try {
            this.getJdbcTemplate().update(sql, param);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public void updatePasswordByUserEmail(String encodedPassword, String email) {
        // TODO Auto-generated method stub
        String sql = "UPDATE users SET password=? WHERE email=?";
        Object[] param = new Object[]{encodedPassword, email};
        try {
            this.getJdbcTemplate().update(sql, param);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void deleteEmailAndVerifyCode(String email) {
        // TODO Auto-generated method stub
        String sql = "DELETE FROM email_verifycode WHERE email=?";
        Object[] param = new Object[]{email};
        try {
            this.getJdbcTemplate().update(sql, param);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public Date getUserDateJoinGroup(int iduser) {
        // TODO Auto-generated method stub
        String sql = "SELECT dateJoinGroup FROM users WHERE iduser=?";
        Object[] param = new Object[]{iduser};
        try {
            Date date = this.getJdbcTemplate().queryForObject(sql, param, Date.class);
            return date;
        } catch (EmptyResultDataAccessException e) {
            // TODO: handle exception
            return null;

        }
    }

    public int getUserBalanceByUsername(String username) {
        // TODO Auto-generated method stub
        String sql = "SELECT balance FROM users WHERE username=?";
        Object[] param = new Object[]{username};
        try {
            int b = this.getJdbcTemplate().queryForObject(sql, param, Integer.class);
            return b;
        } catch (Exception e) {
            // TODO: handle exception
            return 0;

        }
    }

    public void updateUserBalanceByUsername(int balance, String username) {
        // TODO Auto-generated method stub
        String sql = "UPDATE users SET balance=? WHERE username=?";
        Object[] param = new Object[]{balance, username};
        try {
            this.getJdbcTemplate().update(sql, param);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void truncateEmailVerifyCodeTable() {
        // TODO Auto-generated method stub
        String sql = "TRUNCATE email_verifycode";
        try {
            this.getJdbcTemplate().update(sql);
        } catch (Exception e) {
        }
    }

}
