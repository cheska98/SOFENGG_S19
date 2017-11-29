package model.entries;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
 
public class SaleEntry {
	
	public SimpleStringProperty itemName = new SimpleStringProperty("<Name>"); 
	public SimpleFloatProperty price = new SimpleFloatProperty();
	public SimpleFloatProperty ucost= new SimpleFloatProperty();
	public SimpleIntegerProperty qty = new SimpleIntegerProperty();
	public SimpleBooleanProperty delete = new SimpleBooleanProperty();
	
    public SimpleBooleanProperty getDelete() {
		return delete;
	}

	public void setDelete(SimpleBooleanProperty delete) {
		this.delete = delete;
	}
	
	public boolean getSelected(){
	    return selectedProperty().get();
	}
	
	public SimpleBooleanProperty selectedProperty() {
	    return this.delete;
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
    
    public Float getUcost() {
		return ucost.get();
	}

	public void setUcost(SimpleFloatProperty f) {
		this.ucost = f;
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