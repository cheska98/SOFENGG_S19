package model.entries;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ReportEntry {
	
	public final SimpleStringProperty transID = new SimpleStringProperty("");
	public final SimpleStringProperty item = new SimpleStringProperty("");;
	public final SimpleIntegerProperty quantity = new SimpleIntegerProperty();;
	public final SimpleFloatProperty unitCost = new SimpleFloatProperty();;
	public final SimpleFloatProperty price = new SimpleFloatProperty();;
	
	
	public String getTransID() {
		return transID.get();
	}
	
	public String getItem() {
		return item.get();
	}
	
	public Integer getQuantity() {
		return quantity.get();
	}
	
	public Float getUnitCost() {
		return unitCost.get();
	}
	
	public Float getPrice() {
		return price.get();
	}
	
}
