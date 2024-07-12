package com.ar.apimovies;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            String url = "jdbc:mysql://localhost:3306/movies_cac";
            String user = "root";
            String password = "";

            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successful!");

            // Close the connection
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
