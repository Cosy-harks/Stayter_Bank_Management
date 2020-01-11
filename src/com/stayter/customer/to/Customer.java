package com.stayter.customer.to;
/*
 * Customer Class
 * Stores a few parameter
 *  - username
 *  - Customer id
 *  - logged In
 *  - has Account
 */


public class Customer {
	private String username;
	private String password;
	private int cid;
	private boolean loggedIn;
	
	
	
	
	public Customer(String username, int cid, boolean loggedIn) {
		super();
		this.username = username;
		this.cid = cid;
		this.loggedIn = loggedIn;
	}
	
	public Customer(String username) {
		super();
		this.username = username;
		this.loggedIn = false;
	}
	public Customer() {
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getCid() {
		return cid;
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}
	
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	@Override
	public String toString() {
		return "Customer [username=" + username + ", password=" + password + ", cid=" + cid + ", loggedIn=" + loggedIn
				+ "]";
	}
}
