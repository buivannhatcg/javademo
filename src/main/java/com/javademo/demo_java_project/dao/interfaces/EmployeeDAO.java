package com.javademo.demo_java_project.dao.interfaces;

import java.util.List;

import com.javademo.demo_java_project.model.Employee;

public interface EmployeeDAO extends GennericDAO<Employee> {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id);
    int addEmployee(Employee employee);
    boolean updateEmployee(Employee employee);
    boolean deleteEmployee(int id);
}
