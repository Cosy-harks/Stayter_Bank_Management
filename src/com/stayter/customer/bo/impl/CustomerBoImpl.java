package com.stayter.customer.bo.impl;

import com.stayter.customer.bo.CustomerBO;
import com.stayter.customer.dao.CustomerDAO;
import com.stayter.customer.dao.impl.CustomerDaoImpl;
import com.stayter.customer.to.Customer;

public class CustomerBoImpl implements CustomerBO{
	
	private CustomerDAO dao;
	
	@Override
	public boolean newSignUp(Customer signup) throws Exception {
		//Customer customer = new Customer(signup.getUsername());
		if(getDao().registerCustomer(signup)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isValidUsername(String username) {
		String w = username.replaceAll("[a-zA-Z_0-9]", "");
		if (w.length()>0) {
			return false;
		}
		if(username.matches(".{4,}")) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isValidPassword(String password) throws Exception {
		if(password.matches(".*[^a-zA-Z0-9]+[a-zA-Z0-9]+[^a-zA-Z0-9]+.*")) {
//			throw new Exception("needs at least 2 special characters separated by alphanumerics");
			return true;
		}
		return false;
	}
	
	@Override
	public Customer login(String username, String password) throws Exception {
		//TODO update stats, login with db customer, return customer if found
		Customer customer = getDao().retrieveCustomer(username, password);
		if(customer != null) {
			customer.setLoggedIn(true);
			getDao().updateCustomerLoggedin(customer);
		}
		return customer;
	}
	
	@Override
	public void logout(Customer customer) throws Exception {
		customer.setLoggedIn(false);
		getDao().updateCustomerLoggedin(customer);
	}
	
	public CustomerDAO getDao() {
		if(dao == null) {
			dao = new CustomerDaoImpl();
		}
		return dao;
	}
}
