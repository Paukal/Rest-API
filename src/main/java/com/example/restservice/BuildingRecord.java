package com.example.restservice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"address",
        "owner",
	"size",
        "market_value",
	"property_type"
})
public class BuildingRecord {

        @JsonProperty("address")
	private String address;
        @JsonProperty("owner")
        private String owner;
        @JsonProperty("size")
	private int size;
        @JsonProperty("market_value")
        private int market_value;
        @JsonProperty("property_type")
	private String property_type;
        
        public BuildingRecord() {
	    super();
	}
        
	public BuildingRecord(String address, String owner, int size,
        int market_value, String property_type) {
		this.address = address;
                this.owner = owner;
		this.size = size;
                this.market_value = market_value;
		this.property_type = property_type;
	}

	public String getAddress() {
		return address;
	}
        
        public String getOwner() {
		return owner;
	}

	public int getSize() {
		return size;
	}
        
        public int getMarketValue() {
		return market_value;
	}

	public String getPropertyType() {
		return property_type;
	}
        
        public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setSize(int size) {
		this.size = size;
	}
        
        public void setMarketValue(int market_value) {
		this.market_value = market_value;
	}

	public void setPropertyType(String property_type) {
		this.property_type = property_type;
	}  
        
        @Override
        public String toString() { 
            return "Address: '" + this.address + "', Owner: '" + this.owner + 
                    "', Size: '" + this.size + "'"
                    + "', Market value: '" + this.market_value + "'" + "', "
                    + "Property type: '" + this.property_type + "'";
        }  
} 