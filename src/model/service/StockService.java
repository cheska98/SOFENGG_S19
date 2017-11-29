package model.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Database;
import model.entity.Stock;

public class StockService {
	
	public static Stock getStock(int id, String type) {
		try {
			Connection c = Database.getConnection();
			
			String query = "SELECT *"
					+ " FROM " + type
					+ " WHERE itemid = " + id;

			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			Stock stock = new Stock();
			
			if (rs.next())
			{
				int qty = rs.getInt(Database.STOCK_QTY);
				
				stock.setId(id);;
				stock.setQuantity(qty);
				
				st.close();
				
				return stock;
			} else {
				return null;
			}
			
		} catch (Exception e) {
			System.err.println("Error");
			System.err.println(e.getMessage());
			
			return null;
		}
	}
	
	public static ArrayList<Stock> getStockList(String type) {
		try {
			Connection c = Database.getConnection();

			String query = "SELECT *"
					+ " FROM " + type;

			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(query);

			ArrayList<Stock> stocks = new ArrayList<Stock>();
			
			while (rs.next())
			{
				Stock s = new Stock();
				int id = rs.getInt(Database.STOCK_ID);
				int qty = rs.getInt(Database.STOCK_QTY);
				
				s.setId(id);
				s.setQuantity(qty);
				
				stocks.add(s);
			}
			
			st.close();
			
			return stocks;
			
		} catch (Exception e) {
			System.err.println("Error");
			System.err.println(e.getMessage());
			
			return null;
		}
	}
	
	public static Stock incrementStock(int id, int qty, String type) {
		try {
			Connection c = Database.getConnection();
			
			String query = "SELECT *"
					+ " FROM " + type
					+ " WHERE itemid = " + id;

			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(query);

			Stock stock = new Stock();
			stock.setId(id);
			
			if (rs.next())
			{
				query = "UPDATE " + Database.DATABASE + "."
						+ type + " SET "
						+ Database.STOCK_QTY + " = " + (qty + rs.getInt(Database.STOCK_QTY))
						+ " WHERE " + Database.STOCK_ID + " = " + id;
			} else {
				query = "INSERT INTO " + type
						+ " (" + Database.STOCK_ID
						+ ", " + Database.STOCK_QTY 
						+ ") VALUES (" + id
						+ ", " + qty + ")";
			}

			st = c.createStatement();
			if(st.executeUpdate(query) > 0) {
				stock.setQuantity(qty + rs.getInt(Database.STOCK_QTY));
				st.close();
				return stock;
			} else {
				st.close();
				return null;
			}
		} catch (Exception e) {
			System.err.println("Error");
			System.err.println(e.getMessage());
			
			return null;
		}
	}
	
	public static Stock decrementStock(int id, int qty, String type) {
		try {
			Connection c = Database.getConnection();
			
			String query = "SELECT *"
					+ " FROM " + type
					+ " WHERE itemid = " + id;

			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			if (rs.next() && rs.getInt(Database.STOCK_QTY) >= qty)
			{
				query = "UPDATE " + Database.DATABASE + "."
						+ type + " SET "
						+ Database.STOCK_QTY + " = " + (rs.getInt(Database.STOCK_QTY) - qty)
						+ " WHERE " + Database.STOCK_ID + " = " + id;
				
				st = c.createStatement();
				
				Stock stock = new Stock();
				stock.setId(id);
				
				if(st.executeUpdate(query) > 0) {
					stock.setQuantity(rs.getInt(Database.STOCK_QTY) - qty);
					
					st.close();
					return stock;
				} else {
					st.close();
					return null;
				}
			} else {
				st.close();
				return null;
			}
			
		} catch (Exception e) {
			System.err.println("Error");
			System.err.println(e.getMessage());
			
			return null;
		}
	}
//	
//	//for debugging
//	public static void main(String[] args) {
////		System.out.println(incrementStock(new Stock(1, 0), 5, "inventory"));
////		System.out.println(getStockList("inventory"));
////		decrementStock(2, 5, "inventory");
//		incrementStock(3, 5, "inventory");
//	}

}
