package com.ar.apimovies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConneccionSQL {

    // Método main: Muestra un menú de opciones y llama a los métodos correspondientes 
	// insertarUsuario, actualizarUsuario, eliminarUsuario, listarUsuarios
	// según la opción seleccionada por el usuario.
    public static void main(String[] args) {
       
    	// Se crea una instancia de la clase Conexion para utilizar su método conectar, 
    	// que establece la conexión con la base de datos.
    	// Esto permite modularizar el código de conexión a la base de datos y 
    	// reutilizarlo en diferentes partes del programa.
    	DatabaseConnection coneccion = new DatabaseConnection();

    	// La variable Connection va a almacena la conexión activa a la base de datos.
        Connection cn = null;
        
        Scanner scan = new Scanner(System.in);

        try {
            // Establecer conexión
            cn = coneccion.conectar();

            // Menú de opciones
            int opcion;
            do {
                System.out.println("\nSeleccione una opción:");
                System.out.println("1. Insertar una nuevo pelicula");
                System.out.println("2. Actualizar los datos de la pelicula");
                System.out.println("3. Eliminar una pelicula");
                System.out.println("4. Listar todas las peliculas");
                System.out.println("5. Salir");
                opcion = scan.nextInt();

                switch (opcion) {
                    case 1:
                        insertarPelicula(cn, scan);
                        break;
                    case 2:
                        actualizarPelicula(cn, scan);
                        break;
                    case 3:
                        eliminarPelicula(cn, scan);
                        break;
                    case 4:
                        List<Pelicula> usuarios = listarPeliculas(cn);
                        usuarios.forEach(System.out::println);
                        break;
                    case 5:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } while (opcion != 5);

        } finally {
            // Cerrar recursos en el orden inverso de su apertura
            try {
                if (cn != null) cn.close();
                scan.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void insertarPelicula(Connection cn, Scanner scan) {
        System.out.println("Ingrese el título de la película: ");
        String titulo = scan.next();
        System.out.println("Ingrese la descripción: ");
        String description = scan.next();
        System.out.println("Ingrese la duración: ");
        String duracion = scan.next();
        System.out.println("Ingrese el género: ");
        String genero = scan.next();
        /*System.out.println("Ingrese la URL de la imagen: ");
        String imagen = scan.next();*/

        String insertQuery = "INSERT INTO peliculas (titulo, description, duracion, genero) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstm = cn.prepareStatement(insertQuery)) {
            pstm.setString(1, titulo);
            pstm.setString(2, description);
            pstm.setString(3, duracion);
            pstm.setString(4, genero);
            //pstm.setString(5, imagen);

            pstm.executeUpdate();

            System.out.println("Usuario insertado exitosamente.");

        } catch (SQLException e) {

            System.err.println("Error al insertar el usuario");
            e.printStackTrace();
        }
    }

    private static void actualizarPelicula(Connection cn, Scanner scan) {
        System.out.println("Ingrese el título de la película a actualizar: ");
        String titulo = scan.next();
        System.out.println("Ingrese la nueva descripción: ");
        String description = scan.next();
        System.out.println("Ingrese la nueva duración: ");
        String duracion = scan.next();
        System.out.println("Ingrese el nuevo género: ");
        String genero = scan.next();
        /*System.out.println("Ingrese la nueva URL de la imagen: ");
        String imagen = scan.next();*/

        String updateQuery = "UPDATE peliculas SET description = ?, duracion = ?, genero = ?,  WHERE titulo = ?";

        try (PreparedStatement pstm = cn.prepareStatement(updateQuery)) {
            pstm.setString(1, description);
            pstm.setString(2, duracion);
            pstm.setString(3, genero);
            //pstm.setString(4, imagen);
            pstm.setString(5, titulo);

            int result = pstm.executeUpdate();

            if (result > 0) {
                System.out.println("Pelicula actualizada exitosamente.");
            } else {
                System.out.println("No se encontró la pelicula con ese nombre especificado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar la pelicula");
            e.printStackTrace();
        }
    }

    private static void eliminarPelicula(Connection cn, Scanner scan) {

        List<Pelicula> peliculas = listarPeliculas(cn);

        System.out.println("Lista de películas disponibles:");
        for (Pelicula pelicula : peliculas) {
            System.out.println("ID: " + pelicula.getIdPelicula() + ", Título: " + pelicula.getTitulo());
        }

        System.out.println("Ingrese el ID de la película a eliminar: "); 
        int id = scan.nextInt();

        String deleteQuery = "DELETE FROM peliculas WHERE idPelicula = ?";

        try (PreparedStatement pstm = cn.prepareStatement(deleteQuery)) {
            pstm.setInt(1, id);

            int result = pstm.executeUpdate();

            if (result > 0) {
                System.out.println("Película eliminada exitosamente.");
            } else {
                System.out.println("No se encontró una película con el ID especificado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar la película");
            e.printStackTrace();
        }
    }

    private static List<Pelicula> listarPeliculas(Connection cn) {

        List<Pelicula> peliculas = new ArrayList<>();

        String selectQuery = "SELECT idPelicula, titulo, description, duracion, genero FROM peliculas";


        try (Statement stm = cn.createStatement();
             ResultSet rs = stm.executeQuery(selectQuery)) {

            while (rs.next()) {
                int idPelicula = rs.getInt("idPelicula");
                String titulo = rs.getString("titulo");
                String description = rs.getString("description");
                String duracion = rs.getString("duracion");
                String genero = rs.getString("genero");
                String imagen = rs.getString("imagen");

                Pelicula pelicula = new Pelicula(idPelicula, titulo, description, duracion, genero, imagen);
                peliculas.add(pelicula);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar las películas");
            e.printStackTrace();
        }
        return peliculas;
    }
    
}


