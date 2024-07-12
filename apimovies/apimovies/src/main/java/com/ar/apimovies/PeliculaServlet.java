package com.ar.apimovies;
import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper; // Importación de ObjectMapper de Jackson para convertir objetos Java a JSON y viceversa
import java.sql.Connection;
import java.sql.SQLException;

//END-POINT: http://localhost:8080/apimovies/peliculas

@WebServlet("/peliculas") /*http://elServidor/peliculas */
public class PeliculaServlet extends HttpServlet {

    private PeliculaDAO peliculaDAO = new PeliculaDAO();
    private ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        Connection conn = null;

        try {
            // Create a new connection to the database
            DatabaseConnection conexion = new DatabaseConnection();
            conn = conexion.getConnection();

            // Retrieve all peliculas using the DAO
            List<Pelicula> peliculas = peliculaDAO.getAllPeliculas();

            // Convert the list of peliculas to JSON
            String jsonResponse = objectMapper.writeValueAsString(peliculas);
            resp.setContentType("application/json");
            resp.getWriter().write(jsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        } finally {
            if (conn != null) {
                try {
                    conn.close(); // Close the database connection
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        try {
            // Read the Pelicula object from the request
            Pelicula pelicula = objectMapper.readValue(req.getInputStream(), Pelicula.class);

            // Insert the Pelicula into the database
            Long id = peliculaDAO.insertPelicula(pelicula);

            // Prepare the JSON response
            String jsonResponse = objectMapper.writeValueAsString(id);
            resp.setContentType("application/json");
            resp.getWriter().write(jsonResponse);

            if (id != null) {
                resp.setStatus(HttpServletResponse.SC_CREATED); // 201 Created
                resp.getWriter().println("Se insertó la pelicula con id: " + id);
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 Bad Request
                resp.getWriter().println("No se pudo insertar la pelicula");
            }
         } catch (IOException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }
}
