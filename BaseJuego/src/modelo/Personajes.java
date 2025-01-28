/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ruben.banber
 */
public class Personajes {
    private int id_personaje;
    private String nombre;
    private String descripcion;
    private int id_videojuego;

    public Personajes() {
    }

    public Personajes(int id_personaje, String nombre, String descripcion, int id_videojuego) {
        this.id_personaje = id_personaje;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.id_videojuego = id_videojuego;
    }

    public int getId_personaje() {
        return id_personaje;
    }

    public void setId_personaje(int id_personaje) {
        this.id_personaje = id_personaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {

        this.descripcion = descripcion;
    }

    public int getId_videojuego() {
        return id_videojuego;
    }

    public void setId_videojuego(int id_videojuego) {
        this.id_videojuego = id_videojuego;
    }

    @Override
    public String toString() {
        return "Personaje{" + "id_personaje=" + id_personaje + ", nombre=" + nombre + ", descripcion=" + descripcion + ", id_videojuego=" + id_videojuego + '}';
    }
    
    
}
