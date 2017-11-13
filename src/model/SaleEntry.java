package model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
 
public class SaleEntry {
	
	public SimpleStringProperty itemName = new SimpleStringProperty("<Name>"); 
	public SimpleFloatProperty price = new SimpleFloatProperty();
	public SimpleStringProperty ucost= new SimpleStringProperty();
	public SimpleIntegerProperty qty = new SimpleIntegerProperty();
	public SimpleBooleanProperty delete = new SimpleBooleanProperty();
	
    public SimpleBooleanProperty getDelete() {
		return delete;
	}

	public void setDelete(SimpleBooleanProperty delete) {
		this.delete = delete;
	}

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
	
	public void setQty(SimpleIntegerProperty qty) {
		this.qty = qty;
	}

	public void setItemName(SimpleStringProperty itemName) {
		this.itemName = itemName;
	}

	public void setPrice(SimpleFloatProperty price) {
		this.price = price;
	}
	
	

}