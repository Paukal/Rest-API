/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.restservice;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.h2.jdbcx.JdbcDataSource;

/**
 *
 * @author Paul
 */
//Singleton DB class
public class DB {
    
    private final JdbcDataSource ds;
    private final Connection conn;
    private final Statement stmt;
            
    private DB() throws SQLException {
        ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:mem:");
        ds.setUser("sa");
        ds.setPassword("sa");
        conn = ds.getConnection(); 
        stmt = conn.createStatement();
    }
    
   private static DB instance = null;

   public static DB getInstance() throws SQLException {
      if(instance == null) {
         instance = new DB();
      }
      return instance;
   }
    
    public boolean execute(String query) throws SQLException {
        return stmt.execute(query);
    }
    
    public ResultSet executeQuery(String query) throws SQLException {
        return stmt.executeQuery(query);
    }
    
    public Statement getStatement() {
        return stmt;
    }
    
    public Connection getConnection() {
        return conn;
    }
}
