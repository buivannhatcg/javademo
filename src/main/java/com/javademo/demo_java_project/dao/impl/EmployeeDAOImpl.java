package com.javademo.demo_java_project.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javademo.demo_java_project.dao.interfaces.EmployeeDAO;
import com.javademo.demo_java_project.model.Employee;

import com.javademo.demo_java_project.mapper.EmployeeMapper;
import com.javademo.demo_java_project.util.DatabaseConnection;

public class EmployeeDAOImpl extends AbstractDAO<Employee> implements EmployeeDAO {

    @Override
    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM Employees";
        return quer(sql, new EmployeeMapper());
        // List<Employee> list = new ArrayList<>();
        // try {
        //     Connection conn = DatabaseConnection.getConnection();
        //     PreparedStatement ps = conn.prepareStatement(sql);
        //     ResultSet rs = ps.executeQuery();
        //     while (rs.next()) {
        //         Employee employee = new Employee();
        //         employee.setEmployeeID(rs.getInt("EmployeeID"));
        //         employee.setFirstName(rs.getString("FirstName"));
        //         employee.setLastName(rs.getString("LastName"));
        //         employee.setEmail(rs.getString("Email"));
        //         employee.setPhoneNumber(rs.getString("PhoneNumber"));
        //         employee.setJobID(rs.getInt("JobID"));
        //         employee.setDepartmentID(rs.getInt("DepartmentID"));
        //         employee.setSalary(rs.getDouble("Salary"));
        //         employee.setManagerID(rs.getInt("ManagerID"));

        //         list.add(employee);
        //     }
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }
        // return list;

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public boolean addEmployee(Employee employee) {
        String sql = "INSERT INTO Employees (FirstName, LastName, Email, PhoneNumber, JobID, DepartmentID, Salary, ManagerID) VALUES (?,?,?,?,?,?,?,?)";
        Connection conn = null;
    
        try {
            conn = DatabaseConnection.getConnection(); // Khởi tạo conn
            conn.setAutoCommit(false); // Tắt chế độ auto-commit
            
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                // Thiết lập thông tin cho PreparedStatement
                ps.setString(1, employee.getFirstName());
                ps.setString(2, employee.getLastName());
                ps.setString(3, employee.getEmail());
                ps.setString(4, employee.getPhoneNumber());
                ps.setInt(5, employee.getJobID());
                ps.setInt(6, employee.getDepartmentID());
                ps.setDouble(7, employee.getSalary());
                ps.setInt(8, employee.getManagerID());
                
                // Thực hiện cập nhật
                int rows = ps.executeUpdate();
                
                // Gọi commit() nếu mọi thứ thành công
                conn.commit();
                
                return rows > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Ghi log lỗi
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback nếu có lỗi
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close(); // Đảm bảo kết nối được đóng
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
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
            conn.commit();
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
