package com.stayter.employee.bo.impl;

import com.stayter.employee.to.Employee;
import com.stayter.employee.bo.EmployeeBO;
import com.stayter.employee.dao.EmployeeDAO;
import com.stayter.employee.dao.impl.EmployeeDaoImpl;

public class EmployeeBoImpl implements EmployeeBO {
	private EmployeeDAO dao;
	
	@Override
	public boolean isValidUsername(String username) {
		if(username.matches("\\W")) {
			return false;
		}else if(!username.matches("\\w{6,}")){
			return false;
		}
		return true;
	}

	@Override
	public boolean isValidPassword(String password) {
		String g = password.replaceAll("\\W", "");
		String h = password.replaceAll("\\w", "");
		if(!g.matches("\\w{12,}") || !h.matches("\\W{4,}")) {
			return false;
		}
		return true;
	}

	@Override
	public boolean registerEmployee(String username, String password) throws Exception {
		return getDao().registerEmployee(username, password);
	}

	@Override
	public EmployeeDAO getDao() {
		if(dao == null) {
			dao = new EmployeeDaoImpl();
		}
		return dao;
	}

	@Override
	public Employee employeeLogin(String username, String password) throws Exception {
		if(!isValidUsername(username) || !isValidPassword(password)) {
			throw new Exception("username or password is improper");
		}
		// TODO Auto-generated method stub
		Employee employee = getDao().employeeLogin(username, password);
		if(employee != null) {
			employee.setLoggedIn(true);
			getDao().updateEmployeeLoggedin(employee);
		}
		return employee;
	}
	
	@Override
	public boolean employeeLogout(Employee employee) throws Exception {
		if(employee != null) {
			employee.setLoggedIn(false);
			getDao().updateEmployeeLoggedin(employee);
		}
		return employee.isLoggedIn();
	}

	@Override
	public boolean isEmployeeEid(String ceid) throws Exception {
		return getDao().isEmployeeEid(ceid);
	}


	

}
