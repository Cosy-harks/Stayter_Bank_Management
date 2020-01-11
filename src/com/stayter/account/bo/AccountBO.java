package com.stayter.account.bo;

import java.util.List;

import com.stayter.account.to.Account;
import com.stayter.account.dao.AccountDAO;
import com.stayter.customer.to.Customer;

public interface AccountBO {
	public boolean isNum(String num);
	public Account makeAccount(double balance, String customer);
	public void registerAccount(Account account) throws Exception;
	public List<Account> getAccountList(Customer customer) throws Exception;
	public AccountDAO getDao();
	public boolean invalidWithdrawl(double withdrawAmount, Account account);
	public List<Account> reduceOnReview(List<Account> accountList);
	public List<Account> getAllPendingAccounts() throws Exception;
	public int updatePendingAccounts(List<Account> accountList);
	public void deposit(Account account, double amount);
	public void withdraw(Account account, double amount);
}
