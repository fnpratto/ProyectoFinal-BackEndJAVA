package com.ar.apimovies;

import java.util.Date;

public class Pelicula {
    private int idPelicula; // No need to set in constructor if auto-generated
    private String titulo;
    private Date fechaLanzamiento;
    private String genero;
    private int duracion;
    private String reparto;
    private String sinapsis;
    private int director;
    private String imagen;
    private boolean activo;


    public Pelicula(){


    }
    public static void main(String[] args) {
        Pelicula pelicula = new Pelicula();
        System.out.println(pelicula);
    }
    public Pelicula(String titulo, Date fechaLanzamiento, String genero, int duracion, String reparto, String sinapsis, int director, String imagen, boolean activo) {
        this.titulo = titulo;
        this.fechaLanzamiento = fechaLanzamiento;
        this.genero = genero;
        this.duracion = duracion;
        this.reparto = reparto;
        this.sinapsis = sinapsis;
        this.director = director;
        this.imagen = imagen;
        this.activo = activo;
    }
    public Pelicula(int id, String titulo, Date fechaLanzamiento, String genero, int duracion, String reparto, String sinapsis, int director, String imagen, boolean activo) {
        this.idPelicula = id;
        this.titulo = titulo;
        this.fechaLanzamiento = fechaLanzamiento;
        this.genero = genero;
        this.duracion = duracion;
        this.reparto = reparto;
        this.sinapsis = sinapsis;
        this.director = director;
        this.imagen = imagen;
        this.activo = activo;
    }


    // Getters
    public int getIdPelicula() {
        return idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public String getGenero() {
        return genero;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getReparto() {
        return reparto;
    }

    public String getSinapsis() {
        return sinapsis;
    }

    public int getDirector() {
        return director;
    }

    public String getImagen() {
        return imagen;
    }

    public boolean isActivo() {
        return activo;
    }

    @Override
    public String toString() {
        return "Pelicula [idPelicula=" + idPelicula + ", titulo=" + titulo + ", sinapsis=" + sinapsis
                + ", duracion=" + duracion + ", genero=" + genero + ", imagen=" + imagen + "]";
    } //todo
}