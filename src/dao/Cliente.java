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
public class Cliente {
    Conexion cnx = new Conexion();
    
    public ResultSet clientes(){
        return cnx.getValores("select * from persona inner join cliente on cliente.cli_idPersona=persona.per_idPersona;");
    }
    
    public ResultSet clientes(String filtro){
        return cnx.getValores("select * from persona inner join cliente on cliente.cli_idPersona=persona.per_idPersona where per_nombre like '"+filtro+"%' OR per_apellido like '"+filtro+"%';");
    }
}
