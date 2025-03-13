package com.javademo.demo_java_project.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.javademo.demo_java_project.model.Employee;

public class EmployeeMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs) {
        try {
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
            return employee;
        } catch (SQLException e) {
            return null;
        }
    }

}
