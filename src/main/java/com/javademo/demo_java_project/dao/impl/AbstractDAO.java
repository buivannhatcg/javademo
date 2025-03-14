package com.javademo.demo_java_project.dao.impl;

import java.sql.Connection;
import java.sql.Date;
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
    @Override
    public int create(String sql, Object... params) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            int id = 0;
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false); 
            statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
            setParameter(statement, params);
            statement.executeUpdate();
            rs = statement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            connection.commit();
            return id;
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                } 
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally{
            try {
                if (connection != null) {
                    DatabaseConnection.closeConnection(connection);
                }
                if (statement!= null) {
                    statement.close();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return 0;
    }

    private void setParameter(PreparedStatement statement, Object... params) {
        for (int i = 0; i < params.length; i++) {
            Object value = params[i];
            int index = i + 1;
            try {
                if (value instanceof Long) {
                    statement.setLong(index, (Long) value);
                }else if (value instanceof String) {
                    statement.setString(index, (String) value);
                }else if (value instanceof Integer) {
                    statement.setInt(index,  (Integer) value);
                }else if (value instanceof Double) {
                    statement.setDouble(index, (double) value);
                }else if (value instanceof java.util.Date) {
                    statement.setDate(index, new Date(((java.util.Date) value).getTime()));
                }else {
                    throw new IllegalArgumentException("Unsupported parameter type: " + value.getClass().getName());
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error setting parameter at index " + index, e);
            }
        }
    }

    @Override
    public boolean update(String sql, Object... params) {

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false); 
            statement = connection.prepareStatement(sql);
            setParameter(statement, params);
            int row = statement.executeUpdate();
            connection.commit();
            return row > 0;
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    return false;
                }
            }
            return false;
        } finally {
            try {
                if (connection!= null) {
                    DatabaseConnection.closeConnection(connection);
                }
                if (statement!= null) {
                    statement.close();
                }
            } catch (SQLException e2) {
                return false;
            }
        }
    }

    @Override
    public T getByID(String sql, RowMapper<T> rowMapper, Object... params) {
        T results = null;
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
                results = rowMapper.mapRow(rs);
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
            } catch (SQLException e) {
                return null;
            }
        }
    }

    @Override
    public boolean delete(String sql, Object... params) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false); 
            statement = connection.prepareStatement(sql);
            setParameter(statement, params);
            int row = statement.executeUpdate();
            connection.commit();
            return row > 0;
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    return false;
                }
            }
            return false;
        } finally {
            try {
                if (connection!= null) {
                    DatabaseConnection.closeConnection(connection);
                }
                if (statement!= null) {
                    statement.close();
                }
            } catch (SQLException e2) {
                return false;
            }
        }
    }
}
