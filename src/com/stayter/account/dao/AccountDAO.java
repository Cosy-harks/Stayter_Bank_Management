package com.stayter.account.dao;

import java.util.List;

import com.stayter.account.to.Account;
import com.stayter.customer.to.Customer;

public interface AccountDAO {
	public int registerAccount(Account account) throws Exception;
	public List<Account> getAccountList(Customer customer) throws Exception;
	public List<Account> getAllPending() throws Exception;
	public int updatePendingAccounts(List<Integer> aidList, List<Boolean> reviewList);
	public int depositUpdate(Account account, double amount);
}
