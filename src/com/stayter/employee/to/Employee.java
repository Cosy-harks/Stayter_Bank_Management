package com.stayter.employee.to;

public class Employee {
	private String username;
	private String eid;
	private boolean loggedIn;
	
	public Employee() {
		super();
		this.username = "";
		this.eid = null;
		this.loggedIn = false;
	}
	
	public Employee(String username, String eid, boolean loggedIn) {
		super();
		this.username = username;
		this.eid = eid;
		this.loggedIn = loggedIn;
	}
	public Employee(String username) {
		super();
		this.username = username;
		this.loggedIn = false;
	}
	public String getUsername() {
		return username;
	}
	public String getEid() {
		return eid;
	}
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
}
