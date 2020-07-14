/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.restservice;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@AutoConfigureMockMvc

@WebAppConfiguration
@ContextConfiguration(classes = MyWebConfig.class)
public class CalculateTest {

  @Autowired
  private MockMvc mockMvc; 
    
    @Test
    public void calculateTest1() throws Exception {
        
            String property_type="default";
            int amount = 20;
            
            TaxRate rate = new TaxRate(property_type, amount);
           
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(rate);
            
            MockHttpServletRequestBuilder builder = 
                    MockMvcRequestBuilders.put("http://localhost:8080/createTax")
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .characterEncoding("UTF-8").content(json);
            
            this.mockMvc.perform(builder);
            
            
            String address = "Ilinout, jr, 1";
            String owner="James Jones";
            int size=25000;
            int market_value=100000;
            
            BuildingRecord br = new BuildingRecord(address,owner,size,
                    market_value,property_type);
           
            ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            json = ow.writeValueAsString(br);
            
            builder = 
                    MockMvcRequestBuilders.put("http://localhost:8080/create")
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .characterEncoding("UTF-8").content(json);
            
            this.mockMvc.perform(builder);
            
            

            
            this.mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                    .param("owner", owner))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content()
                                                  .string("2000000"))
                  .andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    public void calculateTest2() throws Exception {
        
            String property_type="default2";
            int amount = 158;
            
            TaxRate rate = new TaxRate(property_type, amount);
           
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(rate);
            
            MockHttpServletRequestBuilder builder = 
                    MockMvcRequestBuilders.put("http://localhost:8080/createTax")
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .characterEncoding("UTF-8").content(json);
            
            this.mockMvc.perform(builder);
            
            
            String address = "Paxon, St, l";
            String owner="Erix Brad";
            int size=2500;
            int market_value=10890;
            
            BuildingRecord br = new BuildingRecord(address,owner,size,
                    market_value,property_type);
           
            ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            json = ow.writeValueAsString(br);
            
            builder = 
                    MockMvcRequestBuilders.put("http://localhost:8080/create")
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .characterEncoding("UTF-8").content(json);
            
            this.mockMvc.perform(builder);
            
            
            address = "Rei, dk, 58";
            owner="Erix Brad";
            size=25000;
            market_value=10590;
            
            br = new BuildingRecord(address,owner,size,
                    market_value,property_type);
           
            ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            json = ow.writeValueAsString(br);
            
            builder = 
                    MockMvcRequestBuilders.put("http://localhost:8080/create")
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .characterEncoding("UTF-8").content(json);
            
            this.mockMvc.perform(builder);
            

            
            this.mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                    .param("owner", owner))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content()
                                                  .string("3393840"))
                  .andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    public void calculateTest3() throws Exception {
        
            String property_type="default3";
            int amount = 100;
            
            TaxRate rate = new TaxRate(property_type, amount);
           
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(rate);
            
            MockHttpServletRequestBuilder builder = 
                    MockMvcRequestBuilders.put("http://localhost:8080/createTax")
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .characterEncoding("UTF-8").content(json);
            
            this.mockMvc.perform(builder);
            
            
            String address = "Jungle street, 10";
            String owner="Jurg Lautrei";
            int size=25000;
            int market_value=10052;
            
            BuildingRecord br = new BuildingRecord(address,owner,size,
                    market_value,property_type);
           
            ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            json = ow.writeValueAsString(br);
            
            builder = 
                    MockMvcRequestBuilders.put("http://localhost:8080/create")
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .characterEncoding("UTF-8").content(json);
            
            this.mockMvc.perform(builder);
            
            

            
            this.mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                    .param("owner", owner))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content()
                                                  .string("1005200"))
                  .andDo(MockMvcResultHandlers.print());
    }
}