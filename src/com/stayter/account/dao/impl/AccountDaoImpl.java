package com.stayter.account.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stayter.account.to.Account;
import com.stayter.account.dao.AccountDAO;
import com.dbutil.OracleConnection;
import com.stayter.customer.to.Customer;

public class AccountDaoImpl implements AccountDAO {

	public int registerAccount(Account account) throws Exception {
		int c = 0;
		
		try (Connection connection = OracleConnection.getConnection()){
			String sql = "insert into stayter_accounts(aid,nickname,balance,username) values(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, -12);
			preparedStatement.setString(2, account.getNickname());
			preparedStatement.setDouble(3, account.getBalance());
			preparedStatement.setString(4, account.getUsername());
			
			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new Exception("Sum thing whened tare uh blee ron g\n" + e);
		}
		return c;
	}

	public List<Account> getAccountList(Customer customer) throws Exception {
		
		String sql = "select aid,nickname,balance from Stayter_Accounts where username=? AND underreview=0";
		List<Account> accountList = new ArrayList<Account>();
		Account account = null;
		
		try(Connection connection = OracleConnection.getConnection()){
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getUsername());
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				int aid = resultSet.getInt("aid");
				String nickname = resultSet.getString("nickname");
				double balance = resultSet.getDouble("balance");
				String username = customer.getUsername();
				
				account = new Account(aid, nickname, balance, username);
				accountList.add(account);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new Exception("bobby " +e);
		}
		return accountList;
	}

	public List<Account> getAllPending() throws Exception {
		String sql = "select * from Stayter_Accounts where underreview=1";
		List<Account> accountList = new ArrayList<Account>();
		Account account = null;
		
		try(Connection connection = OracleConnection.getConnection()){
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				int aid = resultSet.getInt("aid");
				String nickname = resultSet.getString("nickname");
				double balance = resultSet.getDouble("balance");
				String username = resultSet.getString("username");
				boolean underReview = resultSet.getBoolean("underReview");
				
				account = new Account(aid, nickname, balance, username, underReview);
				accountList.add(account);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new Exception("bobby " +e);
		}
		return accountList;
	}

	public int updatePendingAccounts(List<Integer> aidList, List<Boolean> reviewList) {
		
		String sql = "update stayter_accounts Set underReview=? where aid=?";
		int a = 0;
		try(Connection connection = OracleConnection.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			for(int index = 0; index < aidList.size(); index++) {
				preparedStatement.setBoolean(1, reviewList.get(index));
				preparedStatement.setInt(2, aidList.get(index));
				a += preparedStatement.executeUpdate();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	public int depositUpdate(Account account, double amount) {
		String sql = "update stayter_accounts Set balance=? where aid=?";
		int a = 0;
		try(Connection connection = OracleConnection.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, account.getBalance()+amount);
			preparedStatement.setInt(2, account.getAid());
			a = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return a;
	}
	
	
	
}
