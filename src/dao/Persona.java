/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;

/**
 *
 * @author DELL
 */
public class Persona {
    Conexion cnx = new Conexion();
        
    public void agregarPersona(String dui, String nit, String afp,  String isss,String nombre, String apellido, String direccion, String telefono,  String email,   int nat){
        cnx.UID("INSERT INTO persona (per_dui, per_nit,per_nup,per_isss,per_nombre, per_apellido, per_direccion,per_telefono,per_email,per_natural) VALUES('"+dui+"','"+nit+"','"+afp+"','"+isss+"','"+nombre+"','"+apellido+"','"+direccion+"','"+telefono+"','"+email+"','"+nat+"' )");
    }
    public void eliminarCategoria(int idCategoria) {
        cnx.UID("DELETE FROM Categoria WHERE cat_idCategoria="+idCategoria);
    }
    public void editarPersona(int idpersona, String dui, String nit, String afp,  String isss,String nombre, String apellido, String direccion, String telefono,  String email) {
        cnx.UID("UPDATE persona set per_nombre='"+nombre+"',per_apellido='"+apellido+"', per_dui='"+dui+"', per_nit='"+nit+"',per_direccion='"+direccion+"', per_telefono='"+telefono+"',per_email='"+email+"', per_nup='"+afp+"', per_isss='"+isss+"' WHERE per_idPersona="+idpersona);
    }
    public ResultSet cantidadCategoria() {
        return cnx.getValores("SELECT COUNT(*) FROM persona");
    }
    public ResultSet personas() {
        return cnx.getValores("SELECT * FROM persona");
    }
    
    public ResultSet personasEntidad(){
        return cnx.getValores("SELECT per_idPersona, per_nombre, per_apellido, per_direccion, per_telefono,\n" +
            "    IF(per_natural,'Natural','Jurídica') AS 'Tipo',\n" +
            "    IF(per_idPersona = cli_idPersona, 'SI', 'NO') AS 'cliente',\n" +
            "    IF(per_idPersona = prov_idPersona, 'SI', 'NO') AS 'proveedor',\n" +
            "    IF(per_idPersona = usu_idPersona, 'SI', 'NO') AS 'usuario',\n" +
            "    IF(per_idPersona = usu_idPersona, tus_tipoUsuario, NULL) AS 'Rol'\n" +
            "FROM persona\n" +
            "LEFT JOIN usuario ON usuario.usu_idPersona=persona.per_idPersona\n" +
            "LEFT JOIN cliente ON cliente.cli_idPersona=persona.per_idPersona\n" +
            "LEFT JOIN proveedor ON proveedor.prov_idPersona=persona.per_idPersona\n" +
            "LEFT JOIN tipoUsuario ON usuario.usu_idTipoUsuario=tipoUsuario.tus_idTipoUsuario");
    }
    
     public ResultSet ClienteProve() {
        return cnx.getValores(" SELECT per_idPersona, per_nombre, per_apellido, per_dui, per_nit FROM persona");
    }
    public ResultSet filtrar(String filtro) {
        return cnx.getValores("SELECT*FROM persona WHERE per_dui LIKE '"+filtro+"%'");
    }
    public ResultSet filtrarCliente(String filtro) {
        return cnx.getValores("SELECT per_idPersona, per_nombre, per_apellido, per_dui, per_nit FROM persona WHERE per_dui LIKE '"+filtro+"%'");
    }
     public void agregarCliente(int idPersona){
         cnx.UID("INSERT INTO cliente (cli_idPersona)VALUES('"+idPersona+"' )");
     }
     public void agregarProveedor(int idPersona){
         cnx.UID("INSERT INTO proveedor (prov_idPersona)VALUES('"+idPersona+"' )") ;
     }

    
}
