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

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Employee employee = new Employee();
		employee.setId(Integer.parseInt(request.getParameter("id")));
		employee.setName(request.getParameter("name"));
		employee.setEmail(request.getParameter("email"));
		employee.setPassword(request.getParameter("password"));
		employee.setAge(Integer.parseInt(request.getParameter("age")));
		employee.setMobile(request.getParameter("mobile"));
		employee.setGender(request.getParameter("gender"));
		employee.setDepartment(request.getParameter("department"));
		employee.setAddress(request.getParameter("address"));
		EmployeeDao dao = new EmployeeDao();

		try {
			boolean status = dao.updateEmployee(employee);
			if (status) {
				dao.commit();
				request.setAttribute("status","updated successfully");
				RequestDispatcher rd = request.getRequestDispatcher("edit.jsp");
				rd.forward(request, response);
			}
			else {
				dao.rollback();
				request.setAttribute("status","updated failed");
				RequestDispatcher rd = request.getRequestDispatcher("edit.jsp");
				rd.forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

	}

}