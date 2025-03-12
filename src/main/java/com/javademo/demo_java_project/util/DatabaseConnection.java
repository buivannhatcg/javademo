package com.javademo.demo_java_project.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static final String PROPERTIES_FILE = "application.properties";
    private static String URL;
    private static String USER;
    private static String PASS;
    // private static boolean USE_INTEGRATED_SECURITY;

    static {
        try (InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            Properties props = new Properties();
            if (input == null) {
                throw new IOException("Không tìm thấy file " + PROPERTIES_FILE);
            }
            props.load(input);

            URL = props.getProperty("spring.datasource.url");
            USER = props.getProperty("spring.datasource.username");
            PASS = props.getProperty("spring.datasource.password");
            String driverClass = props.getProperty("spring.datasource.driver-class-name");

            // Load driver JDBC (chỉ cần nếu chạy bằng JDBC thuần)
            if (driverClass != null && !driverClass.isEmpty()) {
                Class.forName(driverClass);
            }

            // Kiểm tra nếu dùng Windows Authentication
            // USE_INTEGRATED_SECURITY = URL.contains("integratedSecurity=true");

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Lỗi khi tải cấu hình database: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Lỗi khi đóng kết nối: " + e.getMessage());
            }
        }
    }
}
