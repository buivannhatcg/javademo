package com.javademo.demo_java_project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Department {

    private int departmentID;
    private String departmentName;
    private int locationID;
}
