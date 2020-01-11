package com.stayter.transaction.bo.impl;

import java.util.List;

import com.stayter.account.to.Account;
import com.stayter.customer.to.Customer;
import com.stayter.transaction.to.Transaction;
import com.stayter.transaction.bo.TransactionBO;
import com.stayter.transaction.dao.TransactionDAO;
import com.stayter.transaction.dao.impl.TransactionDaoImpl;

public class TransactionBoImpl implements TransactionBO {

	private TransactionDAO transactionDao;
	@Override
	public void transfer(Account account, Customer customer, double amount, boolean checked) throws Exception {
		// TODO Auto-generated method stub
		if(!checked) {
			
		}
		getDao().transfer(account, customer, amount);
		
	}

	@Override
	public TransactionDAO getDao() {
		if(transactionDao == null) {
			transactionDao = new TransactionDaoImpl();
		}
		return transactionDao;
	}

	@Override
	public void acceptTransfer(Account account, int transactionId, boolean checked) throws Exception {
		// TODO Auto-generated method stub
		if(!checked) {
			//CHECK parameters
		}
		getDao().acceptTransfer(account, transactionId);
	}
	
	@Override
	public List<Transaction> getAllTransactions() {
		return getDao().getAll();
	}

	@Override
	public List<Transaction> getPendingTransfers(Customer customer) throws Exception {
		// TODO Auto-generated method stub
		return getDao().getPendingTransfers(customer);
	}

	@Override
	public void deposit(Account account, double amount) {
		getDao().deposit(account, amount);
	}

	@Override
	public void withdraw(Account account, double amount) {
		// TODO Auto-generated method stub
		getDao().withdraw(account, amount);
	}
	
}
