package model.entries;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RefundReplaceEntry {
	
	public SimpleStringProperty itemName = new SimpleStringProperty("");
	public SimpleIntegerProperty qty = new SimpleIntegerProperty();
	public SimpleIntegerProperty id = new SimpleIntegerProperty();
	public SimpleStringProperty date = new SimpleStringProperty("");
	
	public RefundReplaceEntry(SimpleStringProperty itemName, SimpleIntegerProperty qty, SimpleIntegerProperty id,
			SimpleStringProperty date) {
		super();
		this.itemName = itemName;
		this.qty = qty;
		this.id = id;
		this.date = date;
	}
	
	public RefundReplaceEntry() {}

	public SimpleStringProperty getItemName() {
		return itemName;
	}

	public void setItemName(SimpleStringProperty itemName) {
		this.itemName = itemName;
	}

	public SimpleIntegerProperty getQty() {
		return qty;
	}

	public void setQty(SimpleIntegerProperty qty) {
		this.qty = qty;
	}

	public SimpleIntegerProperty getId() {
		return id;
	}

	public void setId(SimpleIntegerProperty id) {
		this.id = id;
	}

	public SimpleStringProperty getDate() {
		return date;
	}

	public void setDate(SimpleStringProperty date) {
		this.date = date;
	}
	
	

}
