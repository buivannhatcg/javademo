package com.javademo.demo_java_project.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Leave {
    private int leaveID;
    private int employeeID;
    private Date startDate;
    private Date endDate;
    private String leaveType;
    private String reason;
    private String status;
}
