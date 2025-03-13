package com.javademo.demo_java_project.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javademo.demo_java_project.dao.interfaces.GennericDAO;
import com.javademo.demo_java_project.mapper.RowMapper;
import com.javademo.demo_java_project.util.DatabaseConnection;

public class AbstractDAO<T> implements GennericDAO<T> {

    @Override
    public List<T> quer(String sql, RowMapper<T> rowMapper, Object... params) {
        List<T> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(sql);
            //Set parameters()
            setParameter(statement, params);
            rs = statement.executeQuery();
            while (rs.next()) {
                results.add(rowMapper.mapRow(rs));
            }
            return results;
        } catch (SQLException e) {
            return null;
        }finally{
            try {
                if (connection != null) {
                    DatabaseConnection.closeConnection(connection);
                }
                if (statement != null) {
                    statement.close();
                } 
                if (rs!= null) {
                    rs.close();
                }
            } catch (Exception e) {
                return null;
            }
        }
    }

    private void setParameter(PreparedStatement statement, Object... params) {
        for (int i = 0; i < params.length; i++) {
            Object value = params[i];
            try {
                if (value instanceof Long) {
                    statement.setLong(i + 1, (Long) value);
                }else if (value instanceof String) {
                    statement.setString(i + 1, (String) value);
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error setting parameter at index " + i, e);
            }
        }
    }

}
