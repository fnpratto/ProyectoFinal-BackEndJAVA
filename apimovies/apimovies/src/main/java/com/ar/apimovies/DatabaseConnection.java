package com.ar.apimovies;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DatabaseConnection {

    private static final String CONTROLAR = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/movies_cac";
    private static final String USER = "root";
    private static final String PASS = "";

    static {
        try {
            Class.forName(CONTROLAR);
            System.out.println("Driver loaded");
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver: " + e.getMessage());
            e.printStackTrace();
        }
    }
    

    public Connection conectar() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexión establecida");
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión con la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
        return connection; // Devuelve la conexión establecida o null si hay un error
    }
}
