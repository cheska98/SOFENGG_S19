package model;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CustomerEntry {

	private SimpleStringProperty contactno = new SimpleStringProperty("");
	private SimpleStringProperty lasttrans = new SimpleStringProperty("");
	private SimpleStringProperty name = new SimpleStringProperty("");
	private SimpleFloatProperty balance = new SimpleFloatProperty();
	private SimpleIntegerProperty transID = new SimpleIntegerProperty();
	
	public CustomerEntry(String contactno, String lasttrans,
					String name, Float balance, int transID)
	{
		this.contactno =  new SimpleStringProperty(contactno);
		this.lasttrans =  new SimpleStringProperty(lasttrans);
		this.name = new SimpleStringProperty(name);
		this.balance = new SimpleFloatProperty(balance);
		this.transID = new SimpleIntegerProperty(transID);
	}

	public String getContactno() {
		return contactno.get();
	}

	public void setContactno(SimpleStringProperty contactno) {
		this.contactno = contactno;
	}

	public String getLasttrans() {
		return lasttrans.get();
	}

	public void setLasttrans(SimpleStringProperty lasttrans) {
		this.lasttrans = lasttrans;
	}

	public String getName() {
		return name.get();
	}

	public void setName(SimpleStringProperty name) {
		this.name = name;
	}

	public Float getBalance() {
		return balance.get();
	}

	public void setBalance(SimpleFloatProperty balance) {
		this.balance = balance;
	}

	public Integer getTransID() {
		return transID.get();
	}

	public void setTransID(SimpleIntegerProperty transID) {
		this.transID = transID;
	}
	
	
}
