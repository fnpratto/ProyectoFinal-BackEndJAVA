package com.ar.apimovies;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConneccionSQL {

    // Método main: Muestra un menú de opciones y llama a los métodos correspondientes 
	// insertarUsuario, actualizarUsuario, eliminarUsuario, listarUsuarios
	// según la opción seleccionada por el usuario.

    private static void insertarPelicula(Connection cn, Scanner scan) {
         try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Ingrese el título de la película: ");
            String titulo = reader.readLine();

            java.sql.Date fechaLanzamiento = null;
            boolean fechaValida = false;
            while (!fechaValida) {
                System.out.println("Ingrese la fecha de lanzamiento (YYYY-MM-DD): ");
                String fechaLanzamientoStr = reader.readLine();
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    fechaLanzamiento = new java.sql.Date(dateFormat.parse(fechaLanzamientoStr).getTime());
                    fechaValida = true; // Si la fecha se parsea correctamente, marcamos como válida
                } catch (ParseException e) {
                    System.out.println("Formato de fecha incorrecto. Ingrese la fecha en formato YYYY-MM-DD.");
                }
            }

            System.out.println("Ingrese el género: ");
            String genero = reader.readLine();

            int duracion = 0;
            boolean duracionValida = false;
            while (!duracionValida) {
                try {
                    System.out.println("Ingrese la duración (en minutos): ");
                    duracion = Integer.parseInt(reader.readLine());
                    if (duracion > 0) {
                        duracionValida = true; // Duración válida
                    } else {
                        System.out.println("La duración debe ser mayor que 0.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Debe ingresar un número entero para la duración.");
                }
            }

            System.out.println("Ingrese el reparto: ");
            String reparto = reader.readLine();

            System.out.println("Ingrese la sinapsis: ");
            String sinapsis = reader.readLine();

            int director = 0;
            boolean directorValido = false;
            while (!directorValido) {
                try {
                    System.out.println("Ingrese el ID del director: ");
                    director = Integer.parseInt(reader.readLine());
                    if (director > 0) {
                        directorValido = true; // ID de director válido
                    } else {
                        System.out.println("El ID del director debe ser mayor que 0.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Debe ingresar un número entero para el ID del director.");
                }
            }

            System.out.println("Ingrese la URL de la imagen: ");
            String imagen = reader.readLine();

            boolean activo = false;
            boolean activoValido = false;
            while (!activoValido) {
                System.out.println("¿La película está activa? (true/false): ");
                String activoStr = reader.readLine().toLowerCase();
                if (activoStr.equals("true") || activoStr.equals("false")) {
                    activo = Boolean.parseBoolean(activoStr);
                    activoValido = true; // Activo válido
                } else {
                    System.out.println("Debe ingresar true o false para indicar si la película está activa.");
                }
            }


            Pelicula pelicula = new Pelicula(titulo, fechaLanzamiento, genero, duracion, reparto, sinapsis, director, imagen, activo);

            String insertQuery = "INSERT INTO movies (Nombre, fecha_lanzamiento, genero, duracion, reparto, sinapsis, director, imagen, activo) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = cn.prepareStatement(insertQuery)) {
                pstm.setString(1, pelicula.getTitulo());
                pstm.setDate(2, new java.sql.Date(pelicula.getFechaLanzamiento().getTime()));
                pstm.setString(3, pelicula.getGenero());
                pstm.setInt(4, pelicula.getDuracion());
                pstm.setString(5, pelicula.getReparto());
                pstm.setString(6, pelicula.getSinapsis());
                pstm.setInt(7, pelicula.getDirector());
                pstm.setString(8, pelicula.getImagen());
                pstm.setBoolean(9, pelicula.isActivo());

                int affectedRows = pstm.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Película insertada correctamente");
                } else {
                    System.out.println("No se pudo insertar la película");
                }
            } catch (SQLException e) {
                System.out.println("Error al insertar la película: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error al ingresar los datos de la película: " + e.getMessage());
        }
    }

    //Esta funcion puede estar para activar peliculas
    private static void actualizarPelicula(Connection cn, Scanner scan) {

        try {
            System.out.println("Ingrese el título de la película a actualizar: ");
            scan.nextLine(); // Consume the newline character left by previous nextBoolean()

            String titulo = scan.nextLine(); // Leer el título de la película

            boolean activo = false;
            boolean inputValid = false;

            while (!inputValid) {
                System.out.println("Desea activar la película (true/false): ");
                String activoStr = scan.nextLine().trim(); // Leer y limpiar la entrada

                if (activoStr.equalsIgnoreCase("true")) {
                    activo = true;
                    inputValid = true;
                } else if (activoStr.equalsIgnoreCase("false")) {
                    activo = false;
                    inputValid = true;
                } else {
                    System.out.println("Entrada inválida. Ingrese 'true' o 'false'.");
                }
            }

            String updateQuery = "UPDATE movies SET activo = ? WHERE Nombre = ?";

            try (PreparedStatement pstm = cn.prepareStatement(updateQuery)) {
                pstm.setBoolean(1, activo);  // Set activo (boolean) at index 1
                pstm.setString(2, titulo);   // Set titulo (String) at index 2

                int result = pstm.executeUpdate();

                if (result > 0) {
                    System.out.println("Película actualizada exitosamente.");
                } else {
                    System.out.println("No se encontró la película con ese título especificado.");
                }
            } catch (SQLException e) {
                System.err.println("Error al actualizar la película");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.err.println("Error al leer la entrada del usuario.");
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

        String selectQuery = "SELECT id_movie, Nombre, fecha_lanzamiento, genero, duracion, reparto, sinapsis, director, imagen, activo FROM movies";
   
        try (Statement stm = cn.createStatement();
             ResultSet rs = stm.executeQuery(selectQuery)) {

            while (rs.next()) {
                Pelicula pelicula = new Pelicula();
                pelicula.setIdPelicula(rs.getInt("id_movie"));
                pelicula.setTitulo(rs.getString("Nombre"));
                pelicula.setFechaLanzamiento(rs.getDate("fecha_lanzamiento"));
                pelicula.setGenero(rs.getString("genero"));
                pelicula.setDuracion(rs.getInt("duracion"));
                pelicula.setReparto(rs.getString("reparto"));
                pelicula.setSinapsis(rs.getString("sinapsis"));
                pelicula.setDirector(rs.getInt("director"));
                pelicula.setImagen(rs.getString("imagen"));
                pelicula.setActivo(rs.getBoolean("activo"));

                peliculas.add(pelicula);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar las películas");
            e.printStackTrace();
        }
        return peliculas;
    }

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
            cn = coneccion.getConnection();
            if (cn != null) {
                System.out.println("Conexión establecida correctamente.");
    
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
            } else {
                System.out.println("No se pudo establecer la conexión.");
            }

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

    
}


