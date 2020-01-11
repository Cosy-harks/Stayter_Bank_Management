package com.stayter.customer.bo;

import com.stayter.customer.to.Customer;

public interface CustomerBO {

	void logout(Customer customer) throws Exception;

	Customer login(String username, String password) throws Exception;

	boolean isValidPassword(String password) throws Exception;

	boolean isValidUsername(String username);

	boolean newSignUp(Customer signup) throws Exception;

}
