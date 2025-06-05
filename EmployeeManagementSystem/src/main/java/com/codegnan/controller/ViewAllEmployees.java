package com.codegnan.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codegnan.dao.EmployeeDao;
import com.codegnan.service1.Employee;

@WebServlet("/findAll")
public class ViewAllEmployees extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EmployeeDao dao = new EmployeeDao();
		try {
			List<Employee> emplist = dao.viewAllEmployees();
			request.setAttribute("emplist", emplist);
			RequestDispatcher rd = request.getRequestDispatcher("viewallemp.jsp");
			rd.forward(request, response);
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
	}

}