/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author ruben.banber
 */
public class ExcepcionesJuego extends Exception{
    private String mensajeUsuario;
    private String mensajeAdmin;
    private Integer codigoError;
    private String sentenciaSQL;

    public ExcepcionesJuego() {
    }

    public ExcepcionesJuego(String mensajeUsuarion, String mensajeAdmin, Integer codigoError, String sentenciaSQL) {
        this.mensajeUsuario = mensajeUsuarion;
        this.mensajeAdmin = mensajeAdmin;
        this.codigoError = codigoError;
        this.sentenciaSQL = sentenciaSQL;
    }

    public String getMensajeUsuario() {
        return mensajeUsuario;
    }

    public void setMensajeUsuario(String mensajeUsuarion) {
        this.mensajeUsuario = mensajeUsuarion;
    }

    public String getMensajeAdmin() {
        return mensajeAdmin;
    }

    public void setMensajeAdmin(String mensajeAdmin) {
        this.mensajeAdmin = mensajeAdmin;
    }

    public Integer getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(Integer codigoError) {
        this.codigoError = codigoError;
    }

    public String getSentenciaSQL() {
        return sentenciaSQL;
    }

    public void setSentenciaSQL(String sentenciaSQL) {
        this.sentenciaSQL = sentenciaSQL;
    }

    @Override
    public String toString() {
        return mensajeUsuario;
    }
    
    
    
}
