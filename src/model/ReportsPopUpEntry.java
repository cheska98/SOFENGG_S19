package model;

import javafx.beans.property.SimpleStringProperty;

public class ReportsPopUpEntry {
	
	public final SimpleStringProperty transID = new SimpleStringProperty("");;
	public final SimpleStringProperty date = new SimpleStringProperty("");;
	public final SimpleStringProperty time = new SimpleStringProperty("");;
	
	
	public String getTransID() {
		return transID.get();
	}
	public String getDate() {
		return date.get();
	}
	public String getTime() {
		return time.get();
	}

	
	
}
