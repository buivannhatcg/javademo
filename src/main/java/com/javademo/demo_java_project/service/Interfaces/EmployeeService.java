package com.javademo.demo_java_project.service.Interfaces;

import java.util.List;

import com.javademo.demo_java_project.model.Employee;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id);
    boolean addEmployee(Employee employee);
    boolean updateEmployee(Employee employee);
    boolean deleteEmployee(int id);
}
