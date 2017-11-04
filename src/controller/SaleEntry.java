package controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
 
public class SaleEntry {
	
    public SimpleStringProperty itemName = new SimpleStringProperty("<Name>"); 
    public SimpleStringProperty price = new SimpleStringProperty();
    public SimpleIntegerProperty qty = new SimpleIntegerProperty();
    public int invoiceId;
 
    public String getItemName() {
        return itemName.get();
    }
 
    public String getPrice() {
        return price.get();
    }
 
    public Integer getQty() {
        return qty.get();
    }
}