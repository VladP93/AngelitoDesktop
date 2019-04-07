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
public class Categoria {
    Conexion cnx = new Conexion();
        
    public void agregarCategoria(String categoria){
        cnx.UID("INSERT INTO Categoria (cat_categoria) VALUES('"+categoria+"')");
    }
    public void eliminarCategoria(int idCategoria) {
        cnx.UID("DELETE FROM Categoria WHERE cat_idCategoria="+idCategoria);
    }
    public void editarCategoria(int idCategoria, String categoria) {
        cnx.UID("UPDATE Categoria set cat_categoria='"+categoria+"' WHERE cat_idCategoria="+idCategoria);
    }
    public ResultSet cantidadCategoria() {
        return cnx.getValores("SELECT COUNT(*) FROM Categoria");
    }
    public ResultSet categorias() {
        return cnx.getValores("SELECT * FROM Categoria");
    }
    public ResultSet filtrar(String filtro) {
        return cnx.getValores("SELECT*FROM categoria WHERE cat_categoria LIKE '"+filtro+"%'");
    }
}
