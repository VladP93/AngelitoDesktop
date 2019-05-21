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
        
    public void agregarPersona(int idPersona, String dui, String nit, String afp,  String isss,String nombre, String apellido, String direccion, String telefono,  String email,   int nat){
        cnx.UID("INSERT INTO persona VALUES("+idPersona+", "+dui+", "+nit+", "+afp+", "+isss+", '"+nombre+"', "+apellido+", '"+direccion+"', "+telefono+", "+email+", "+nat+" )");
    }
    
    public void agregarCliente(int idPersona){
        cnx.UID("INSERT INTO cliente VALUES("+idPersona+")");
    }
    
    public void agregarEmpleado(int idPersona){
        cnx.UID("INSERT INTO empleado VALUES("+idPersona+")");
    }
    
    public void agregarProveedor(int idPersona){
        cnx.UID("INSERT INTO proveedor VALUES("+idPersona+")");
    }
    
    public void eliminarPersona(int idPersona) {
        cnx.UID("DELETE FROM persona WHERE per_idPersona="+idPersona);
    }
    
    public void eliminarCliente(int idPersona){
        cnx.UID("DELETE FROM cliente WHERE cli_idPersona="+idPersona);
    }
    
    public void eliminarEmpleado(int idPersona){
        cnx.UID("DELETE FROM empleado WHERE emp_idPersona="+idPersona);
    }
    
    public void eliminarProveedor(int idPersona){
        cnx.UID("DELETE FROM proveedor WHERE prov_idPersona="+idPersona);
    }
    
    public void editarPersona(int idpersona, String dui, String nit, String afp,  String isss,String nombre, String apellido, String direccion, String telefono,  String email) {
        cnx.UID("UPDATE persona set per_nombre='"+nombre+"',per_apellido="+apellido+", per_dui="+dui+", per_nit="+nit+",per_direccion='"+direccion+"', per_telefono="+telefono+",per_email="+email+", per_nup="+afp+", per_isss="+isss+" WHERE per_idPersona="+idpersona);
    }
    public ResultSet cantidadPersonas() {
        return cnx.getValores("SELECT COUNT(*) FROM persona");
    }
    public ResultSet personas() {
        return cnx.getValores("SELECT * FROM persona");
    }
    
    public ResultSet personasEntidad(){
        return cnx.getValores("SELECT per_idPersona, per_nombre, per_apellido, per_direccion, per_telefono, "
                + "IF(per_natural,'Natural','Jurídica') AS 'Tipo', "
                + "IF(per_idPersona = cli_idPersona, 'SI', 'NO') AS 'cliente', "
                + "IF(per_idPersona = prov_idPersona, 'SI', 'NO') AS 'proveedor', "
                + "IF(per_idPersona = emp_idPersona, 'SI', 'NO') AS 'empleado', "
                + "IF(per_idPersona = usu_idPersona, 'SI', 'NO') AS 'usuario', "
                + "IF(per_idPersona = usu_idPersona, tus_tipoUsuario, NULL) AS 'Rol' "
                + "FROM persona "
                + "LEFT JOIN usuario "
                + "ON usuario.usu_idPersona=persona.per_idPersona "
                + "LEFT JOIN cliente "
                + "ON cliente.cli_idPersona=persona.per_idPersona "
                + "LEFT JOIN proveedor "
                + "ON proveedor.prov_idPersona=persona.per_idPersona "
                + "LEFT JOIN empleado "
                + "ON persona.per_idPersona=empleado.emp_idPersona "
                + "LEFT JOIN tipoUsuario "
                + "ON usuario.usu_idTipoUsuario=tipoUsuario.tus_idTipoUsuario");
    }
    
    public ResultSet personasEntidad(int idPersona){
        return cnx.getValores("SELECT per_dui, per_nit, per_nup, per_isss, "
                + "per_nombre, per_apellido, per_direccion, per_telefono, "
                + "per_email, IF(per_natural,'Natural','Jurídica') AS 'Tipo', "
                + "IF(per_idPersona = cli_idPersona, 'SI', 'NO') AS 'cliente', "
                + "IF(per_idPersona = prov_idPersona, 'SI', 'NO') AS 'proveedor', "
                + "IF(per_idPersona = emp_idPersona, 'SI', 'NO') AS 'empleado', "
                + "IF(per_idPersona = usu_idPersona, 'SI', 'NO') AS 'usuario', "
                + "IF(per_idPersona = usu_idPersona, tus_tipoUsuario, NULL) AS 'Rol' "
                + "FROM persona LEFT JOIN usuario "
                + "ON usuario.usu_idPersona=persona.per_idPersona "
                + "LEFT JOIN cliente "
                + "ON cliente.cli_idPersona=persona.per_idPersona "
                + "LEFT JOIN proveedor "
                + "ON proveedor.prov_idPersona=persona.per_idPersona "
                + "LEFT JOIN empleado "
                + "ON persona.per_idPersona=empleado.emp_idPersona "
                + "LEFT JOIN tipoUsuario "
                + "ON usuario.usu_idTipoUsuario=tipoUsuario.tus_idTipoUsuario "
                + "WHERE per_idPersona="+idPersona);
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
    
    public ResultSet obtenerSiCliente(int idPersona){
        return cnx.getValores("SELECT COUNT(*) FROM persona "
                + "INNER JOIN cliente ON per_idPersona = cli_idPersona "
                + "INNER JOIN factura ON fac_idPersona = cli_idPersona "
                + "WHERE per_idPersona = "+idPersona);
    }
    
    public ResultSet obtenerSiProveedor(int idPersona){
        return cnx.getValores("SELECT COUNT(*) FROM persona "
                + "INNER JOIN proveedor ON per_idPersona = prov_idPersona "
                + "INNER JOIN compra ON com_idPersona = prov_idPersona "
                + "WHERE per_idPersona = "+idPersona);
    }
    
    public ResultSet obtenerSiUsuarioComp(int idPersona){
        return cnx.getValores("SELECT COUNT(*) FROM persona "
                + "INNER JOIN empleado ON per_idPersona = emp_idPersona "
                + "INNER JOIN usuario ON emp_idPersona = usu_idPersona "
                + "INNER JOIN compra ON com_idUsuario = usu_idUsuario "
                + "WHERE per_idPersona = "+idPersona);
    }
    
    public ResultSet obtenerSiUsuarioFac(int idPersona){
        return cnx.getValores("SELECT COUNT(*) FROM persona "
                + "INNER JOIN empleado ON per_idPersona = emp_idPersona "
                + "INNER JOIN usuario ON emp_idPersona = usu_idPersona "
                + "INNER JOIN factura ON fac_idUsuario = usu_idUsuario "
                + "WHERE per_idPersona = "+idPersona);
    }
}
