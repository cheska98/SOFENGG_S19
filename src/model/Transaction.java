package model;
import java.util.Date;

public class Transaction {
	private int id;
	private Date date;
	private float total;
	private int isPaid;

	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public int getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(int isPaid) {
		this.isPaid = isPaid;
	}
	
	
}
