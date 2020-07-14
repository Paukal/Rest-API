/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.restservice;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Paul
 */
import org.springframework.stereotype.Service;

@Service
public class TaxRateDB {
    
    private final DB db;
    private boolean tableCreated;
    
    private TaxRateDB() throws SQLException {
        db = DB.getInstance();
        tableCreated = false;
    }
    
    private static TaxRateDB instance = null;

    public static TaxRateDB getInstance() throws SQLException {
       if(instance == null) {
          instance = new TaxRateDB();
       }
       return instance;
    }
    
    public void setTableCreated(boolean value) {
        tableCreated = value;
    }
    
    public boolean getTableCreated() {
        return tableCreated;
    }
    
    public void createTable() throws SQLException {
        db.execute("create table taxRate(property_type varchar(100) primary key,"
                        + "amount int)");
    }
    
    public String add(TaxRate rate) throws SQLException {
        ResultSet rs = db.executeQuery("select * from taxRate values where"
                + " property_type='" + rate.getPropertyType() + "'");
        if(!rs.next()) {
            db.execute("insert into taxRate values('" + rate.getPropertyType() + "'"
                + "," + rate.getAmount() + ")");
            
            return "200 OK";
        }       
        
        return "Property type already exists.";
    }
    
    public String get(String property_type) throws SQLException {
        TaxRate rate = new TaxRate(property_type, -1);
        ResultSet rs = db.executeQuery("select * from taxRate values where "
                + "property_type='" + property_type + "'");
        if(rs.next()) {
                rate.setAmount(rs.getInt("amount"));
                return rate.toString();
        }
        
        return "Property type does not exist";
    }
    
    public String update(@RequestParam String property_type, 
            @RequestParam int amount) throws SQLException {
        
        TaxRate rate = new TaxRate(property_type, -1);
        ResultSet rs = db.executeQuery("select * from taxRate values where "
                + "property_type='" + property_type + "'");
        if(rs.next()) {
            rate.setAmount(rs.getInt("amount"));
            
            if(amount != rate.getAmount()) {
                db.execute("update taxRate set amount='" + amount + "' where "
                        + "property_type='" + property_type + "'");
                return "200 OK";
            }
            else {
                return "Nothing has been changed.";
            }
        }
        
       return "This property type does not exist in the DB.";
    }
    
    public String delete(String property_type) throws SQLException {
        ResultSet rs = db.executeQuery("select * from taxRate values where "
                + "property_type='" + property_type + "'");
        if(rs.next()) {
            db.execute("delete from taxRate where property_type='" + 
                    property_type + "'");
            
            return "200 OK";
        }
        
        return "Property type does not exist";
    }
}
