package com.javademo.demo_java_project.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Employee {

    private int employeeID;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date hỉreDate;
    private int jobID;
    private int departmentID;
    private double salary;
    private int managerID;
    public Employee(){};

}
