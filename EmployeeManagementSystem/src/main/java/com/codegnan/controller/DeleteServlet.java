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

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		EmployeeDao dao = new EmployeeDao();
		try {
			boolean status = dao.deleteById(id);
			if(status) {
				dao.commit();
				RequestDispatcher rd = request.getRequestDispatcher("findAll");
				rd.forward(request, response);
			}
			else {
				dao.rollback();
				RequestDispatcher rd = request.getRequestDispatcher("findAll");
				rd.forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
	}

}