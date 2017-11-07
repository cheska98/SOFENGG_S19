package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TopItemEntry {
	
	public final SimpleStringProperty item = new SimpleStringProperty("");;
	public final SimpleIntegerProperty quantity = new SimpleIntegerProperty();
	
	public String getItem() {
		return item.get();
	}
	
	public Integer getQuantity() {
		return quantity.get();
	};
	
}
