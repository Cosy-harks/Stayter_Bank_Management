package com.stayter.transaction.dao;

import java.util.List;

import com.stayter.account.to.Account;
import com.stayter.customer.to.Customer;
import com.stayter.transaction.to.Transaction;

public interface TransactionDAO {
	public int transfer(Account account, Customer customer, double amount) throws Exception;
	public int acceptTransfer(Account account, int transactionId) throws Exception;
	public List<Transaction> getAll();
	public List<Transaction> getPendingTransfers(Customer customer) throws Exception;
	public void deposit(Account account, double amount);
	public void withdraw(Account account, double amount);
}
