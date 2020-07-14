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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaxRateController.class)
@AutoConfigureMockMvc

@WebAppConfiguration
@ContextConfiguration(classes = MyWebConfig.class)
public class TaxRateTests {

  @Autowired
  private MockMvc mockMvc;
    
    @Test
    public void putRequestTest() throws Exception {
        
            String property_type="loft";
            int amount = 200;
            
            TaxRate rate = new TaxRate(property_type, amount);
           
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(rate);
            
            MockHttpServletRequestBuilder builder = 
                    MockMvcRequestBuilders.put("http://localhost:8080/createTax")
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .characterEncoding("UTF-8").content(json);
            
            this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status()
                                                  .isOk())
                  .andExpect(MockMvcResultMatchers.content()
                                                  .string("200 OK"))
                  .andDo(MockMvcResultHandlers.print());
    } 
    
    @Test
    public void putPostRequestTest() throws Exception {
        
            String property_type="house";
            int amount = 200;
            
            TaxRate rate = new TaxRate(property_type, amount);
           
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(rate);
            
            MockHttpServletRequestBuilder builder = 
                    MockMvcRequestBuilders.put("http://localhost:8080/createTax")
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .characterEncoding("UTF-8").content(json);
            
            this.mockMvc.perform(builder);
            
            rate.setAmount(150);

            
            ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            json = ow.writeValueAsString(rate);
            
            builder = MockMvcRequestBuilders.post("http://localhost:8080/updateTax")
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .characterEncoding("UTF-8").content(json);
            
            this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status()
                                                  .isOk())
                  .andExpect(MockMvcResultMatchers.content()
                                                  .string("200 OK"))
                  .andDo(MockMvcResultHandlers.print());
    } 
    
    @Test
    public void putGetRequestTest() throws Exception {
        
            String property_type="tower";
            int amount = 200;
            
            TaxRate rate = new TaxRate(property_type, amount);
           
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(rate);
            
            MockHttpServletRequestBuilder builder = 
                    MockMvcRequestBuilders.put("http://localhost:8080/createTax")
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .characterEncoding("UTF-8").content(json);
            
            this.mockMvc.perform(builder);
            
            this.mockMvc.perform(get("/readTax").param("property_type", property_type))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content()
                                                  .string(rate.toString()))
                  .andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    public void putDeleteRequestTest() throws Exception {
        
            String property_type="apartment";
            int amount = 200;
            
            TaxRate rate = new TaxRate(property_type, amount);
           
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(rate);
            
            MockHttpServletRequestBuilder builder = 
                    MockMvcRequestBuilders.put("http://localhost:8080/createTax")
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .characterEncoding("UTF-8").content(json);
            
            this.mockMvc.perform(builder);
            
            this.mockMvc.perform(MockMvcRequestBuilders.delete("/deleteTax")
                    .param("property_type", property_type))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content()
                                                  .string("200 OK"))
                  .andDo(MockMvcResultHandlers.print());
    } 
	
    
    @Test
    public void putTwoTimesRequestTest() throws Exception {
        
            String property_type="flat";
            int amount = 200;
            
            TaxRate rate = new TaxRate(property_type, amount);
           
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(rate);
            
            MockHttpServletRequestBuilder builder = 
                    MockMvcRequestBuilders.put("http://localhost:8080/createTax")
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .characterEncoding("UTF-8").content(json);
            
            this.mockMvc.perform(builder);
            
            this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status()
                                                  .isOk())
                  .andExpect(MockMvcResultMatchers.content()
                                                  .string("Property type already exists."))
                  .andDo(MockMvcResultHandlers.print());
    } 
    
    @Test
    public void postDBNonExistRequestTest() throws Exception {
        
        String property_type="existant property";
            int amount = 200;
            
            TaxRate rate = new TaxRate(property_type, amount);
           
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(rate);
            
            MockHttpServletRequestBuilder builder = 
                    MockMvcRequestBuilders.put("http://localhost:8080/createTax")
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .characterEncoding("UTF-8").content(json);
            
            this.mockMvc.perform(builder);
        
            property_type="non existant property";
            amount = 200;
            
            rate = new TaxRate(property_type, amount);
           
            ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            json = ow.writeValueAsString(rate);
   
            builder = MockMvcRequestBuilders.post("http://localhost:8080/updateTax")
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .characterEncoding("UTF-8").content(json);
            
            this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status()
                                                  .isOk())
                  .andExpect(MockMvcResultMatchers.content()
                                                  .string("This property type "
                                                          + "does not exist in the DB."))
                  .andDo(MockMvcResultHandlers.print());
    } 
    
    
}