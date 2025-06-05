package com.codegnan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.codegnan.service1.Employee;

public class EmployeeDao {

	Connection connection = null;

	public boolean saveEmployee(Employee employee) throws ClassNotFoundException, SQLException {
		connection = ConnectionManager.getConnection();
		PreparedStatement st = connection.prepareStatement("insert into employee values(?,?,?,?,?,?,?,?,?)");
		st.setInt(1, employee.getId());
		st.setString(2, employee.getName());
		st.setString(3, employee.getEmail());
		st.setString(4, employee.getPassword());
		st.setInt(5, employee.getAge());
		st.setString(6, employee.getMobile());
		st.setString(7, employee.getGender());
		st.setString(8, employee.getDepartment());
		st.setString(9, employee.getAddress());

		int status = st.executeUpdate();
		if (status == 1) {
			return true;
		}

		return false;
	}

	public void commit() throws SQLException {
		connection.commit();
		connection.close();
	}

	public void rollback() throws SQLException {
		connection.rollback();
		connection.close();

	}

	public boolean checkLoginDetails(String email, String password) throws ClassNotFoundException, SQLException {
		connection = ConnectionManager.getConnection();

		String query = "select count(*) from employee where email = ? and password = ?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, email);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		int count = 0;
		if (rs.next()) {
			count = rs.getInt(1);
		}
		if (count == 1) {
			return true;
		}
		return false;
	}

	public List<Employee> viewAllEmployees() throws ClassNotFoundException, SQLException {
		connection = ConnectionManager.getConnection();
		PreparedStatement ps = connection.prepareStatement("select * from employee");
		ResultSet rs = ps.executeQuery();
		List<Employee> emplist = new ArrayList<Employee>();
		while (rs.next()) {
			Employee emp = new Employee();
			emp.setId(rs.getInt(1));
			emp.setName(rs.getString(2));
			emp.setEmail(rs.getString(3));
			emp.setPassword(rs.getString(4));
			emp.setAge(rs.getInt(5));
			emp.setMobile(rs.getString(6));
			emp.setGender(rs.getString(7));
			emp.setDepartment(rs.getString(8));
			emp.setAddress(rs.getString(9));
			emplist.add(emp);

		}

		return emplist;
	}

	public boolean deleteById(int id) throws SQLException, ClassNotFoundException {
		connection = ConnectionManager.getConnection();
		PreparedStatement ps = connection.prepareStatement("delete from employee where id = ?");
		ps.setInt(1, id);
		int status = ps.executeUpdate();
		if (status == 1) {
			return true;
		}
		return false;
	}

	public boolean updateEmployee(Employee employee) throws SQLException, ClassNotFoundException {
		connection = ConnectionManager.getConnection();
		PreparedStatement st = connection.prepareStatement(
				"update  employee set name = ?,email= ?,password = ?,age=?,gender=?,mobile=?,department=?,address=?where id =?");
		st.setString(1, employee.getName());
		st.setString(2, employee.getEmail());
		st.setString(3, employee.getPassword());
		st.setInt(4, employee.getAge());
		st.setString(5, employee.getMobile());
		st.setString(6, employee.getGender());
		st.setString(7, employee.getDepartment());
		st.setString(8, employee.getAddress());
		st.setInt(9, employee.getId());

		int status = st.executeUpdate();
		if (status == 1) {
			return true;
		}

		return false;

	}

	public Employee searchEmployee(int id) throws ClassNotFoundException, SQLException {
		connection = ConnectionManager.getConnection();
		PreparedStatement st = connection.prepareStatement("select * from employee where id = ?");
		st.setInt(1, id);
		ResultSet rs = st.executeQuery();
		Employee emp = new Employee();
		if (rs.next()) {

			emp.setId(rs.getInt(1));
			emp.setName(rs.getString(2));
			emp.setEmail(rs.getString(3));
			emp.setPassword(rs.getString(4));
			emp.setAge(rs.getInt(5));
			emp.setMobile(rs.getString(6));
			emp.setGender(rs.getString(7));
			emp.setDepartment(rs.getString(8));
			emp.setAddress(rs.getString(9));

		}
		return emp;
	}
}