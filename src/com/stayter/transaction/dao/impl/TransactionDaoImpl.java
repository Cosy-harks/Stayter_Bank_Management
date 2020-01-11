package com.stayter.transaction.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stayter.account.to.Account;
import com.dbutil.OracleConnection;
import com.stayter.customer.to.Customer;
import com.stayter.transaction.to.Transaction;
import com.stayter.transaction.dao.TransactionDAO;

public class TransactionDaoImpl implements TransactionDAO {

	@Override
	public int transfer(Account account, Customer customer, double amount) throws Exception {
		String sql = "{CALL TRANSFER(?,?,?)}";
		int c = 0;
		try(Connection connection = OracleConnection.getConnection()){
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.setInt(1,account.getAid());
			callableStatement.setDouble(2, amount);
			callableStatement.setString(3, customer.getUsername());
			c = callableStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new Exception("What did you do" + e);
		}
		return c;
	}

	@Override
	public int acceptTransfer(Account account, int transactionId) throws Exception {
		String sql = "{CALL ACCEPT_TRANSFER(?,?)}";
		int c = 0;
		try(Connection connection = OracleConnection.getConnection()){
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.setInt(1, account.getAid());
			callableStatement.setDouble(2, transactionId);
			c = callableStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			throw new Exception("What did you do" +e);
		}
		return c;
	}

	@Override
	public List<Transaction> getAll() {
		List<Transaction> transactionList = new ArrayList<>();
		
		String sql = "select * from stayter_transactions";
		
		try(Connection connection = OracleConnection.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				int aid_from = resultSet.getInt("AID_FROM");
				int aid_to = resultSet.getInt("AID_TO");
				double amount = resultSet.getDouble("amount");
				Date date = resultSet.getDate("localDate");
				String username = resultSet.getString("username");
				int tid = resultSet.getInt("tid");
				transactionList.add(new Transaction(aid_from, amount, aid_to, username, date, tid));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return transactionList;
	}

	@Override
	public List<Transaction> getPendingTransfers(Customer customer) throws Exception {
		List<Transaction> transactionList = new ArrayList<>();
		
		String sql = "select * from stayter_transactions where username=? AND aid_to is null";
		try(Connection connection = OracleConnection.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getUsername());
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				int aid_from = resultSet.getInt("AID_FROM");
				int aid_to = resultSet.getInt("AID_TO");
				double amount = resultSet.getDouble("amount");
				Date date = resultSet.getDate("localDate");
				String username = resultSet.getString("username");
				int tid = resultSet.getInt("tid");
				transactionList.add(new Transaction(aid_from, amount, aid_to, username, date, tid));
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new Exception(e.getMessage());
		}
		return transactionList;
	}

	@Override
	public void deposit(Account account, double amount) {
		String sql = "INSERT into stayter_transactions(aid_from, amount, aid_to, username,localDate) values(?,?,?,?,?)";
		
		LocalDate localDate = LocalDate.now( ZoneId.of("America/Montreal"));
		try(Connection connection = OracleConnection.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, 2020);
			preparedStatement.setDouble(2, amount);
			preparedStatement.setInt(3, account.getAid());
			preparedStatement.setString(4, account.getUsername());
			preparedStatement.setDate(5, java.sql.Date.valueOf(localDate));
			preparedStatement.execute();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void withdraw(Account account, double amount) {
		String sql = "INSERT into stayter_transactions(aid_from, amount, aid_to, username,localDate) values(?,?,?,?,?)";
		
		LocalDate localDate = LocalDate.now( ZoneId.of("America/Montreal"));
		try(Connection connection = OracleConnection.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, account.getAid());
			preparedStatement.setDouble(2, amount);
			preparedStatement.setInt(3, 2020);
			preparedStatement.setString(4, account.getUsername());
			preparedStatement.setDate(5, java.sql.Date.valueOf(localDate));
			preparedStatement.execute();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
