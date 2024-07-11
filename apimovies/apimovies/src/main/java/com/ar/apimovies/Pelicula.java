package com.ar.apimovies;

import java.util.Date;

public class Pelicula {

    private int idPelicula;  
    private String titulo;
    private Date fechaLanzamiento;
    private String genero;
    private int duracion;
    private String reparto;
    private String sinapsis;
    private int director; //TO-DO seguros??
    private String imagen;
    private boolean activo;


    public Pelicula(){


    }
    public static void main(String[] args) {
        Pelicula pelicula = new Pelicula();
        System.out.println(pelicula);
    }
    public Pelicula(int idPelicula, String titulo, Date fechaLanzamiento, String genero, int duracion, String reparto, String sinapsis, int director, String imagen, boolean activo) {
        this.idPelicula = idPelicula;
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

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getReparto() {
        return reparto;
    }

    public void setReparto(String reparto) {
        this.reparto = reparto;
    }

    public String getSinapsis() {
        return sinapsis;
    }

    public void setSinapsis(String sinapsis) {
        this.sinapsis = sinapsis;
    }

    public int getDirector() {
        return director;
    }

    public void setDirector(int director) {
        this.director = director;
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

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Pelicula [idPelicula=" + idPelicula + ", titulo=" + titulo + ", sinapsis=" + sinapsis
                + ", duracion=" + duracion + ", genero=" + genero + ", imagen=" + imagen + "]";
    }
}