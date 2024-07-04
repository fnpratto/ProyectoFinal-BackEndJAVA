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
                System.out.println("2. Activar una pelicula");
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
        System.out.println("Ingrese la fecha de lanzamiento (YYYY-MM-DD): ");
        String fechaLanzamiento = scan.next();
        System.out.println("Ingrese el género: ");
        String genero = scan.next();
        System.out.println("Ingrese la duración (en minutos): ");
        int duracion = scan.nextInt();
        System.out.println("Ingrese el reparto: ");
        String reparto = scan.next();
        System.out.println("Ingrese la sinapsis: ");
        String sinapsis = scan.next();
        System.out.println("Ingrese el ID del director: ");
        int director = scan.nextInt();
        /*System.out.println("Ingrese la URL de la imagen: ");
        String imagen = scan.next();*/
        System.out.println("¿La película está activa? (true/false): ");
        boolean activo = scan.nextBoolean();

        String insertQuery = "INSERT INTO movies (Nombre, fecha_lanzamiento, genero, duracion, reparto, sinapsis, director, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstm = cn.prepareStatement(insertQuery)) {
            pstm.setDate(1, java.sql.Date.valueOf(fechaLanzamiento));
            pstm.setString(2, genero);
            pstm.setInt(3, duracion);
            pstm.setString(4, reparto);
            pstm.setString(5, sinapsis);
            pstm.setInt(6, director);
            //pstm.setString(7, imagen);
            pstm.setBoolean(8, activo);
            pstm.setString(9, titulo);

            pstm.executeUpdate();

            System.out.println("Pelicula insertada exitosamente.");

        } catch (SQLException e) {

            System.err.println("Error al insertar el usuario");
            e.printStackTrace();
        }
    }

    //Esta funcion puede estar para activar peliculas
    private static void actualizarPelicula(Connection cn, Scanner scan) {


        System.out.println("Ingrese el título de la película a actualizar: ");
        String titulo = scan.next();
       /* System.out.println("Ingrese la nueva fecha de lanzamiento (YYYY-MM-DD): ");
        String fechaLanzamiento = scan.next();
        System.out.println("Ingrese el nuevo género: ");
        String genero = scan.next();
        System.out.println("Ingrese la nueva duración (en minutos): ");
        int duracion = scan.nextInt();
        System.out.println("Ingrese el nuevo reparto: ");
        String reparto = scan.next();
        System.out.println("Ingrese la nueva sinapsis: ");
        String sinapsis = scan.next();
        System.out.println("Ingrese el nuevo ID del director: "); // TO-DO Cambiar por el nombre del director??
        int director = scan.nextInt();*/
        /*System.out.println("Ingrese la nueva URL de la imagen: ");
        String imagen = scan.next();*/
        System.out.println("Desea activar la pelicula (true/false): ");
        boolean activo = scan.nextBoolean();

        String updateQuery = "UPDATE movies SET activo = ? WHERE Nombre = ?";

        try (PreparedStatement pstm = cn.prepareStatement(updateQuery)) {
            /*pstm.setDate(1, java.sql.Date.valueOf(fechaLanzamiento));
            pstm.setString(2, genero);
            pstm.setInt(3, duracion);
            pstm.setString(4, reparto);
            pstm.setString(5, sinapsis);
            pstm.setInt(6, director);
            pstm.setString(7, imagen);*/
            pstm.setBoolean(8, activo);
            pstm.setString(9, titulo);

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


    //desactivar
    private static void eliminarPelicula(Connection cn, Scanner scan) {

        List<Pelicula> peliculas = listarPeliculas(cn);

        System.out.println("Lista de películas disponibles:");
        for (Pelicula pelicula : peliculas) {
            System.out.println("ID: " + pelicula.getIdPelicula() + ", Título: " + pelicula.getTitulo());
        }

        System.out.println("Ingrese el ID de la película a desactivar: "); 
        int id = scan.nextInt();

        String updateQuery = "UPDATE movies SET activo = false WHERE id_movie = ?";

        try (PreparedStatement pstm = cn.prepareStatement(updateQuery)) {
            pstm.setLong(1, id);
    
            int result = pstm.executeUpdate();
    
            if (result > 0) {
                System.out.println("Película desactivada exitosamente.");
            } else {
                System.out.println("No se encontró una película con el ID especificado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al desactivar la película");
            e.printStackTrace();
        }
    }

    private static List<Pelicula> listarPeliculas(Connection cn) {

        List<Pelicula> peliculas = new ArrayList<>();

        String selectQuery = "SELECT id_movie, Nombre, fecha_lanzamiento, genero, duracion, reparto, sinapsis, director, imagen FROM movies WHERE activo = true";

        try (Statement stm = cn.createStatement();
             ResultSet rs = stm.executeQuery(selectQuery)) {

            while (rs.next()) {
                int idPelicula = rs.getInt("idPelicula");
                String titulo = rs.getString("Nombre");
                String fechaLanzamiento = rs.getDate("fecha_lanzamiento").toString();
                String genero = rs.getString("genero");
                int duracion = rs.getInt("duracion");
                String reparto = rs.getString("reparto");
                String sinapsis = rs.getString("sinapsis");
                int director = rs.getInt("director");
                String imagen = rs.getString("imagen");
                //boolean activo = rs.getBoolean("activo");

                Pelicula pelicula = new Pelicula(idPelicula, titulo, fechaLanzamiento, genero, duracion, reparto, sinapsis, director, imagen, true);
                peliculas.add(pelicula);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar las películas");
            e.printStackTrace();
        }
        return peliculas;
    }
    
}


