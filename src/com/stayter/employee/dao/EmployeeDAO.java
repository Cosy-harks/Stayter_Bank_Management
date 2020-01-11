package com.stayter.employee.dao;

import com.stayter.employee.to.Employee;

public interface EmployeeDAO {
	public boolean registerEmployee(String username, String password) throws Exception;
	public Employee employeeLogin(String username, String password) throws Exception;
	public boolean isEmployeeEid(String ceid) throws Exception;
	public boolean updateEmployeeLoggedin(Employee employee) throws Exception;
}
