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
import modelo.Atributos;
import modelo.Inventarios;

/**
 *
 * @author ruben.banber
 */
public class AtributosDAO {
    public List<Atributos> listarTodosLosAtributos() throws Exception {
        List<Atributos> atributos = new ArrayList<>();
        String query = "SELECT * FROM atributos";

        try (
                Connection connection = ConexionBD.conectarBD(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resulSet = statement.executeQuery();) {
            while (resulSet.next()) {
                Atributos atributo = new Atributos(
                        resulSet.getInt("id_atributo"),
                        resulSet.getString("nombre"),
                        resulSet.getInt("valor"),
                        resulSet.getInt("id_personaje")
                );
                atributos.add(atributo);
            }
            return atributos;
        } catch (Exception e) {
            throw new Exception("Error al listar los atributos " + e.getMessage(), e);
        }
    }
    
    public void insertarInventario(Atributos atributos) throws SQLException {
        String query = "INSERT INTO atributos (nombre, valor, id_personaje) values (?,?,?)";
        try {
            Connection connection = ConexionBD.conectarBD();
            PreparedStatement statement = connection.prepareStatement(query);
            {

                statement.setString(1, atributos.getNombre());
                statement.setInt(2, atributos.getValor());
                statement.setInt(3, atributos.getId_personaje());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void updateAtributos(Atributos atributos) {
        String query = "UPDATE atributos SET nombre = ?, valor = ?, id_personaje = ? WHERE id_atributo = ?";

        try {
            Connection connection = ConexionBD.conectarBD();
            PreparedStatement statement = connection.prepareStatement(query);
            {
                statement.setString(1, atributos.getNombre());
                statement.setInt(2, atributos.getValor());
                statement.setInt(3, atributos.getId_personaje());
                statement.setInt(4, atributos.getId_atributo());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void deleteAtributos(int id) {
        String query = "DELETE FROM atributos WHERE id_atributo = ?";

        try (
                Connection connection = ConexionBD.conectarBD(); // Conexión a la base de datos
                 PreparedStatement statement = connection.prepareStatement(query);) {
            // Asignar valores a los parámetros de la consulta
            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
