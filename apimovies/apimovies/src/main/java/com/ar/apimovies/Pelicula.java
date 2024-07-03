package com.ar.apimovies;

public class Pelicula {

    private int idPelicula;  
    private String titulo ;
    private String description;
    private String duracion;
    private String genero;
    private String imagen;

    public Pelicula(){


    }

    public Pelicula(int idPelicula, String titulo, String description, String duracion, String genero, String imagen) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.description = description;
        this.duracion = duracion;
        this.genero = genero;
        this.imagen = imagen;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Pelicula [idPelicula=" + idPelicula + ", titulo=" + titulo + ", description=" + description
                + ", duracion=" + duracion + ", genero=" + genero + ", imagen=" + imagen + "]";
    }
}