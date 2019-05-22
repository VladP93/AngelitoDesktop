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
public class Backup {
    Conexion cnx = new Conexion();
    
    public ResultSet getPersonas() {
        return cnx.getValores("SELECT * FROM persona");
    }
    
    public ResultSet getClientes() {
        return cnx.getValores("SELECT * FROM cliente");
    }
    
    public ResultSet getEmpleados() {
        return cnx.getValores("SELECT * FROM empleado");
    }
    
    public ResultSet getProveedores() {
        return cnx.getValores("SELECT * FROM proveedor");
    }
    
    public ResultSet getTiposDeUsuario() {
        return cnx.getValores("SELECT * FROM tipousuario");
    }
    
    public ResultSet getUsuarios() {
        return cnx.getValores("SELECT * FROM usuario");
    }
    
    public ResultSet getFacturas() {
        return cnx.getValores("SELECT * FROM factura");
    }
    
    public ResultSet getCompras() {
        return cnx.getValores("SELECT * FROM compra");
    }
    
    public ResultSet getCategorias() {
        return cnx.getValores("SELECT * FROM categoria");
    }
    
    public ResultSet getProductos() {
        return cnx.getValores("SELECT * FROM producto");
    }
    
    public ResultSet getDetallesFactura() {
        return cnx.getValores("SELECT * FROM detallefactura");
    }
    
    public ResultSet getLotes() {
        return cnx.getValores("SELECT * FROM lote");
    }
    
    public ResultSet getDetallesCompra() {
        return cnx.getValores("SELECT * FROM detallecompra");
    }
    
}
