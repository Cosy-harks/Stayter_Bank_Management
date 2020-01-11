package com.stayter.employee.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbutil.OracleConnection;
import com.stayter.employee.to.Employee;
import com.stayter.employee.dao.EmployeeDAO;

public class EmployeeDaoImpl implements EmployeeDAO {

	@Override
	public boolean registerEmployee(String username, String password) throws Exception {
		boolean b = false;
		//username, dob, eid, password
		String sql = "{CALL REGISTER_EMPLOYEE(?,?)}";
		try(Connection connection = OracleConnection.getConnection()){
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.setString(1, username);
			callableStatement.setString(2, password);
			b = callableStatement.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new Exception("Something happened " +e);
		}
		return b;
	}

	@Override
	public Employee employeeLogin(String username, String password) throws Exception {
		Employee employee = null;
		String sql = "select eid,loggedin from stayter_employees where username=? and password=?";
		try(Connection connection = OracleConnection.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				String eid = resultSet.getString("EID");
				System.out.println("did we make it");
				boolean loggedin = resultSet.getBoolean("LOGGEDIN");
				employee = new Employee(username, eid, loggedin);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new Exception("Connection faulty");
		}
		return employee;
	}

	@Override
	public boolean isEmployeeEid(String eid) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select eid from stayter_employees where eid=?";
		try(Connection connection = OracleConnection.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, eid);
			return preparedStatement.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new Exception("Connection faulty");
		}
	}

	@Override
	public boolean updateEmployeeLoggedin(Employee employee) throws Exception {
		boolean b = false;
		try(Connection connection = OracleConnection.getConnection()){
			String sql = "update stayter_employees SET loggedin=? where username=?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, employee.isLoggedIn());
			preparedStatement.setString(2, employee.getUsername());
			b = preparedStatement.execute();
		} catch (ClassNotFoundException | SQLException e) {
			throw new Exception("Internal Error Occured");
		}
		return b;
	}

}
