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
        cnx.UID("INSERT INTO usuario VALUES(null,"+idPersona+","+idTipoUsuario+",'"+usu+"',MD5('"+pass+"'))");
    }
    
    public void editarUsuario(int idUsuario, int idTipoUsuario, String usu, String pass){
        cnx.UID("UPDATE usuario SET usu_idTipoUsuario="+idTipoUsuario+", usu_alias='"+usu+"', usu_contrasenia=MD5('"+pass+"') WHERE usu_idUsuario="+idUsuario);
    }
    
    public ResultSet usuarios() {
        return cnx.getValores("SELECT * FROM usuario INNER JOIN tipoUsuario ON usu_idTipoUsuario=tus_idTipoUsuario");
    }
    
    public ResultSet usuarioById(int idUsuario){
        return cnx.getValores("SELECT usu_alias, usu_contrasenia, tus_tipousuario "
                + "FROM usuario INNER JOIN tipousuario ON "
                + "usuario.usu_idTipoUsuario=tipousuario.tus_idTipoUsuario "
                + "WHERE usu_idUsuario="+idUsuario);
    }
    
    public ResultSet usuariosAsignados(){
        return cnx.getValores("SELECT emp_idPersona, usu_idUsuario, per_nombre, per_apellido, "
                + "usu_alias, usu_idTipoUsuario, tus_tipoUsuario "
                + "FROM persona INNER JOIN empleado ON persona.per_idPersona=empleado.emp_idPersona "
                + "LEFT JOIN usuario ON empleado.emp_idPersona=usuario.usu_idPersona "
                + "LEFT JOIN tipousuario ON usuario.usu_idTipoUsuario=tipousuario.tus_idTipoUsuario");
    }
    
    public ResultSet obtenerRoles(){
        return cnx.getValores("SELECT tus_tipousuario FROM tipousuario");
    }
    
    public ResultSet obtenerIdRol(String rol){
        return cnx.getValores("SELECT tus_idTipoUsuario FROM tipousuario WHERE tus_tipousuario='"+rol+"'");
    }
    
    public ResultSet usuarios(String usu, String pass){
        return cnx.getValores("SELECT * FROM usuario "
                + "INNER JOIN tipoUsuario ON usu_idTipoUsuario=tus_idTipoUsuario "
                + "INNER JOIN persona ON persona.per_idPersona=usuario.usu_idPersona "
                + "WHERE usu_alias='"+usu+"' AND usu_contrasenia=MD5('"+pass+"')");
    }
    
    public ResultSet comprobarPass(int idUsuario, String pass){
        return cnx.getValores("SELECT 1 FROM usuario WHERE usu_idUsuario="+idUsuario+" AND usu_contrasenia=MD5('"+pass+"');");
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
    
    public void eliminarUsuario(int idUsuario){
        cnx.UID("DELETE FROM usuario WHERE usu_idUsuario="+idUsuario);
    }
}
