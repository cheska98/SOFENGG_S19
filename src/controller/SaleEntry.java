package controller;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
 
public class SaleEntry {
	
    public SimpleStringProperty itemName = new SimpleStringProperty("<Name>"); 
    public SimpleFloatProperty price = new SimpleFloatProperty();
    public SimpleStringProperty ucost= new SimpleStringProperty();
	public SimpleIntegerProperty qty = new SimpleIntegerProperty();
	
    public String getItemName() {
        return itemName.get();
    }
 
    public Float getPrice() {
        return price.get();
    }
 
    public Integer getQty() {
        return qty.get();
    } 
    
    public String getUcost() {
		return ucost.get();
	}

	public void setUcost(SimpleStringProperty string) {
		this.ucost = string;
	}

}