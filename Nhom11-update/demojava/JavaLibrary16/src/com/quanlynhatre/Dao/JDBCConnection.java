/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quanlynhatre.Dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class JDBCConnection {
//    public Connection con= null;
    public static Connection getJDBCConnection(){
         String url = "net.sourceforge.jtds.jdbc.Driver";
         //String dbUrl = "jdbc:jtds:sqlserver://DESKTOP-26IQ342:1433/QLNHATRE1;instance=SQLEXPRESS";
         //String dbUrl = "jdbc:jtds:sqlserver://DESKTOP-26IQ342\SQLEXPRESS:1433/QLNHATRE1";
//       String dbUrl = "jdbc:jtds:sqlserver://DESKTOP-8T8UIUI:1433/QLNHATRE1";
         String dbUrl = "jdbc:jtds:sqlserver://THANHTUAN123:1433/QLNHATRE1";
        try {
            Class.forName(url);
            return  DriverManager.getConnection(dbUrl);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
}
}

