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
public class Compra {
        Conexion cnx = new Conexion();
    
        
    public ResultSet comprar(String codLote, String idProducto, String FechaFab, String FechaVen,
            int cantidad, double precio, int idCompra, int idPersona, int idProveedor){
        return cnx.getValores("call proc_comprar('"+codLote+"','"+idProducto+"','"+FechaFab+"','"+FechaVen+"',"+cantidad+","+precio+","+idCompra+","+idPersona+","+idProveedor+")");
    }
    
    public ResultSet ultimaCompra(){
        return cnx.getValores("SELECT dco_idCompra FROM detallecompra ORDER BY dco_idCompra DESC LIMIT 1");
    }
    
}
