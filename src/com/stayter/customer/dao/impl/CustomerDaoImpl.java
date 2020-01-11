package com.stayter.customer.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import com.dbutil.OracleConnection;
import com.stayter.customer.dao.CustomerDAO;
import com.stayter.customer.to.Customer;

public class CustomerDaoImpl implements CustomerDAO {
	
	@Override
	public boolean registerCustomer(Customer signup) throws Exception {
		boolean b = false;
		
		String SQL = "{CALL REGISTER_CUSTOMER(?,?)}";
		//String sql = "insert into stayter_Customers(username,loggedin,hasAccount,password,underreview) values(?,?,?,?,?)";
			
		try(Connection connection = OracleConnection.getConnection()){
			CallableStatement callableStatement = connection.prepareCall(SQL);
			callableStatement.setString(1, signup.getUsername());
			callableStatement.setString(2, signup.getPassword());
			b = callableStatement.execute();
			System.out.println(callableStatement.getUpdateCount());
			if(callableStatement.getUpdateCount()==-1) {
				b=true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new Exception("Sorry that username is taken");
		}
		System.out.println(b);
		return b;
	}

	@Override
	public Customer retrieveCustomer(String username, String password) throws Exception {
		Customer customer = null;
		// TODO Auto-generated method stub
		try(Connection connection = OracleConnection.getConnection()){
			String sql = "Select cid,loggedin from stayter_customers where username=? AND password=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			int i = 0;
			for (;resultSet.next();i++) {
				//May go back to Arraylist, but also not store them with Customer
				//Can use string for accountIds without switching back and forth to a List
				int cid = resultSet.getInt("cid");
				boolean loggedIn = resultSet.getBoolean("loggedIn");
				customer = new Customer(username, cid, loggedIn);
				customer.setPassword(password);
			}if(i > 1) {
				throw new Exception("Internal Error Occured");
			}else if(i == 0) {
				throw new Exception("No customer account found with those credentials");
			}
			
		} catch (SQLIntegrityConstraintViolationException e) {
			throw new Exception("Internal Error");
		}catch (ClassNotFoundException | SQLException e) {
			throw new Exception("bad");
		} 
		return customer;
	}
	
	@Override
	public boolean updateCustomerLoggedin(Customer customer) throws Exception {
		boolean b = false;
		try(Connection connection = OracleConnection.getConnection()){
			String sql = "update stayter_Customers SET loggedin=? where username=?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, customer.isLoggedIn());
			preparedStatement.setString(2, customer.getUsername());
			b = (preparedStatement.executeUpdate() == 1);
		} catch (ClassNotFoundException | SQLException e) {
			throw new Exception("Internal Error Occured");
		}
		return b;
	}

	@Override
	public List<String> getAllUsernames() throws Exception {
		List<String> usernameList = new ArrayList<>();
		// TODO Auto-generated method stub
		try(Connection connection = OracleConnection.getConnection()){
			String sql = "Select username from stayter_customers";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			for (;resultSet.next();) {
				//May go back to Arraylist, but also not store them with Customer
				//Can use string for accountIds without switching back and forth to a List
				String username = resultSet.getString("username");
				usernameList.add(username);
			}
		}catch (ClassNotFoundException | SQLException e) {
			throw new Exception("bad "+e);
		} 
		return usernameList;
	}
	
}
