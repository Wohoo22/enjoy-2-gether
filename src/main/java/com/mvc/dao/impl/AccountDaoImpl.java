package com.mvc.dao.impl;

import java.time.LocalDate;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.AccountDao;
import com.mvc.mapper.Groups_AccountDetailMapper;
import com.mvc.model.Account;
import com.mvc.model.Groups_Accounts;

@Service
@Transactional
public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {

	@Autowired
	public AccountDaoImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public Account getAccountInfoByAccountId(int idaccount) {
		// TODO Auto-generated method stub
		return null;
	}

	public Groups_Accounts getAccountDetailsOfGroupByGroupId(int idgroup) {
		// TODO Auto-generated method stub
		String sql = "SELECT groups_accounts.idgroup, groups_accounts.idaccount, accounts.email, accounts.password, accounts.expiredDate FROM groups_accounts INNER JOIN accounts ON groups_accounts.idaccount = accounts.idaccount AND idgroup=?";
		Object[] param = new Object[] { idgroup };
		Groups_AccountDetailMapper mapper = new Groups_AccountDetailMapper();
		try {
			Groups_Accounts ga = this.getJdbcTemplate().queryForObject(sql, param, mapper);
			return ga;
		} catch (EmptyResultDataAccessException e) {

			return null;
		}
	}

	public void addNewAccountInfo(Account acc) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO accounts ( email, password, signedDate, expiredDate, service) VALUES (?,?,?,?,?)";

		Object[] param = new Object[] { acc.getEmail(), acc.getPassword(), acc.getSignedDate(), acc.getExpiredDate(),
				acc.getService() };
		try {
			this.getJdbcTemplate().update(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void updateAccountExpiredDate(java.sql.Date date, String email) {
		// TODO Auto-generated method stub
		String sql = "UPDATE accounts SET expiredDate=? WHERE email=?";
		Object[] param = new Object[] { date, email };
		try {
			this.getJdbcTemplate().update(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void mapAccountIdWithGroupId(int idaccount, int idgroup) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO groups_accounts (idgroup, idaccount) VALUES (?,?)";
		Object[] param = new Object[] { idgroup, idaccount };
		try {
			this.getJdbcTemplate().update(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public int getNextAutoIncrementAccountId() {
		// TODO Auto-generated method stub
		String sql = "SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'loginmvc' AND TABLE_NAME = 'accounts'";
		try {
			int id = this.getJdbcTemplate().queryForObject(sql, Integer.class);
			return id;
		} catch (EmptyResultDataAccessException e) {
			// TODO: handle exception
			return 0;
		}
	}

	public int getAccountIdByAccountEmail(String email) {
		// TODO Auto-generated method stub
		String sql = "SELECT idaccount FROM accounts WHERE email = ?";
		Object[] param = new Object[] { email };
		try {
			int id = this.getJdbcTemplate().queryForObject(sql, param, Integer.class);
			return id;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public int getGroupIdByAccountId(int idaccount) {
		// TODO Auto-generated method stub
		String sql = "SELECT idgroup FROM groups_accounts WHERE idaccount=?";
		Object[] param = new Object[] { idaccount };
		try {
			int id = this.getJdbcTemplate().queryForObject(sql, param, Integer.class);
			return id;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	@Override
	public void updateAccountPassword(String email, String pw) {
		// TODO Auto-generated method stub
		String sql = "UPDATE accounts SET password=? WHERE email=?";
		Object[] param = new Object[] { pw, email };
		try {
			this.getJdbcTemplate().update(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
