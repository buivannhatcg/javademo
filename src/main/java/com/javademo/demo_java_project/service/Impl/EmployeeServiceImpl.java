package com.javademo.demo_java_project.service.Impl;

import java.util.List;

import javax.inject.Inject;

import com.javademo.demo_java_project.dao.impl.EmployeeDAOImpl;
import com.javademo.demo_java_project.model.Employee;
import com.javademo.demo_java_project.service.Interfaces.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService{

    @Inject
    private EmployeeDAOImpl dao ;
    @Override
    public List<Employee> getAllEmployees() {
        return dao.getAllEmployees();
    }

    @Override
    public Employee getEmployeeById(int id) {
        return dao.getEmployeeById(id);
    }

    @Override
    public int addEmployee(Employee employee) {
        return dao.addEmployee(employee);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return dao.updateEmployee(employee);
    }

    @Override
    public boolean deleteEmployee(int id) {
        return dao.deleteEmployee(id);
    }

}
