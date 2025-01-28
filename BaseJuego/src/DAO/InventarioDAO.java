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
import modelo.Inventarios;
import modelo.Videojuegos;

/**
 *
 * @author ruben.banber
 */
public class InventarioDAO {

    public List<Inventarios> listarTodosLosInventarios() throws Exception {
        List<Inventarios> inventario = new ArrayList<>();
        String query = "SELECT * FROM inventarios";

        try (
                Connection connection = ConexionBD.conectarBD(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resulSet = statement.executeQuery();) {
            while (resulSet.next()) {
                Inventarios juego = new Inventarios(
                        resulSet.getInt("id_inventario"),
                        resulSet.getString("nombre"),
                        resulSet.getString("descripcion"),
                        resulSet.getInt("cantidad"),
                        resulSet.getInt("id_personaje")
                );
                inventario.add(juego);
            }
            return inventario;
        } catch (Exception e) {
            throw new Exception("Error al listar los inventarios " + e.getMessage(), e);
        }
    }

    public void insertarInventario(Inventarios inventario) throws SQLException {
        String query = "INSERT INTO Inventarios (nombre, descripcion, cantidad, id_personaje) values (?,?,?,?)";
        try {
            Connection connection = ConexionBD.conectarBD();
            PreparedStatement statement = connection.prepareStatement(query);
            {

                statement.setString(1, inventario.getNombre());
                statement.setString(2, inventario.getDescripcion());
                statement.setInt(3, inventario.getCantidad());
                statement.setInt(4, inventario.getId_personaje());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void updateInventario(Inventarios inventario) {
        String query = "UPDATE inventarios SET nombre = ?, descripcion = ?, cantidad = ?, id_personaje = ? WHERE id_inventario = ?";

        try {
            Connection connection = ConexionBD.conectarBD();
            PreparedStatement statement = connection.prepareStatement(query);
            {
                statement.setString(1, inventario.getNombre());
                statement.setString(2, inventario.getDescripcion());
                statement.setInt(3, inventario.getCantidad());
                statement.setInt(4, inventario.getId_personaje());
                statement.setInt(5, inventario.getId_invetario());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteInventario(int id) {
        String query = "DELETE FROM inventarios WHERE id_inventario = ?";

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
