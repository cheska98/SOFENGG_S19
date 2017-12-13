package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RefundReplaceEntry {
	
	public SimpleStringProperty itemName = new SimpleStringProperty("");
	public SimpleIntegerProperty qty = new SimpleIntegerProperty();
	public SimpleIntegerProperty id = new SimpleIntegerProperty();
	public SimpleStringProperty date = new SimpleStringProperty("");
	
	public void setItemName(SimpleStringProperty itemName) {
		this.itemName = itemName;
	}
	
	public String getItemName() {
		
		return itemName.get();
	}
	
	public Integer getQty() {
		
		return qty.get();
	}
	
	public Integer getId() {
		
		return id.get();
	}
	
	public String getDate() {
		
		return date.get();
	}
	
	public void setQty(SimpleIntegerProperty qty) {
		
		this.qty = qty;
		
	}
	public void setId(SimpleIntegerProperty id) {
		
		this.id = id;
		
	}
	public void setDate(SimpleStringProperty date) {
		
		this.date = date;
		
	}
	
	

}
