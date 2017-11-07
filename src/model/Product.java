package model;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {

	private SimpleStringProperty item = new SimpleStringProperty("");
	private SimpleIntegerProperty quantity = new SimpleIntegerProperty();
	private SimpleFloatProperty unitcost = new SimpleFloatProperty();
	
	
	public Product(String item,int quantity){
		this.item = new SimpleStringProperty(item);
		this.quantity = new SimpleIntegerProperty(quantity);
	}
	
	public Product(String item,int quantity, float unitcost){
		this.unitcost = new SimpleFloatProperty(unitcost);
		this.item = new SimpleStringProperty(item);
		this.quantity = new SimpleIntegerProperty(quantity);
	}

	public String getItem() {
		return item.get();
	}

	public void setItem(SimpleStringProperty item) {
		this.item = item;
	}

	public Integer getQuantity() {
		return quantity.get();
	}

	public void setQuantity(SimpleIntegerProperty quantity) {
		this.quantity = quantity;
	}

	public Float getUnitcost() {
		return unitcost.get();
	}

	public void setUnitcost(SimpleFloatProperty unitcost) {
		this.unitcost = unitcost;
	}
	
}
