/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;

/**
 *
 * @author vladi
 */
public class Usuario {
    Conexion cnx = new Conexion();
    
    public void agregarUsuario(int idPersona, int idTipoUsuario, String usu, String pass){
        cnx.UID("INSERT INTO usuario VALUES((SELECT COUNT(*)+1),"+idPersona+","+idTipoUsuario+",'"+usu+"',MD5('"+pass+"'))");
    }
    
    public void editarUsuario(int idUsuario, int idTipoUsuario, String usu, String pass){
        cnx.UID("UPDATE usuario SET usu_idTipoUsuario="+idTipoUsuario+", usu_alias='"+usu+"', usu_contrasenia=MD5('"+pass+"') WHERE usu_idUsuario="+idUsuario);
    }
    
    public ResultSet usuarios() {
        return cnx.getValores("SELECT * FROM usuario INNER JOIN tipoUsuario ON usu_idTipoUsuario=tus_idTipoUsuario");
    }
    
    public ResultSet usuarios(String usu, String pass){
        return cnx.getValores("SELECT * FROM usuario "
                + "INNER JOIN tipoUsuario ON usu_idTipoUsuario=tus_idTipoUsuario "
                + "INNER JOIN persona ON persona.per_idPersona=usuario.usu_idPersona "
                + "WHERE usu_alias='"+usu+"' AND usu_contrasenia=MD5('"+pass+"')");
    }
    
    public ResultSet usuarios(String usu){
        return cnx.getValores("SELECT * FROM usuario "
                + "INNER JOIN tipoUsuario ON usu_idTipoUsuario=tus_idTipoUsuario "
                + "INNER JOIN persona ON persona.per_idPersona=usuario.usu_idPersona "
                + "WHERE usu_alias='"+usu+"'");
    }
    
    public void cambiarPass(int idUsuario, String pass){
        cnx.UID("UPDATE usuario SET usu_contrasenia=MD5('"+pass+"') WHERE usu_idUsuario="+idUsuario);
    }
    
    public ResultSet cantidadUsuarios(){
        return cnx.getValores("SELECT COUNT(*) FROM usuario");
    }
}
