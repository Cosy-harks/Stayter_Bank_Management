package com.stayter.transaction.to;

import java.util.Date;

public class Transaction {
	int aid_from;
	double amount;
	int aid_to;
	String username;
	Date date;
	int tid;
	public Transaction(int aid_from, double amount, int aid_to, String username, Date date, int tid) {
		super();
		this.aid_from = aid_from;
		this.amount = amount;
		this.aid_to = aid_to;
		this.username = username;
		this.date = date;
		this.tid=tid;
	}
	@Override
	public String toString() {
		return "Transaction [aid_from=" + aid_from + ", amount=" + amount + ", aid_to=" + aid_to + ", username="
				+ username + ", date=" + date + ", tid=" + tid + "]";
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	};
	
}
