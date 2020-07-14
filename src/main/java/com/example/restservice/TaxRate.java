package com.example.restservice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"property_type",
        "amount"
})
public class TaxRate {

        @JsonProperty("property_type")
	private String property_type;
        @JsonProperty("amount")
        private int amount;
        
        public TaxRate() {
	    super();
	}
        
	public TaxRate(String property_type, int amount) {
		this.property_type = property_type;
                this.amount = amount;
	}
    
	public String getPropertyType() {
		return property_type;
	}
        
        public int getAmount() {
                return amount;
        }
        
        public void setAmount(int amount) {
                this.amount = amount;
        }
        
        @Override
        public String toString() { 
            return "Property type: '" + this.property_type + "', Amount: '"
                    + this.amount + "'";
        } 
} 