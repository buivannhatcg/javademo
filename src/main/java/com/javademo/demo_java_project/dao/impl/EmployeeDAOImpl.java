package com.javademo.demo_java_project.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javademo.demo_java_project.dao.interfaces.EmployeeDAO;
import com.javademo.demo_java_project.model.Employee;
import com.javademo.demo_java_project.util.DatabaseConnection;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public List<Employee> getAllEmployees() {

        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM Employees";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployeeID(rs.getInt("EmployeeID"));
                employee.setFirstName(rs.getString("FirstName"));
                employee.setLastName(rs.getString("LastName"));
                employee.setEmail(rs.getString("Email"));
                employee.setPhoneNumber(rs.getString("PhoneNumber"));
                employee.setJobID(rs.getInt("JobID"));
                employee.setDepartmentID(rs.getInt("DepartmentID"));
                employee.setSalary(rs.getDouble("Salary"));
                employee.setManagerID(rs.getInt("ManagerID"));

                list.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }

    @Override
    public Employee getEmployeeById(int id) {

        String sql = "SELECT * FROM Employees WHERE EmployeeID = ?";
        Employee employee = null;
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employee = new Employee();
                employee.setEmployeeID(rs.getInt("EmployeeID"));
                employee.setFirstName(rs.getString("FirstName"));
                employee.setLastName(rs.getString("LastName"));
                employee.setEmail(rs.getString("Email"));
                employee.setPhoneNumber(rs.getString("PhoneNumber"));
                employee.setJobID(rs.getInt("JobID"));
                employee.setDepartmentID(rs.getInt("DepartmentID"));
                employee.setSalary(rs.getDouble("Salary"));
                employee.setManagerID(rs.getInt("ManagerID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public boolean addEmployee(Employee employee) {

        String sql = "INSERT INTO Employees (FirstName, LastName, Email, PhoneNumber, JobID, DepartmentID, Salary, ManagerID) VALUES (?,?,?,?,?,?,?,?)";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getEmail());
            ps.setString(4, employee.getPhoneNumber());
            ps.setInt(5, employee.getJobID());
            ps.setInt(6, employee.getDepartmentID());
            ps.setDouble(7, employee.getSalary());
            ps.setInt(8, employee.getManagerID());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean updateEmployee(Employee employee) {

        String sql = "UPDATE Employees SET FirstName=?, LastName=?, Email=?, PhoneNumber=?, JobID=?, DepartmentID=?, Salary=?, ManagerID=? WHERE EmployeeID=?";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getEmail());
            ps.setString(4, employee.getPhoneNumber());
            ps.setInt(5, employee.getJobID());
            ps.setInt(6, employee.getDepartmentID());
            ps.setDouble(7, employee.getSalary());
            ps.setInt(8, employee.getManagerID());
            ps.setInt(9, employee.getEmployeeID());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteEmployee(int id) {

        String sql = "DELETE FROM Employees WHERE EmployeeID =?";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
