/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.restservice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Paul
 */
import org.springframework.stereotype.Service;

@Service
public class BuildingRecDB {
    
    //private final DB db;
    private final DB db;
    private boolean tableCreated;
    private TaxRateDB txDB;
    
    public BuildingRecDB() throws SQLException {
        db = DB.getInstance();
        tableCreated = false;
    }
    
    private static BuildingRecDB instance = null;

    public static BuildingRecDB getInstance() throws SQLException {
       if(instance == null) {
          instance = new BuildingRecDB();
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
        db.execute("create table buildingRecord(address varchar(100) primary key,"
                + " owner varchar(100), "
                        + "size int, market_value int, "
                        + "property_type varchar(100))");
    }
    
    public String add(BuildingRecord rec) throws SQLException {
        ResultSet rs = db.executeQuery("select * from buildingRecord values "
                + "where address='" + rec.getAddress() + "'");
        if(!rs.next()) {
            if(!"".equals(rec.getAddress()) && !"".equals(rec.getOwner()) && 
                    rec.getSize()>0 && 
                    rec.getMarketValue()>0 && !"".equals(rec.getPropertyType()))
            {
                db.execute("insert into buildingRecord values('" + rec.getAddress() + "'"
                            + ", '" + rec.getOwner() + "'"
                            + ", " + rec.getSize() 
                            + ", " + rec.getMarketValue()
                            + ", '" + rec.getPropertyType() + "'"
                            + ")");

                return "200 OK";
            }
            else 
                return "One or more values are incorrect(empty string/negative "
                        + "value/zero)";
        }
        
        return "Building record with this address already exists.";
    }
    
    public String get(String address) throws SQLException {
        BuildingRecord br = new BuildingRecord(address, "", -1, -1, "");
        ResultSet rs = db.executeQuery("select * from buildingRecord values "
                + "where address='" + address +"'");
        if(rs.next()) {
                br.setOwner(rs.getString("owner"));
                br.setSize(rs.getInt("size"));
                br.setMarketValue(rs.getInt("market_value"));
                br.setPropertyType(rs.getString("property_type"));
                
                return br.toString();
        }
        
        return "Building record does not exist";
    }
    
    public String update(@RequestParam String address,
            @RequestParam String owner, @RequestParam int size,
            @RequestParam int market_value, @RequestParam String property_type) 
            throws SQLException {
        
        BuildingRecord br = new BuildingRecord(address, "", -1, -1, "");
        ResultSet rs = db.executeQuery("select * from buildingRecord values "
                + "where address='" + address + "'");
        if(rs.next()) {
            br.setOwner(rs.getString("owner"));
            br.setSize(rs.getInt("size"));
            br.setMarketValue(rs.getInt("market_value"));
            br.setPropertyType(rs.getString("property_type"));
            
            if(!owner.equals(br.getOwner())) {
                if(!"".equals(owner))
                    db.execute("update buildingRecord set owner='" + owner + "' "
                            + "where address='" + address + "'");
                else
                    return "owner value is empty!";
            }
            if(size != br.getSize()) {
                if(size>0)
                    db.execute("update buildingRecord set size=" + size + ""
                            + " where address='" + address + "'");
                else
                    return "size value can't be zero or less than zero!";
            }
            if(market_value != br.getMarketValue()) {
                if(market_value>0)
                    db.execute("update buildingRecord set market_value=" + 
                            market_value + " where address='" + address + "'");
                else
                    return "market_value value can't be zero or less than zero!";
            }
            if(!property_type.equals(br.getPropertyType())) {
                if(!"".equals(property_type))
                    db.execute("update buildingRecord set property_type='" + 
                            property_type + "' where address='" + address + "'");
                else
                    return "property_type value is empty!";
            }
            
            return "200 OK";
        }
        
        return "This building record does not exist in the DB.";
    }
    
    public String delete(String address) throws SQLException {
        ResultSet rs = db.executeQuery("select * from buildingRecord values "
                + "where address='" + address + "'");
        if(rs.next()) {
            db.execute("delete from buildingRecord where address='" + address + "'");
            
            return "200 OK";
        }
        
        return "Building record does not exist";
    }
    
    public String calculateTax(String owner) throws SQLException {

        txDB = TaxRateDB.getInstance();
        if(!txDB.getTableCreated()) {
            return "TaxRate table is not created.";
        }
        else {
            int result = 0;
            ResultSet rs = db.executeQuery("select * from taxRate values where "
                    + "property_type='default'");
            if(rs.next()) {
                rs = db.executeQuery("select * from buildingRecord values where "
                        + "owner='" + owner + "'");
                List<BuildingRecord> brList = new ArrayList<>();
                while (rs.next()) { 
                        BuildingRecord br = new BuildingRecord("", "", -1, -1, "");
                        br.setMarketValue(rs.getInt("market_value"));
                        br.setPropertyType(rs.getString("property_type"));
                        brList.add(br);
                }
                
                if(brList.isEmpty()) {
                    return "The specified owner does not exist.";
                }

                for (BuildingRecord br : brList) {
                    rs = db.executeQuery("select * from taxRate values where "
                            + "property_type='" + br.getPropertyType() + "'");

                    if(rs.isBeforeFirst()){
                        rs.next();
                        int txRate = rs.getInt("amount");
                        int mrkValue = br.getMarketValue();
                        result+= mrkValue*txRate;
                    }
                    else {
                        rs = db.executeQuery("select * from taxRate values where "
                                + "property_type='default'");
                        rs.next();
                        int txRate = rs.getInt("amount");
                        int mrkValue = br.getMarketValue();
                        result+= mrkValue*txRate;

                    }
                }

                return "" + result;

            } 
            else {
                return "The default property tax rate value is not set!";
            }
        }
    }
}
