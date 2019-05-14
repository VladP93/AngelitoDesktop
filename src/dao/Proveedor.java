/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;

/**
 *
 * @author Arnold
 */
public class Proveedor {
    Conexion cnx = new Conexion();
    
    public ResultSet obtenerProveedores(){
        return cnx.getValores("select * from persona inner join proveedor on proveedor.prov_idPersona=persona.per_idPersona;");
    }
    
    public ResultSet filtrarProveedores(String filtro){
        return cnx.getValores("select * from persona inner join proveedor on proveedor.prov_idPersona=persona.per_idPersona where per_nombre like '%"+filtro+"%' OR per_apellido like '%"+filtro+"%';");
    }
}
