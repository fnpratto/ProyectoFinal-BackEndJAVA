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

        Connection cn = conexion.getConnection();

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
        Connection cn = conexion.getConnection();
        List<Pelicula> peliculas = new ArrayList<>();
        String query = "SELECT idPelicula, titulo, fechaLanzamiento, genero, duracion, reparto, sinapsis, director, imagen, activo FROM movies";
        
        try (PreparedStatement pstm = cn.prepareStatement(query);
             ResultSet resultSet = pstm.executeQuery()) {

            while (resultSet.next()) {
                Pelicula pelicula = new Pelicula();
                pelicula.setIdPelicula(resultSet.getInt("idPelicula"));
                pelicula.setTitulo(resultSet.getString("titulo"));
                pelicula.setFechaLanzamiento(resultSet.getDate("fechaLanzamiento"));
                pelicula.setGenero(resultSet.getString("genero"));
                pelicula.setDuracion(resultSet.getInt("duracion"));
                pelicula.setReparto(resultSet.getString("reparto"));
                pelicula.setSinapsis(resultSet.getString("sinapsis"));
                pelicula.setDirector(resultSet.getInt("director"));
                pelicula.setImagen(resultSet.getString("imagen"));
                pelicula.setActivo(resultSet.getBoolean("activo"));
                peliculas.add(pelicula);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching peliculas: " + e.getMessage());
        }

        return peliculas;
    }

    public void desactivarPelicula(Long idPelicula) {
        DatabaseConnection conexion = new DatabaseConnection();
        Connection cn = conexion.getConnection();

        String updateQuery = "UPDATE movies SET activo = false WHERE idPelicula = ?";

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
