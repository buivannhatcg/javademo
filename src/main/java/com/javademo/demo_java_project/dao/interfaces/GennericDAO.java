package com.javademo.demo_java_project.dao.interfaces;

import java.util.List;

import com.javademo.demo_java_project.mapper.RowMapper;

public interface GennericDAO<T> {
    List <T> quer(String sql, RowMapper<T> rowMapper, Object... params);
    T getByID(String sql, RowMapper<T> rowMapper, Object... params);
    boolean update(String sql, Object... params);
    boolean delete(String sql, Object... params);
    int create(String sql, Object... params);
}
