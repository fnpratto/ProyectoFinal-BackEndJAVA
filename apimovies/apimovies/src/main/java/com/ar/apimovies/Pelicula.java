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

    public void setIdPelicula(int int1) {
        this.idPelicula = int1;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String string) {
        this.titulo = string;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(java.sql.Date date) {
        this.fechaLanzamiento = date;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String string) {
        this.genero = string;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int int1) {
        this.duracion = int1;
    }

    public String getReparto() {
        return reparto;
    }

    public void setReparto(String string) {
        this.reparto = string;
    }

    public String getSinapsis() {
        return sinapsis;
    }

    public void setSinapsis(String string) {
        this.sinapsis = string;
    }

    public int getDirector() {
        return director;
    }

    public void setDirector(int int1) {
        this.director = int1;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean boolean1) {
        this.activo = boolean1;
    }

    @Override
    public String toString() {
        return "Pelicula [idPelicula=" + idPelicula + ", titulo=" + titulo + ", sinapsis=" + sinapsis
                + ", duracion=" + duracion + ", genero=" + genero + ", imagen=" + imagen + "]";
    } 
}