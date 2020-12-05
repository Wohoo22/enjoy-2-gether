package com.mvc.dao;

import java.time.LocalDate;
import java.util.Date;

import com.mvc.model.Account;
import com.mvc.model.Groups_Accounts;

public interface AccountDao {
	Account getAccountInfoByAccountId(int idaccount);

	Groups_Accounts getAccountDetailsOfGroupByGroupId(int idgroup);

	void addNewAccountInfo(Account acc);

	void updateAccountExpiredDate(java.sql.Date date, String email);

	void mapAccountIdWithGroupId(int idaccount, int idgroup);

	int getNextAutoIncrementAccountId();

	int getAccountIdByAccountEmail(String email);

	int getGroupIdByAccountId(int idaccount);

	void updateAccountPassword(String email, String pw);
}
