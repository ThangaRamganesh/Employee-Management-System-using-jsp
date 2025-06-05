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


@WebServlet("/save")
public class SaveEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employee emp = new Employee();
		emp.setId(Integer.parseInt(request.getParameter("id")));
		emp.setName(request.getParameter("name"));
		emp.setEmail(request.getParameter("email"));
		emp.setPassword(request.getParameter("password"));
		emp.setAge(Integer.parseInt(request.getParameter("age")));
		emp.setMobile(request.getParameter("mobile"));
		emp.setGender(request.getParameter("gender"));
		emp.setDepartment(request.getParameter("department"));
		emp.setAddress(request.getParameter("address"));
		
		EmployeeDao dao = new EmployeeDao();
		try {
			boolean status = dao.saveEmployee(emp);
			RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
			if(status) {
				dao.commit();
				request.setAttribute("status", "Registration Successfully");
				rd.forward(request, response);
			}
			else {
				dao.rollback();
				request.setAttribute("status", "Registration Failed");
				rd.forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
