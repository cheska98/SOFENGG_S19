package model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {

	private SimpleStringProperty item = new SimpleStringProperty("");
	private SimpleStringProperty restock = new SimpleStringProperty("");
	private SimpleStringProperty update = new SimpleStringProperty("");
	private SimpleIntegerProperty quantity = new SimpleIntegerProperty();
	private SimpleFloatProperty price = new SimpleFloatProperty();
	private SimpleBooleanProperty checkbox = new SimpleBooleanProperty();
	
	
	public Product(String update,String restock,String item,int quantity, float unitcost){
		this.update = new SimpleStringProperty(update);
		this.restock = new SimpleStringProperty(restock);
		this.price = new SimpleFloatProperty(unitcost);
		this.item = new SimpleStringProperty(item);
		this.quantity = new SimpleIntegerProperty(quantity);
	}
	
	public String getDateUpdate() {
		return update.get();
	}

	public void setDateUpdate(SimpleStringProperty dateupdate) {
		this.update = dateupdate;
	}
	
	public String getDateRestock() {
		return restock.get();
	}

	public void setDateRestock(SimpleStringProperty daterestock) {
		this.restock = daterestock;
	}
	
	public Boolean getCheckBox() {
		return checkbox.get();
	}

	public void setCheckBox(SimpleBooleanProperty cb) {
		this.checkbox = cb;
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

	public Float getPrice() {
		return price.get();
	}

	public void setPrice(SimpleFloatProperty unitcost) {
		this.price = unitcost;
	}
	
}
