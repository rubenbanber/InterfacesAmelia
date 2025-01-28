/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author ruben.banber
 */
public class Videojuegos {
    private int id_videojuego;
    private String nombre;
    private String desarrollador;
    private Date fecha_lanzamiento;
    private String genero;
    private String plataforma;

    public Videojuegos() {
    }

    public Videojuegos(int id_videojuego, String nombre, String desarrollador, Date fecha_lanzamiento, String genero, String plataforma) {
        this.id_videojuego = id_videojuego;
        this.nombre = nombre;
        this.desarrollador = desarrollador;
        this.fecha_lanzamiento = fecha_lanzamiento;
        this.genero = genero;
        this.plataforma = plataforma;
    }

    public int getId_videojuego() {
        return id_videojuego;
    }

    public void setId_videojuego(int id_videojuego) {
        this.id_videojuego = id_videojuego;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(String desarrollador) {
        this.desarrollador = desarrollador;
    }

    public Date getFecha_lanzamiento() {
        return fecha_lanzamiento;
    }

    public void setFecha_lanzamiento(Date fecha_lanzamiento) {
        this.fecha_lanzamiento = fecha_lanzamiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    @Override
    public String toString() {
        return "Videojuego{" + "id_videojuego=" + id_videojuego + ", nombre=" + nombre + ", desarrollador=" + desarrollador + ", fecha_lanzamiento=" + fecha_lanzamiento + ", genero=" + genero + ", plataforma=" + plataforma + '}';
    }
    
    
}
