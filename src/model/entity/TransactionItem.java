package model.entity;

public class TransactionItem {
	int transactionID;
	int itemID;
	float unitPrice;
	int quantity;
	String action;
	
	public TransactionItem(){
		
	}

	public TransactionItem(int transactionID, int itemID, float unitPrice, int quantity, String action){
		this.transactionID = transactionID;
		this.itemID = itemID;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.action = action;
	}
	
	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
