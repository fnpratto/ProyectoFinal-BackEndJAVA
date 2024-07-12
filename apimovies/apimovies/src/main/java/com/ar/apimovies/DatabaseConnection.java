package com.ar.apimovies;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DatabaseConnection {

    private static final String CONTROLAR = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/movies_cac";
    private static final String USER = "root";
    private static final String PASS = "";
    private Connection connection;

    public DatabaseConnection() {
        try {
            Class.forName(CONTROLAR);
            System.out.println("Driver loaded");
            connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexión establecida");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  // Imprimir el error en caso de no encontrar el driver
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión con la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

      // Método para obtener la conexión GETTER
    public Connection getConnection() {
        return connection;  // Devuelve el objeto Connection establecido
    }

    public void close() {
        try {
            // Verificar si la conexión no es nula y está abierta, entonces cerrarla
            if (connection != null && !connection.isClosed()) {
                connection.close();  // Cierra la conexión a la base de datos
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Imprimir el error en caso de problemas al cerrar la conexión
        }
    }
}
