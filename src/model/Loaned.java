package model;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Loaned {
	
	private SimpleStringProperty item = new SimpleStringProperty("");
	private SimpleFloatProperty unitcost = new SimpleFloatProperty();
	private SimpleStringProperty  transdate = new SimpleStringProperty("");
	
	
	public Loaned(String item, Float unitcost, String transdate){
		this.unitcost = new SimpleFloatProperty(unitcost);
		this.item = new SimpleStringProperty(item);
		this.transdate = new SimpleStringProperty(transdate);
	}
	
	public SimpleStringProperty getItem() {
		return item;
	}
	public void setItem(SimpleStringProperty item) {
		this.item = item;
	}
	public SimpleFloatProperty getUnitcost() {
		return unitcost;
	}
	public void setUnitcost(SimpleFloatProperty unitcost) {
		this.unitcost = unitcost;
	}
	public SimpleStringProperty getTransdate() {
		return transdate;
	}
	public void setTransdate(SimpleStringProperty transdate) {
		this.transdate = transdate;
	}
	
	
}
