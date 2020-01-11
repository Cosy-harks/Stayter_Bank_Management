package com.stayter.account.bo.impl;

import java.util.ArrayList;
import java.util.List;

import com.stayter.account.to.Account;
import com.stayter.account.bo.AccountBO;
import com.stayter.account.dao.AccountDAO;
import com.stayter.account.dao.impl.AccountDaoImpl;
import com.stayter.customer.to.Customer;

public class AccountBoImpl implements AccountBO {
	private AccountDAO dao;
	
	public boolean isNum(String num) {
		if(num.matches("[^0-9.]")) {
			return false;
		}
		if(num.matches("\\.[0-9]*\\.")) {//lastIndexOf(".")!=num.indexOf(".")) {
			return false;
		}
		return true;
	}

	public void registerAccount(Account account) throws Exception {
		getDao().registerAccount(account);
	}

	public Account makeAccount(double balance, String customer) {
		return new Account(balance, customer);
	}
	
	public List<Account> getAccountList(Customer customer) throws Exception{
		return getDao().getAccountList(customer);
	}
	
	public AccountDAO getDao() {
		if(this.dao == null) {
			this.dao = new AccountDaoImpl();
		}
		return this.dao;
	}

	public boolean invalidWithdrawl(double withdrawAmount, Account account) {
		if(withdrawAmount < 0 || withdrawAmount > account.getBalance()) {
			System.out.println(account.getBalance());
			return true;
		}
		return false;
	}
	
	public List<Account> getAllPendingAccounts() throws Exception {
		return getDao().getAllPending();
	}

	public List<Account> reduceOnReview(List<Account> accountList) {
		for(int i = 0; i < accountList.size()-1; i++) {
			if(accountList.get(i).isUnderReview()) {
				accountList.remove(i);
				i--;
			}
		}
		return accountList;
	}

	public int updatePendingAccounts(List<Account> accountList) {
		List<Integer> aidList = new ArrayList<Integer>();
		List<Boolean> reviewList = new ArrayList<Boolean>();
		
		for(Account a:accountList) {
			aidList.add((Integer)a.getAid());
			reviewList.add((Boolean)a.isUnderReview());
		}
		
		return getDao().updatePendingAccounts(aidList, reviewList);
	}

	public void deposit(Account account, double amount) {
		getDao().depositUpdate(account, amount);
	}

	public void withdraw(Account account, double amount) {
		getDao().depositUpdate(account, -amount);
	}

}
