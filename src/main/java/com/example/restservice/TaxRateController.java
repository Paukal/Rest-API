package com.example.restservice;

import java.sql.SQLException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaxRateController {
    
        private TaxRateDB txDB;
        
        public TaxRateController() throws SQLException{
            this.txDB = TaxRateDB.getInstance();
        }
        
        @PutMapping(path = "/createTax",
        consumes={MediaType.APPLICATION_JSON_VALUE, 
            MediaType.APPLICATION_FORM_URLENCODED_VALUE})
        @ResponseBody
        public String create(@RequestBody TaxRate rate) throws SQLException {
            
            if(!txDB.getTableCreated())
            {
                txDB.createTable();
                txDB.setTableCreated(true);
            }
            
            return txDB.add(new TaxRate(rate.getPropertyType(), rate.getAmount()));
        }        
        
        @GetMapping("/readTax")
	public String read(@RequestParam(value = "property_type") 
                String property_type) throws SQLException {
            
            if(!txDB.getTableCreated())
            {
                txDB.createTable();
                txDB.setTableCreated(true);
            }
            return txDB.get(property_type);
	}
        
        @PostMapping(path = "/updateTax",
        consumes={MediaType.APPLICATION_JSON_VALUE, 
            MediaType.APPLICATION_FORM_URLENCODED_VALUE})
        @ResponseBody
        public String update(@RequestBody TaxRate rate) throws SQLException {
            
            if(!txDB.getTableCreated())
            {
                return "Table not created. Use create path to create table.";
            }
            
            return txDB.update(rate.getPropertyType(), rate.getAmount());
        } 
       
        @DeleteMapping(path = "/deleteTax")
        public String delete(@RequestParam String property_type) throws SQLException {
            
            if(!txDB.getTableCreated())
            {
                return "Table not created. Use create path to create table.";
            }
            return txDB.delete(property_type);
        } 
}
