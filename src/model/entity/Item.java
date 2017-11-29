package model.entity;

import javafx.scene.Node;

public class Item {
	
	private int id;
	private String desc;
	private float unitPrice;

	public Item() {}

	public Item(String desc, float unitPrice) {
		this.desc = desc;
		this.unitPrice = unitPrice;
	}

	public Item(int id, String desc, float unitPrice) {
		this.id = id;
		this.desc = desc;
		this.unitPrice = unitPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}
}
