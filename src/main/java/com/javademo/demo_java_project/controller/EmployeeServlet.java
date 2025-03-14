package com.javademo.demo_java_project.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.javademo.demo_java_project.model.Employee;
import com.javademo.demo_java_project.service.Interfaces.EmployeeService;

@WebServlet(urlPatterns = {"/employees"})
public class EmployeeServlet extends HttpServlet{

    // private final EmployeeServiceImpl service = new EmployeeServiceImpl();
    @Inject
    private EmployeeService service;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> list = service.getAllEmployees();
        request.setAttribute("employees", list);
        request.getRequestDispatcher("employees.jsp").forward(request, response);
    }
}
