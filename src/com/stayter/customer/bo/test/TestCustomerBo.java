package com.stayter.customer.bo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.stayter.customer.to.Customer;
import com.stayter.customer.bo.CustomerBO;
import com.stayter.customer.bo.impl.CustomerBoImpl;

class TestCustomerBO {
	static Customer cust = null;
	static CustomerBO cbo;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Called");
		cust = new Customer("Smithy_John");
		cbo = new CustomerBoImpl();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testUsernameValidity() {
		System.out.println("Called");
		assertEquals(true, cbo.isValidUsername("johnSmith"));
		assertEquals(false, cbo.isValidUsername("John#$35Smith"));
		assertEquals(false, cbo.isValidUsername("John@Smith"));
		assertEquals(false, cbo.isValidUsername("John-Smith+"));
	}

}
