package com.javademo.demo_java_project.mapper;

import java.sql.ResultSet;

public interface RowMapper<T> {

    T mapRow(ResultSet resultSet);

}
