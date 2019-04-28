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
public class Producto {
    Conexion cnx = new Conexion();
    
    public ResultSet contarProductos(String codigo){
        return cnx.getValores("SELECT COUNT(*) FROM Producto Where prod_idProducto='"+codigo+"'");
    }
    
    public ResultSet mostrarProductos(){
        return cnx.getValores("SELECT prod_nombre, prod_precio,prod_precioMayoreo,prod_iva,"
                + "prod_cantidadMayoreo,prod_cantidad,prod_cantidadMinAlerta,cat_categoria,"
                + "prod_descripcion FROM producto INNER JOIN categoria "
                + "ON prod_idCategoria = cat_idCategoria");
    }
    
    public ResultSet productos(){
        return cnx.getValores("select * from producto");
    }
    
    public ResultSet productos(String filtro){
        return cnx.getValores("select * from producto where prod_nombre like '"+filtro+"%'");
    }
    
    public ResultSet productosPorCodigo(String id){
        return cnx.getValores("Select * from producto where prod_idproducto='"+id+"'");
    }
    
    public void agregarProducto(String codigo, String nombre, String descripcion, 
            Double precioDetalle, Double precioMayoreo, Double iva, int cantidadMayoreo,
            int CantidadMinimoAlerta, int Categoria){
        
        cnx.UID("INSERT INTO Producto (prod_idProducto,prod_nombre,prod_descripcion,"
                + "prod_precio,prod_precioMayoreo,prod_iva,prod_cantidadMayoreo,prod_cantidad,"
                + "prod_cantidadMinAlerta,prod_idCategoria) VALUES('"+codigo+"','"+nombre+"','"
                +descripcion+"',"+precioDetalle+","+precioMayoreo+","+iva+","+cantidadMayoreo+",0,"+
                CantidadMinimoAlerta+","+Categoria+")");
                
    }
    
    public void eliminarProducto(String idProducto) {
        cnx.UID("DELETE FROM Producto WHERE prod_idProducto="+idProducto);
    }
    
    
    public ResultSet obtenerIdCategoria(String categoria){
        return cnx.getValores("SELECT cat_idCategoria FROM Categoria WHERE cat_Categoria='"+categoria+"'");
    }
    
    public ResultSet obtenerCategorias(){
        return cnx.getValores("SELECT cat_categoria FROM categoria");
    }
    
    public ResultSet obtenerCodigos(String codigo){
        return cnx.getValores("SELECT COUNT(*) prod_idProducto FROM Producto WHERE prod_idProducto='"+codigo+"'");
    }
}
