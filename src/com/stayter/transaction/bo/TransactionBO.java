package com.stayter.transaction.bo;

import java.util.List;

import com.stayter.account.to.Account;
import com.stayter.customer.to.Customer;
import com.stayter.transaction.to.Transaction;
import com.stayter.transaction.dao.TransactionDAO;

public interface TransactionBO {

	public void transfer(Account account, Customer customer, double amount, boolean checked) throws Exception;
	public void acceptTransfer(Account account, int transaction, boolean checked) throws Exception;
	public List<Transaction> getAllTransactions();
	public TransactionDAO getDao();
	public List<Transaction> getPendingTransfers(Customer customer) throws Exception;
	public void deposit(Account account, double amount);
	public void withdraw(Account account, double amount);

}
