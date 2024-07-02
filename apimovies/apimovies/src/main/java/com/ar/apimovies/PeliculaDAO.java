package com.ar.apimovies;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;


public class PeliculaDAO {

    public Long insertPelicula(Pelicula pelicula) {
        DatabaseConnection conexion = new DatabaseConnection();

        Statement stmt = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String insertarPeliculaSql = "INSERT INTO peliculas (titulo, genero, director, duracion) VALUES (?, ?, ?, ?)";

        Connection cn = conexion.conectar();

        try {
            pstm = cn.prepareStatement(insertarPeliculaSql);
            pstm.setString(1, pelicula.getTitulo());
            pstm.setString(2, pelicula.getGenero());
            pstm.setString(3, pelicula.getDuracion());
            pstm.setString(4, pelicula.getImagen());
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

            while (rs.next()){
                Long idPel = rs.getLong("idPelicula"); // no  acepte columnLabel:
                String tit = rs.getString("titulo");
                String gen = rs.getString("genero");
                String dir = rs.getString("director");
                String dur = rs.getString("duracion");
                String imag = rs.getString("imagen");

                Pelicula pelicula = new Pelicula(idPel,tit,gen,dir,dur,imag);

                peliculas.add(pelicula);
            }

            return peliculas;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
