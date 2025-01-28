/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ruben.banber
 */
public class ConexionBD {
    public static final String USER = "root";
    public static final String PWD = "";
    public static final String URL = "jdbc:MySql://localhost/juegoporturnos";
    
    //Metodo para crear nueva conexion
    public static Connection conectarBD(){
        try{
            Connection conexion = DriverManager.getConnection(URL, USER, PWD);
            System.out.println("Conexion establecida");
            return conexion;
        } catch(SQLException e){
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            throw new RuntimeException("No se pudo conecta a la base de datos" , e);
        }
    }
    
    public static void cerrarConexion(Connection conexion){
        if(conexion != null){
            try{
                if(!conexion.isClosed()){
                    conexion.close();
                    System.out.println("Conexion cerrada");
                }
            } catch (SQLException e){
                System.err.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
    }
}
