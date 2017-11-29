package model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Database;
import model.entity.Item;

public class ItemService {
	
//	public static Item getItem(String desc) {
//		try {
//			Connection c = Database.getConnection();
//
//			String query = "SELECT *"
//					+ " FROM " + Database.ITEM_TABLE
//					+ " WHERE description = '" + desc + "'";
//
//			Statement st = c.createStatement();
//			ResultSet rs = st.executeQuery(query);
//
//			Item item = new Item();
//			
//			if (rs.next())
//			{
//				int id = rs.getInt(Database.ITEM_ID);
//				float unitPrice = rs.getFloat(Database.ITEM_UNITPRICE);
//				
//				item.setId(id);
//				item.setDesc(desc);
//				item.setUnitPrice(unitPrice);
//				
//				st.close();
//				
//				return item;
//			} else {
//				return null;
//			}
//			
//		} catch (Exception e) {
//			System.err.println("Error");
//			System.err.println(e.getMessage());
//			
//			return null;
//		}
//	}
//	
//	public static ArrayList<Item> searchItems(String key) {
//		try {
//			Connection c = Database.getConnection();
//
//			String query = "SELECT *"
//					+ " FROM " + Database.ITEM_TABLE
//					+ " WHERE " + Database.ITEM_DESC + " LIKE '%" + key + "%'";
//			
//			System.out.println(query);
//
//			Statement st = c.createStatement();
//			ResultSet rs = st.executeQuery(query);
//
//			ArrayList<Item> items = new ArrayList<Item>();
//			
//			while (rs.next())
//			{
//				Item item = new Item();
//				int id = rs.getInt(Database.ITEM_ID);
//				String desc = rs.getString(Database.ITEM_DESC);
//				float unitPrice = rs.getFloat(Database.ITEM_UNITPRICE);
//				
//				item.setId(id);
//				item.setDesc(desc);
//				item.setUnitPrice(unitPrice);
//				
//				items.add(item);
//			}
//			
//			st.close();
//			
//			return items;
//			
//		} catch (Exception e) {
//			System.err.println("Error");
//			System.err.println(e.getMessage());
//			
//			return null;
//		}
//	}
	
	public static ArrayList<Item> getAllItemList() {
		try {
			Connection c = Database.getConnection();

			String query = "SELECT *"
					+ " FROM " + Database.ITEM_TABLE;
			
			System.out.println(query);

			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(query);

			ArrayList<Item> items = new ArrayList<Item>();
			
			while (rs.next())
			{
				Item item = new Item();
				int id = rs.getInt(Database.ITEM_ID);
				String desc = rs.getString(Database.ITEM_DESC);
				float unitPrice = rs.getFloat(Database.ITEM_UNITPRICE);
				
				item.setId(id);
				item.setDesc(desc);
				item.setUnitPrice(unitPrice);
				
				items.add(item);
			}
			
			st.close();
			
			return items;
			
		} catch (Exception e) {
			System.err.println("Error");
			System.err.println(e.getMessage());
			
			return null;
		}
	}
	
	public static int addItem(Item item) {
		try {
			int id = -1;
			
			Connection c = Database.getConnection();

			String query = "INSERT INTO " + Database.ITEM_TABLE
					+ " (description, unitPrice) VALUES ('"
					+ item.getDesc() + "', " 
					+ item.getUnitPrice() + ")";
			
			System.out.println(query);

			Statement st = c.createStatement();
			
			if(st.executeUpdate(query) > 0) {
				query = "SELECT LAST_INSERT_ID()";
				ResultSet rs = st.executeQuery(query);
				if(rs.next()){
					id = rs.getInt("LAST_INSERT_ID()");
				}
			}
			
			st.close();
			
			return id;
		} catch (Exception e) {
			System.err.println("Error");
			System.err.println(e.getMessage());
			
			return -1;
		}
	}
	
	public static boolean editItem(Item item) {
		try {
			boolean flag = false;
			
			Connection c = Database.getConnection();

			String query = "UPDATE " + Database.DATABASE + "."
					+ Database.ITEM_TABLE + " SET "
					+ "description = '" + item.getDesc() + "', " 
					+ "unitPrice = " + item.getUnitPrice()
					+ " WHERE id = " + item.getId();
			
			System.out.println(query);

			Statement st = c.createStatement();
			
			if(st.executeUpdate(query) > 0) {
				flag = true;
			}
			
			st.close();
			
			return flag;
		} catch (Exception e) {
			System.err.println("Error");
			System.err.println(e.getMessage());
			
			return false;
		}
	}
	
//	public static Item findItem(int itemID) throws SQLException {
//		Item item = new Item();
//		Connection connect = Database.getConnection();
//		String query = "SELECT * "
//				+ " FROM " + Database.ITEM_TABLE
//				+ " WHERE " + Database.ITEM_ID + "= " + itemID;
//
//		try {
//			PreparedStatement statement = connect.prepareStatement(query);
//
//			ResultSet rs = statement.executeQuery(query);
//
//			if(rs.next()){
//				
//				int id = rs.getInt(Database.ITEM_ID);
//				String desc = rs.getString(Database.ITEM_DESC);
//				float unitPrice = rs.getFloat(Database.ITEM_UNITPRICE);
//				
//				item.setId(id);
//				item.setDesc(desc);
//				item.setUnitPrice(unitPrice);
//			}
//			
//			rs.close();
//			statement.close();
//			connect.close();
//
//			System.out.println("[CONTACTS] SELECT SUCCESS!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("[CONTACTS] SELECT FAILED!");
//			return null;
//		}
//
//		return item;
//	}
	
	
	
	//for debugging
//	public static void main(String[] args) {
////		System.out.println(addItem(new Item("asfsgj", 10)));
////		System.out.println(editItem(new Item(1, "sample item", 10)));
////		System.out.println(searchItems("sample"));
//	}

}
