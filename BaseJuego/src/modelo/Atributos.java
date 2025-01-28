/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ruben.banber
 */
public class Atributos {
    private int id_atributo;
    private String nombre;
    private int valor;
    private int id_personaje;

    public Atributos() {
    }

    public Atributos(int id_atributo, String nombre, int valor, int id_personaje) {
        this.id_atributo = id_atributo;
        this.nombre = nombre;
        this.valor = valor;
        this.id_personaje = id_personaje;
    }

    public int getId_atributo() {
        return id_atributo;
    }

    public void setId_atributo(int id_atributo) {
        this.id_atributo = id_atributo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getId_personaje() {
        return id_personaje;
    }

    public void setId_personaje(int id_personaje) {
        this.id_personaje = id_personaje;
    }

    @Override
    public String toString() {
        return "Atributos{" + "id_atributo=" + id_atributo + ", nombre=" + nombre + ", valor=" + valor + ", id_personaje=" + id_personaje + '}';
    }
    
    
}
