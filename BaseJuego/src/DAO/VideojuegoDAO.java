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
import modelo.ExcepcionesJuego;
import modelo.Videojuegos;

/**
 *
 * @author ruben.banber
 */
public class VideojuegoDAO {

    public List<Videojuegos> listarTodosLosJuegos() throws ExcepcionesJuego {
        List<Videojuegos> juegos = new ArrayList<>();
        String query = "SELECT * FROM videojuegos";

        try (
                Connection connection = ConexionBD.conectarBD(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resulSet = statement.executeQuery();) {
            while (resulSet.next()) {
                Videojuegos juego = new Videojuegos(
                        resulSet.getInt("id_videojuego"),
                        resulSet.getString("nombre"),
                        resulSet.getString("desarrollador"),
                        resulSet.getDate("fecha_lanzamiento"),
                        resulSet.getString("genero"),
                        resulSet.getString("plataforma")
                );
                juegos.add(juego);
            }

        } catch (SQLException ex) {
            ExcepcionesJuego e = new ExcepcionesJuego();
            e.setCodigoError(ex.getErrorCode());
            e.setMensajeAdmin(ex.getMessage());
            e.setSentenciaSQL(query);
            switch (ex.getErrorCode()) {
                case 1064:
                    e.setMensajeUsuario("ERROR: La consulta no es valida");
                case 1054:
                    e.setMensajeUsuario("ERROR: Columna desconocida");
                case 1142:
                    e.setMensajeUsuario("ERROR: Permiso denegado para realizar esta consulta");
                case 1143:
                    e.setMensajeUsuario("ERROR: Permiso denegado para acceder a la columna requerida");
            }
            throw e;
        }
        return juegos;
    }

    public void insertarVideojuego(Videojuegos videojuego) throws SQLException, ExcepcionesJuego {
        String query = "INSERT INTO Videojuegos (nombre, desarrollador, fecha_lanzamiento, genero, plataforma) values (?,?,?,?,?)";
        try {
            Connection connection = ConexionBD.conectarBD();
            PreparedStatement statement = connection.prepareStatement(query);
            {

                statement.setString(1, videojuego.getNombre().isEmpty() ? null : videojuego.getNombre());
                statement.setString(2, videojuego.getDesarrollador().isEmpty() ? null : videojuego.getDesarrollador());
                if (videojuego.getFecha_lanzamiento() == null) {
                    statement.setNull(3, java.sql.Types.DATE);  // Inserta NULL si la fecha es null
                } else {
                    statement.setDate(3, new java.sql.Date(videojuego.getFecha_lanzamiento().getTime()));  // Convierte a java.sql.Date y lo inserta
                }

                statement.setString(4, videojuego.getGenero().isEmpty() ? null : videojuego.getGenero());
                statement.setString(5, videojuego.getPlataforma().isEmpty() ? null : videojuego.getPlataforma());

                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            ExcepcionesJuego e = new ExcepcionesJuego();
            e.setCodigoError(ex.getErrorCode());
            e.setMensajeAdmin(ex.getMessage());
            e.setSentenciaSQL(query);
            switch (ex.getErrorCode()) {
                case 1400:
                    e.setMensajeUsuario("ERROR: Todos los campos son obligatorios");
                    break;
                case 1364:
                    e.setMensajeUsuario("ERROR: La fecha introducida no es correcta o esta vacia");
                case 1064:
                    e.setMensajeUsuario("ERROR: La consulta no es valida");
                    break;
                case 1054:
                    e.setMensajeUsuario("ERROR: Columna desconocida");
                    break;
                case 1062:
                    e.setMensajeUsuario("ERROR: El id esta duplicado, introduzca uno nuevo");
                    break;
                case 1452:
                    e.setMensajeUsuario("ERROR: Falta un dato relacionado en otra tabla. Verifique la referencia.");
                    break;
                case 1048:
                    e.setMensajeUsuario("ERROR: El valor introducido no puede ser nulo");
                    break;
                case 1:
                    e.setMensajeUsuario("ERROR: El formato de la fecha deber ser yyyy-MM-dd o la fecha esta vacia");
                default:
                    e.setMensajeUsuario("Error desconocido: " + e.getMessage());
            }
            throw e;
        }
    }

    public void updateVideojuego(Videojuegos videojuego) throws ExcepcionesJuego {
        String query = "UPDATE Videojuegos SET nombre = ?, desarrollador = ?, fecha_lanzamiento = ?, genero = ?, plataforma = ? WHERE id_videojuego = ?";

        try {
            Connection connection = ConexionBD.conectarBD();
            PreparedStatement statement = connection.prepareStatement(query);
            {
                statement.setString(1, videojuego.getNombre().isEmpty() ? null : videojuego.getNombre());
                statement.setString(2, videojuego.getDesarrollador().isEmpty() ? null : videojuego.getDesarrollador());
                statement.setDate(3, (videojuego.getFecha_lanzamiento() == null) ? null : new java.sql.Date(videojuego.getFecha_lanzamiento().getTime()));
                statement.setString(4, videojuego.getGenero().isEmpty() ? null : videojuego.getGenero());
                statement.setString(5, videojuego.getPlataforma().isEmpty() ? null : videojuego.getPlataforma());
                statement.setObject(6, (videojuego.getId_videojuego() == 0) ? null : videojuego.getId_videojuego());

                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            ExcepcionesJuego e = new ExcepcionesJuego();
            e.setCodigoError(ex.getErrorCode());
            e.setMensajeAdmin(ex.getMessage());
            e.setSentenciaSQL(query);
            switch (ex.getErrorCode()) {
                case 1400:
                    e.setMensajeUsuario("ERROR: Todos los campos son obligatorios");
                    break;
                case 1064:
                    e.setMensajeUsuario("ERROR: La consulta no es valida");
                    break;
                case 1054:
                    e.setMensajeUsuario("ERROR: Columna desconocida");
                    break;
                case 1048:
                    e.setMensajeUsuario("ERROR: El valor introducido no puede ser nulo");
                    break;
                default:
                    e.setMensajeUsuario("Error desconocido: " + e.getMessage());
            }
            throw e;
        }
    }

    public void deleteVideojuego(int id) throws ExcepcionesJuego {
        String query = "DELETE FROM videojuegos WHERE id_videojuego = ?";

        try (
                Connection connection = ConexionBD.conectarBD(); // Conexión a la base de datos
                 PreparedStatement statement = connection.prepareStatement(query);) {
            // Asignar valores a los parámetros de la consulta
            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException ex) {
            ExcepcionesJuego e = new ExcepcionesJuego();
            e.setCodigoError(ex.getErrorCode());
            e.setMensajeAdmin(ex.getMessage());
            e.setSentenciaSQL(query);
            switch (ex.getErrorCode()) {
                case 1400:
                    e.setMensajeUsuario("ERROR: Todos los campos son obligatorios");
                    break;
                case 1064:
                    e.setMensajeUsuario("ERROR: La consulta no es valida");
                    break;
                case 1054:
                    e.setMensajeUsuario("ERROR: Columna desconocida");
                    break;
                case 1048:
                    e.setMensajeUsuario("ERROR: El valor introducido no puede ser nulo");
                    break;
                default:
                    e.setMensajeUsuario("Error desconocido: " + e.getMessage());
            }
            throw e;
        }
    }
}
