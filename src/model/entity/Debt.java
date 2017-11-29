package model.entity;

import java.sql.Date;

public class Debt {

	public static final String TABLE_NAME = "debts";
	public static final String DEBT_TRANSID = "TransactionID";
	public static final String DEBT_CUSTOMERID = "CustomerID";
	public static final String DEBT_PAID = "Paid";
	public static final String DEBT_DATE = "Date";

	private int transactionID;
	private int customerID;
	private float paid;
	private Date date;

	/**
	 * @return the transactionID
	 */
	public int getTransactionID() {
		return transactionID;
	}

	/**
	 * @return the paid
	 */
	public float getPaid() {
		return paid;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param transactionID the transactionID to set
	 */
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	/**
	 * @param d the paid to set
	 */
	public void setPaid(double d) {
		this.paid = (float) d;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the customerID
	 */
	public int getCustomerID() {
		return customerID;
	}

	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
}
