/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import bd.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Personajes;
import modelo.Videojuegos;

/**
 *
 * @author ruben.banber
 */
public class PersonajeDAO {
    public List<Personajes> listarTodosLosPersonajes() throws Exception {
        List<Personajes> personajes = new ArrayList<>();
        String query = "SELECT * FROM personajes";

        try (
                Connection connection = ConexionBD.conectarBD(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resulSet = statement.executeQuery();) {
            while (resulSet.next()) {
                Personajes personaje = new Personajes(
                        resulSet.getInt("id_personaje"),
                        resulSet.getString("nombre"),
                        resulSet.getString("descripcion"),
                        resulSet.getInt("id_videojuego")
                );
                personajes.add(personaje);
            }
            return personajes;
        } catch (Exception e) {
            throw new Exception("Error al listar los personajes " + e.getMessage(), e);
        }
    }
    
    public void insertarPersonajes(Personajes personaje) throws SQLException {
        String query = "INSERT INTO personajes (nombre, descripcion, id_videojuego) values (?,?,?)";
        try {
            Connection connection = ConexionBD.conectarBD();
            PreparedStatement statement = connection.prepareStatement(query);
            {
                statement.setString(1, personaje.getNombre());
                statement.setString(2, personaje.getDescripcion());
                statement.setInt(3, personaje.getId_videojuego());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            //System.err.println(e.getMessage());
        }
     }
    
    public void updatePersonajes(Personajes personaje){
        String query = "UPDATE personajes SET nombre = ?, descripcion = ?, id_videojuego = ? WHERE id_personaje = ?";
        
        try{
            Connection connection = ConexionBD.conectarBD();
            PreparedStatement statement = connection.prepareStatement(query);
            {
                statement.setString(1, personaje.getNombre());
                statement.setString(2, personaje.getDescripcion());
                statement.setInt(3, personaje.getId_videojuego());
                statement.setInt(4, personaje.getId_personaje());
                
                statement.executeUpdate();
            }
        } catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void deletePersonajes(int id){
        String query = "DELETE FROM personajes WHERE id_personaje = ?";

    try (
        Connection connection = ConexionBD.conectarBD(); // Conexión a la base de datos
        PreparedStatement statement = connection.prepareStatement(query);
    ) {
        // Asignar valores a los parámetros de la consulta
        statement.setInt(1, id);
        
        statement.executeUpdate();
        
    } catch (SQLException e){
        System.err.println(e.getMessage());
    } 
}
}