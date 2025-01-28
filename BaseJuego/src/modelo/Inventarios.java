/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ruben.banber
 */
public class Inventarios {
    private int id_invetario;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private int id_personaje;

    public Inventarios() {
    }

    public Inventarios(int id_invetario, String nombre, String descripcion, int cantidad, int id_personaje) {
        this.id_invetario = id_invetario;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.id_personaje = id_personaje;
    }

    public int getId_invetario() {
        return id_invetario;
    }

    public void setId_invetario(int id_invetario) {
        this.id_invetario = id_invetario;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId_personaje() {
        return id_personaje;
    }

    public void setId_personaje(int id_personaje) {
        this.id_personaje = id_personaje;
    }

    @Override
    public String toString() {
        return "Inventario{" + "id_invetario=" + id_invetario + ", nombre=" + nombre + ", descripcion=" + descripcion + ", cantidad=" + cantidad + ", id_personaje=" + id_personaje + '}';
    }
    
   
    
}
