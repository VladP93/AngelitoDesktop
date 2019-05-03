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
public class Factura {
    Conexion cnx = new Conexion();
    
    public void agregarFactura(int idPersona, int idUsuario){
        cnx.UID("INSERT INTO Factura VALUES(null,"+idPersona+","+idUsuario+", NOW())");
    }
    
    public void agregarDetalle(int idFactura, int idProducto, int cantidad){
        cnx.UID("INSERT INTO detallefactura VALUES(null,"+idFactura+","+idProducto+","+cantidad+")");
    }
    
    public ResultSet facturar(int idFactura, int idPersona, int idUsuario, String idProducto, int cantidad){
        return cnx.getValores("call proc_facturar("+idFactura+","+idPersona+","+idUsuario+",'"+idProducto+"',"+cantidad+")");
    }
    
    public ResultSet ultimaFactura(){
        return cnx.getValores("SELECT fac_idFactura FROM factura ORDER BY fac_idFactura DESC LIMIT 1");
    }
    
}
