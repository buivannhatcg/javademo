package com.javademo.demo_java_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication  
public class DemoJavaProjectApplication {  
    // private static final String URL = "jdbc:sqlserver://localdb\\mssqllocaldb;databaseName=employee_db;encrypt=true;trustServerCertificate=true";
    // // private static final String URL = "jdbc:sqlserver://OCG-DESK-76\\LOCALDB#994A8;databaseName=employee_db";  
    // private static final String USER = "sa";
    // private static final String PASS = "12345678aA@";
    // private static final String DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  

    // public static void checkConnection() {  
    //     System.out.println("🔍 Đang kiểm tra kết nối đến SQL Server...");  
    //     try {  
    //         Class.forName(DRIVER_CLASS);  
    //         try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {  
    //             if (conn != null) {  
    //                 System.out.println("✅ Kết nối thành công!");  
    //             } else {  
    //                 System.err.println("❌ Kết nối thất bại!");  
    //             }  
    //         }  
    //     } catch (ClassNotFoundException e) {  
    //         System.err.println("⚠ Lỗi: Không tìm thấy driver JDBC. Hãy kiểm tra lại thư viện SQL Server JDBC.");  
    //         e.printStackTrace();  
    //     } catch (SQLException e) {  
    //         System.err.println("⚠ Lỗi: Không thể kết nối đến database.");  
    //         System.err.println("📌 Kiểm tra lại URL, firewall, SQL Server Browser, và xác thực đăng nhập.");  
    //         e.printStackTrace();  
    //     }  
    // }  

    public static void main(String[] args) {  
        // checkConnection();  
        SpringApplication.run(DemoJavaProjectApplication.class, args);  
    }  
}  
