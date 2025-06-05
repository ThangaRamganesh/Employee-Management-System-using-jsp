package com.codegnan.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codegnan.dao.EmployeeDao;
import com.codegnan.service1.Employee;

@WebServlet("/search")
public class SearchEmployee extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		EmployeeDao dao = new EmployeeDao();
		try {
			Employee employee = dao.searchEmployee(id);
			if (employee.getId() != 0) {
				request.setAttribute("employee", employee);
				RequestDispatcher rd = request.getRequestDispatcher("viewemp.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("employee", "Invalid id");
				RequestDispatcher rd = request.getRequestDispatcher("search.jsp");
				rd.forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}

}
