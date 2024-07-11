package com.ar.apimovies;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PeliculaDAO {

    public Long insertPelicula(Pelicula pelicula) {
        DatabaseConnection conexion = new DatabaseConnection();

        //Statement stmt = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String insertarPeliculaSql = "INSERT INTO peliculas (titulo, genero, director, duracion) VALUES (?, ?, ?, ?)";

        Connection cn = conexion.conectar();

        try {
            pstm = cn.prepareStatement(insertarPeliculaSql);
            pstm.setString(1, pelicula.getTitulo());
            pstm.setDate(2, new java.sql.Date(pelicula.getFechaLanzamiento().getTime()));
            pstm.setString(3, pelicula.getGenero());
            pstm.setInt(4, pelicula.getDuracion());
            pstm.setString(5, pelicula.getReparto());
            pstm.setString(6, pelicula.getSinapsis());
            pstm.setInt(7, pelicula.getDirector());
            pstm.setString(8, pelicula.getImagen());
            pstm.setBoolean(9, pelicula.isActivo());

            int result = pstm.executeUpdate();

            if (result > 0 ){
                rs= pstm.getGeneratedKeys();

                if(rs.next()){
                    System.out.println("Se inserto la pelicula con id: " + rs.getLong(1));
                    return rs.getLong(1);
                }else{
                    System.out.println("No se pudo obtener el id de la pelicula");
                    return null;
                }
            }
            else{
                System.out.println("No se pudo insertar la pelicula");
                return null;
            }
        }catch ( Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Pelicula> getAllPeliculas(){
        DatabaseConnection conexion = new DatabaseConnection();
        Connection cn = conexion.conectar();
        List<Pelicula> peliculas = new ArrayList<>();
        PreparedStatement pstm = null; // Change Statement to PreparedStatement
        ResultSet rs = null;
        String selectPeliculasSql = "SELECT * FROM peliculas";

        try {
            pstm = cn.prepareStatement(selectPeliculasSql);
            rs = pstm.executeQuery(); // Correct usage for PreparedStatement

            while (rs.next()) {
                int idPelicula = rs.getInt("id_pelicula");
                String titulo = rs.getString("Nombre");
                Date fechaLanzamiento = new java.util.Date(rs.getDate("fecha_lanzamiento").getTime());
                String genero = rs.getString("genero");
                int duracion = rs.getInt("duracion");
                String reparto = rs.getString("reparto");
                String sinapsis = rs.getString("sinapsis");
                int director = rs.getInt("director");
                String imagen = rs.getString("imagen");
                boolean activo = rs.getBoolean("activo");

                Pelicula pelicula = new Pelicula(idPelicula, titulo, fechaLanzamiento, genero, duracion, reparto, sinapsis, director, imagen, activo);
                peliculas.add(pelicula);
            }

            return peliculas;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void desactivarPelicula(Long idPelicula) {
        DatabaseConnection conexion = new DatabaseConnection();
        Connection cn = conexion.conectar();

        String updateQuery = "UPDATE movies SET activo = false WHERE id_movie = ?";

        try (PreparedStatement pstm = cn.prepareStatement(updateQuery)) {
            pstm.setLong(1, idPelicula);

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
}
