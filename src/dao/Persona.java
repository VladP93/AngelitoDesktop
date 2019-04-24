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
    public void editarCategoria(int idpersona, String nombre, String apellido, String dui,  String nit, String direccion, String telefono,  String email,  String afp, String isss, int nat) {
        cnx.UID("UPDATE persona set per_nombre='"+nombre+"',per_apellido='"+apellido+"', per_dui='"+dui+"', per_nit='"+nit+"',per_direccion='"+direccion+"', per_telefono='"+telefono+"',per_email='"+email+"', per_nup='"+afp+"', per_isss='"+isss+"', per_natural='"+nat+"' WHERE cat_idCategoria="+idpersona);
    }
    public ResultSet cantidadCategoria() {
        return cnx.getValores("SELECT COUNT(*) FROM persona");
    }
    public ResultSet personas() {
        return cnx.getValores("SELECT * FROM persona");
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
