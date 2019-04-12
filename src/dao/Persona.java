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
        
    public void agregarPersona(String nombre, String apellido, String dui,  String nit, String direccion, String telefono,  String email,  String afp, String isss, String nat){
        cnx.UID("INSERT INTO persona (per_nombre, per_apellido, per_dui, per_nit, per_direccion,per_telefono,per_email,per_nup,per_isss,per_natural) VALUES('"+nombre+"','"+apellido+"','"+dui+"','"+nit+"','"+direccion+"','"+telefono+"','"+email+"','"+afp+"','"+isss+"','"+nat+"' )");
    }
    public void eliminarCategoria(int idCategoria) {
        cnx.UID("DELETE FROM Categoria WHERE cat_idCategoria="+idCategoria);
    }
    public void editarCategoria(int idpersona, String nombre, String apellido, String dui,  String nit, String direccion, String telefono,  String email,  String afp, String isss, String nat) {
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
     public void agregarCliente(String idPersona){
         cnx.UID("INSERT INTO cliente (emp_idPersona)VALUES('"+idPersona+"'" );
     }
     public void agregarProveedor(String idPersona){
         cnx.UID("INSERT INTO proveedor (emp_idPersona)VALUES('"+idPersona+"'" );
     }
}
