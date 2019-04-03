/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author vladi
 */
public class Conexion {
    private String url = "jdbc:mysql://localhost:3306/tiendaangelito?autoReconnect=true&useSSL=false";
    private String login = "root"; 
    private String password = "12345";
    private Connection cnx = null;
    private Statement sttm = null;
    private ResultSet rst = null;

    
        
    public void UID(String sql) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection(url, login, password);
            sttm = cnx.createStatement();
            sttm.executeUpdate(sql); //statement
        } catch (ClassNotFoundException c) {
            JOptionPane.showMessageDialog(null, "ERROR: " + c.getMessage());
            System.exit(1); //salir de aplicación
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR DE mysql: " + e.getMessage());
            System.exit(1);
        }
    }

    
    public ResultSet getValores(String sql) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection(url, login, password);
            sttm = cnx.createStatement();
            rst = sttm.executeQuery(sql);  //resultset
        } catch (ClassNotFoundException c) {
            JOptionPane.showMessageDialog(null, "Error aqui conexion");
            JOptionPane.showMessageDialog(null, "ERROR: " + c.getMessage());
            System.exit(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            System.exit(1);
        } finally {
            return rst;
        }
    }
}
