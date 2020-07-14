package com.example.restservice;

import java.sql.SQLException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuildingRecController {
    
        public static BuildingRecDB brDB;
        
        public BuildingRecController() throws SQLException{
            this.brDB = BuildingRecDB.getInstance();
        }
        
        @PutMapping(path = "/create", 
        consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
        @ResponseBody
        public String create(@RequestBody BuildingRecord br) throws SQLException { 
            
            if(!brDB.getTableCreated())
            {
                brDB.createTable();
                brDB.setTableCreated(true);
            }
            
            return brDB.add(new BuildingRecord(br.getAddress(),br.getOwner(),
                    br.getSize(),br.getMarketValue(),br.getPropertyType()));
        } 
        
        @GetMapping("/read")
	public String read(@RequestParam(value = "address") String address) throws SQLException {
            
            if(!brDB.getTableCreated())
            {
                brDB.createTable();
                brDB.setTableCreated(true);
            }
            return brDB.get(address);
	}
        
        @PostMapping(path = "/update",
        consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
        @ResponseBody
        public String update(@RequestBody BuildingRecord br) throws SQLException {
            
            if(!brDB.getTableCreated())
            {
                return "Table not created. Use create path to create table.";
            }
            
            return brDB.update(br.getAddress(),br.getOwner(),br.getSize(),
                    br.getMarketValue(),br.getPropertyType());
        } 
       
        @DeleteMapping(path = "/delete")
        public String delete(@RequestParam String address) throws SQLException {
            
            if(!brDB.getTableCreated())
            {
                return "Table not created. Use create path to create table.";
            }
            return brDB.delete(address);
        } 
        
        @GetMapping("/calculate")
	public String calculate(@RequestParam(value = "owner") String owner) throws SQLException {
            
            if(!brDB.getTableCreated())
            {
                return "Table not created. Use create path to create table.";
            }
            return brDB.calculateTax(owner);
	}
}
