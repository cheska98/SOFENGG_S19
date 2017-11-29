package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	public static final String CUSTOMER_TABLE = "customers";
	public static final String CUSTOMER_ID = "ID";
	public static final String CUSTOMER_NAME = "Name";
	public static final String CUSTOMER_COMPANY = "Company";
	public static final String CUSTOMER_CONTACT = "Contact";
	public static final String CUSTOMER_QUERY = "CREATE TABLE IF NOT EXISTS " + CUSTOMER_TABLE + "("
			+ CUSTOMER_ID + " INT(10) NOT NULL AUTO_INCREMENT,"
			+ CUSTOMER_NAME + " VARCHAR(45) NOT NULL,"
			+ CUSTOMER_COMPANY + " VARCHAR(45) NOT NULL,"
			+ CUSTOMER_CONTACT + " VARCHAR(45) NOT NULL,"
			+ "PRIMARY KEY(" + CUSTOMER_ID + "))";

	public static final String DEBT_TABLE = "debts";
	public static final String DEBT_TRANSID = "TransactionID";
	public static final String DEBT_CUSTOMERID = "CustomerID";
	public static final String DEBT_PAID = "Paid";
	public static final String DEBT_DATE = "Date";
	public static final String DEBT_QUERY = "CREATE TABLE IF NOT EXISTS " + DEBT_TABLE + "("
			+ DEBT_TRANSID + " INT(10) NOT NULL,"
			+ DEBT_CUSTOMERID + " INT(10) NOT NULL,"
			+ DEBT_PAID + " DECIMAL(12,2) NOT NULL,"
			+ DEBT_DATE + " DATETIME NOT NULL)";

	public static final String TRANSACTION_TABLE = "transactions";
	public static final String TRANSACTION_ID = "id";
	public static final String TRANSACTION_DATE = "date";
	public static final String TRANSACTION_TOTAL = "total";
	public static final String TRANSACTION_IS_PAID = "isPaid";
	public static final String TRANSACTION_QUERY = "CREATE TABLE IF NOT EXISTS " + TRANSACTION_TABLE + "("
			+ TRANSACTION_ID + " INT(10) NOT NULL AUTO_INCREMENT,"
			+ TRANSACTION_DATE + " DATETIME NOT NULL,"
			+ TRANSACTION_TOTAL + " DECIMAL(12,2) NOT NULL,"
			+ TRANSACTION_IS_PAID + " INT(10) NOT NULL)";

	public static final String TRANSACTIONITEM_TABLE = "transaction_items";
	public static final String TRANSACTIONITEM_TRANSACTIONID = "transactionID";
	public static final String TRANSACTIONITEM_ITEMID = "itemID";
	public static final String TRANSACTIONITEM_UNITPRICE = "unitPrice";
	public static final String TRANSACTIONITEM_QUANTITY = "qty";
	public static final String TRANSACTIONITEM_ACTION = "action";
	public static final String TRANSACTIONITEM_QUERY = "CREATE TABLE IF NOT EXISTS " + TRANSACTIONITEM_TABLE + "("
			+ TRANSACTIONITEM_TRANSACTIONID + " INT(10) NOT NULL,"
			+ TRANSACTIONITEM_ITEMID + " INT(10) NOT NULL,"
			+ TRANSACTIONITEM_UNITPRICE + " DECIMAL(12,2) NOT NULL,"
			+ TRANSACTIONITEM_QUANTITY + " INT(10) NOT NULL,"
			+ TRANSACTIONITEM_ACTION + " VARCHAR(45) NOT NULL)";

	public static final String ITEM_TABLE = "item";
	public static final String ITEM_ID = "id";
	public static final String ITEM_DESC = "description";
	public static final String ITEM_UNITPRICE = "unitPrice";
	public static final String ITEM_QUERY = "CREATE TABLE IF NOT EXISTS " + ITEM_TABLE + "("
			+ ITEM_ID + " INT(10) NOT NULL,"
			+ ITEM_DESC + " VARCHAR(45) NOT NULL,"
			+ ITEM_UNITPRICE + " DECIMAL(10,2) NOT NULL,"
			+ "PRIMARY KEY(" + ITEM_ID + "))";

	public static final String STOCK_ID = "itemid";
	public static final String STOCK_QTY = "qty";

	public static final String INVENTORY_TABLE = "inventory";
	public static final String INVENTORY_QUERY = "CREATE TABLE IF NOT EXISTS " + INVENTORY_TABLE + "("
			+ STOCK_ID + " INT(10) NOT NULL,"
			+ STOCK_QTY + " INT(10) NOT NULL,"
			+ "PRIMARY KEY(" + STOCK_ID + "))";

	public static final String DISPLAY_TABLE = "display";
	public static final String DISPLAY_QUERY = "CREATE TABLE IF NOT EXISTS " + DISPLAY_TABLE + "("
			+ STOCK_ID + " INT(10) NOT NULL,"
			+ STOCK_QTY + " INT(10) NOT NULL,"
			+ "PRIMARY KEY(" + STOCK_ID + "))";

	public static final String[] TABLES = {CUSTOMER_TABLE, DEBT_TABLE, TRANSACTION_TABLE, TRANSACTIONITEM_TABLE, ITEM_TABLE, INVENTORY_TABLE, DISPLAY_TABLE};
	public static final String[] QUERIES = {CUSTOMER_QUERY, DEBT_QUERY, TRANSACTION_QUERY, TRANSACTIONITEM_QUERY, ITEM_QUERY, INVENTORY_QUERY, DISPLAY_QUERY};
	
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "FrenchFries";
	public static final String DATABASE = "project_captiran";

	public static Connection getConnection() {
		try {
			Class.forName(DRIVER_NAME);
			Connection c = DriverManager.getConnection(URL + "/" + DATABASE, USERNAME, PASSWORD);
			return c;
		} catch (Exception e) {
			e.getMessage();

			initializeSchema();
			initializeTables();

			try {
				Connection c = DriverManager.getConnection(URL + "/" + DATABASE, USERNAME, PASSWORD);
				return c;
			} catch (SQLException e1) {
				e1.printStackTrace();
				return null;
			}
		}
	}

	private static void initializeSchema() {
		try {
			Connection c = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			String query = "CREATE SCHEMA IF NOT EXISTS " + DATABASE;

			Statement st = c.createStatement();
			st.execute(query);
		} catch (SQLException e2) {
			System.err.println("Error: " + e2.getMessage());
		}
	}

	private static void initializeTables() {
		try {
			Connection c = DriverManager.getConnection(URL + "/" + DATABASE, USERNAME, PASSWORD);
			for (int i = 0; i < TABLES.length; i++) {
				Statement st = c.createStatement();
				st.execute(QUERIES[i]);
			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

//	//for debugging
//	public static void main(String[] args) {
//		getConnection();
//	}
}
