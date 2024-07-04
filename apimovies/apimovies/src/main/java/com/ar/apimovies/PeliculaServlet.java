package com.ar.apimovies;

import java.util.List;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import com.google.gson.Gson;
//import com.ar.apimovies.model.Pelicula;
//import com.ar.apimovies.dao.PeliculaDAO;

import com.fasterxml.jackson.databind.ObjectMapper;

//import com.fasterxml.jackson.datablind.ObjectMapper;

//END-POINT: http://localhost:8080/apimovies/peliculas

@WebServlet("/peliculas") /*http://elServidor/peliculas */
public class PeliculaServlet extends HttpServlet {

    private PeliculaDAO peliculaDAO = new PeliculaDAO();

    private ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        try	{
            List<Pelicula> peliculas = peliculaDAO.getAllPeliculas();
            String jsonResponse = objectMapper.writeValueAsString(peliculas);
            resp.setContentType("application/json");
            resp.getWriter().write(jsonResponse);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ocurrio un error al obtener las peliculas");
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*resp.setHeader("Acess-Control-Allow-Origin", "*");
        resp.setHeader("Acess-Control-Allow-Methods", "*");
        resp.setHeader("Acces-Control-Alloc_Headers", "Content-Type");*/

        req.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        Pelicula pelicula = objectMapper.readValue(req.getInputStream(), Pelicula.class);
        Long id = peliculaDAO.insertPelicula(pelicula);
        String jsonResponse = objectMapper.writeValueAsString(id);
        resp.setContentType("application/json");
        resp.getWriter().write(jsonResponse);

        /*if (id != null) {
            resp.getWriter().println("Se inserto la pelicula con id: " + id);
        } else {
            resp.getWriter().println("No se pudo insertar la pelicula");
        }*/

        resp.setStatus(HttpServletResponse.SC_CREATED);

    }
}
