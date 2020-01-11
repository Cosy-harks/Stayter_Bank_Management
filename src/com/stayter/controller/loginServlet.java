package com.stayter.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stayter.customer.bo.CustomerBO;
import com.stayter.customer.bo.impl.CustomerBoImpl;
import com.stayter.customer.dao.CustomerDAO;
import com.stayter.customer.dao.impl.CustomerDaoImpl;
import com.stayter.customer.to.Customer;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/login")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer customer = new Customer();
		response.setContentType("text/html");
		response.setContentType("UTF-8");
		PrintWriter out = response.getWriter();
		customer.setUsername(request.getParameter("username"));
		customer.setPassword(request.getParameter("password"));
		CustomerBO cbo = new CustomerBoImpl();
		RequestDispatcher rd=null;
		try {
			customer = cbo.login(customer.getUsername(), customer.getPassword());
			response.sendRedirect("user.html");
		} catch (Exception e) {
			rd=request.getRequestDispatcher("login.html");
			rd.include(request, response);
			out.print("<span>"+e.getMessage()+"</span>");
		}
	}

}
