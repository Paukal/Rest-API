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

@WebMvcTest(BuildingRecController.class)
@AutoConfigureMockMvc

@WebAppConfiguration
@ContextConfiguration(classes = MyWebConfig.class)
public class BuildingRecTests {

  @Autowired
  private MockMvc mockMvc;
    
    @Test
    public void putRequestTest() throws Exception {
        
            String address = "Chicago, Bronson st., 89";
            String owner="Edward Johnson";
            int size=25000;
            int market_value=100000;
            String property_type="house";
            
            BuildingRecord br = new BuildingRecord(address,owner,size,
                    market_value,property_type);
           
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(br);
            
            MockHttpServletRequestBuilder builder = 
                    MockMvcRequestBuilders.put("http://localhost:8080/create")
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
        
            String address = "Lithuania, Kaunas, 56";
            String owner="Edward Johnson";
            int size=25000;
            int market_value=100000;
            String property_type="house";
            
            BuildingRecord br = new BuildingRecord(address,owner,size,
                    market_value,property_type);
           
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(br);
            
            MockHttpServletRequestBuilder builder = 
                    MockMvcRequestBuilders.put("http://localhost:8080/create")
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .characterEncoding("UTF-8").content(json);
            
            this.mockMvc.perform(builder);
            
            br.setMarketValue(40);
            br.setOwner("Mr James");
            br.setPropertyType("loft");
            br.setSize(500);
            
            ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            json = ow.writeValueAsString(br);
            
            builder = MockMvcRequestBuilders.post("http://localhost:8080/update")
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
        
            String address = "Orlando, Sixton., 02";
            String owner="Edward Johnson";
            int size=25000;
            int market_value=100000;
            String property_type="house";
            
            BuildingRecord br = new BuildingRecord(address,owner,size,
                    market_value,property_type);
           
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(br);
            
            MockHttpServletRequestBuilder builder = 
                    MockMvcRequestBuilders.put("http://localhost:8080/create")
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .characterEncoding("UTF-8").content(json);
            
            this.mockMvc.perform(builder);
            
            this.mockMvc.perform(get("/read").param("address", address))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content()
                                                  .string(br.toString()))
                  .andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    public void putDeleteRequestTest() throws Exception {
        
            String address = "Miami, Jordan, 23";
            String owner="Edward Johnson";
            int size=25000;
            int market_value=100000;
            String property_type="house";
            
            BuildingRecord br = new BuildingRecord(address,owner,size,
                    market_value,property_type);
           
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(br);
            
            MockHttpServletRequestBuilder builder = 
                    MockMvcRequestBuilders.put("http://localhost:8080/create")
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .characterEncoding("UTF-8").content(json);
            
            this.mockMvc.perform(builder);
            
            this.mockMvc.perform(MockMvcRequestBuilders.delete("/delete")
                    .param("address", address))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content()
                                                  .string("200 OK"))
                  .andDo(MockMvcResultHandlers.print());
    } 
	
    
    @Test
    public void putTwoTimesRequestTest() throws Exception {
        
            String address = "Ilinouts, test 23";
            String owner="Edward Johnson";
            int size=25000;
            int market_value=100000;
            String property_type="house";
            
            BuildingRecord br = new BuildingRecord(address,owner,size,
                    market_value,property_type);
           
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(br);
            
            MockHttpServletRequestBuilder builder = 
                    MockMvcRequestBuilders.put("http://localhost:8080/create")
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .characterEncoding("UTF-8").content(json);
            
            this.mockMvc.perform(builder);
            
            this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status()
                                                  .isOk())
                  .andExpect(MockMvcResultMatchers.content()
                                                  .string("Building record with"
                                                          + " this address already exists."))
                  .andDo(MockMvcResultHandlers.print());
    } 
    
    @Test
    public void postDBNonExistRequestTest() throws Exception {
        
        String address = "existant address";
            String owner="Edward Johnson";
            int size=25000;
            int market_value=100000;
            String property_type="house";
            
            BuildingRecord br = new BuildingRecord(address,owner,size,
                    market_value,property_type);
           
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(br);
            
            MockHttpServletRequestBuilder builder = 
                    MockMvcRequestBuilders.put("http://localhost:8080/create")
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .characterEncoding("UTF-8").content(json);
            
            this.mockMvc.perform(builder);
        
            address = "nonExistant address";
            owner="Edward Johnson";
            size=25000;
            market_value=100000;
            property_type="house";
            
            br = new BuildingRecord(address,owner,size,
                    market_value,property_type);
           
            ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            json = ow.writeValueAsString(br);
   
            builder = MockMvcRequestBuilders
                    .post("http://localhost:8080/update")
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .characterEncoding("UTF-8").content(json);
            
            this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status()
                                                  .isOk())
                  .andExpect(MockMvcResultMatchers.content()
                                                  .string("This building record "
                                                          + "does not exist in the DB."))
                  .andDo(MockMvcResultHandlers.print());
    }   
}