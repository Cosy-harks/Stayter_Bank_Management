package com.stayter.customer.dao;

import java.util.List;

import com.stayter.customer.to.Customer;

public interface CustomerDAO {

	boolean registerCustomer(Customer signup) throws Exception;

	Customer retrieveCustomer(String username, String password) throws Exception;

	boolean updateCustomerLoggedin(Customer customer) throws Exception;

	List<String> getAllUsernames() throws Exception;

}
