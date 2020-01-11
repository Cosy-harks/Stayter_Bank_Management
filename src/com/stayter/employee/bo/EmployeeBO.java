package com.stayter.employee.bo;


import com.stayter.employee.to.Employee;
import com.stayter.employee.dao.EmployeeDAO;

public interface EmployeeBO {
	public boolean isValidUsername(String username);
	public boolean isValidPassword(String password);
	public boolean registerEmployee(String username, String password) throws Exception;
	public Employee employeeLogin(String username, String password) throws Exception;
	public boolean isEmployeeEid(String ceid) throws Exception;
	public EmployeeDAO getDao();
	boolean employeeLogout(Employee employee) throws Exception;
	
}
