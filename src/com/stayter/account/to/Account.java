package com.stayter.account.to;

public class Account {
	private int aid;
	private String nickname;
	private double balance;
	private String username;
	private boolean underReview;
	
	public Account(double balance, String username) {
		super();
		this.balance = balance;
		this.username = username;
	}
	public Account(int aid, String nickname, double balance, String username) {
		super();
		this.aid = aid;
		this.nickname = nickname;
		this.balance = balance;
		this.username = username;
	}
	public Account(int aid, String nickname, double balance, String username, boolean underReview) {
		super();
		this.aid = aid;
		this.nickname = nickname;
		this.balance = balance;
		this.username = username;
		this.underReview = underReview;
	}
	
	@Override
	public String toString() {
		return "Account ID: " + aid + "\nnickname: " + nickname + "\nbalance: $" + String.format("%.2f", balance);
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setUnderReview(boolean underReview) {
		this.underReview = underReview;
	}
	public boolean isUnderReview() {
		return underReview;
	}
	
}
