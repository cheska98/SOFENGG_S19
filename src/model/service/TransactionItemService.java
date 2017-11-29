package model.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Database;
import model.entity.Transaction;
import model.entity.TransactionItem;

public class TransactionItemService {	
	
	public static TransactionItem toTransItem(ResultSet rs) throws SQLException {
		TransactionItem transItem = new TransactionItem();

		transItem.setTransactionID(rs.getInt(Database.TRANSACTIONITEM_TRANSACTIONID));
		transItem.setItemID(rs.getInt(Database.TRANSACTIONITEM_ITEMID));
		transItem.setUnitPrice(rs.getFloat(Database.TRANSACTIONITEM_UNITPRICE));
		transItem.setQuantity(rs.getInt(Database.TRANSACTIONITEM_QUANTITY));
		transItem.setAction(rs.getString(Database.TRANSACTIONITEM_ACTION));

		return transItem;
	}
	
	public static boolean addTransactionItem(TransactionItem transItem){		
		try{
			Connection c = Database.getConnection();

			String query = "INSERT INTO "
					+ Database.TRANSACTIONITEM_TABLE
					+ " VALUES (?, ?, ?, ?, ?)";
			
			PreparedStatement statement = c.prepareStatement(query);
			
			statement.setInt(1, transItem.getTransactionID());
			statement.setInt(2, transItem.getItemID());
			statement.setFloat(3, transItem.getUnitPrice());
			statement.setInt(4, transItem.getQuantity());
			statement.setString(5, transItem.getAction());
			
			statement.executeUpdate();
			
			return true;
			
		} catch (Exception e){
			System.err.println("Error");
			System.err.println(e.getMessage());
		}

		return false;
	}
	
	/*This method updates the quantity of Transacted Item. You have to call newTransactedItemQuantity first*/
	public static boolean updateTransactedItem(TransactionItem transItem){
		try{
			boolean flag = true;
			
			Connection c = Database.getConnection();

			String query = "UPDATE " + Database.TRANSACTIONITEM_TABLE
							+ " SET " + Database.TRANSACTIONITEM_QUANTITY + " = ?," 
							+ Database.TRANSACTIONITEM_ACTION + " = ?"
							+ " WHERE " + Database.TRANSACTIONITEM_TRANSACTIONID + " = ?"
							+ " AND " + Database.TRANSACTIONITEM_ITEMID + " = ?";

			PreparedStatement st = c.prepareStatement(query);
			
			st.setInt(1, transItem.getQuantity());
			st.setString(2, transItem.getAction());
			st.setInt(3, transItem.getTransactionID());
			st.setInt(4, transItem.getItemID());
			
			st.executeUpdate();
			
			st.close();
			
			return flag;
			
		}catch (Exception e){
			System.err.println("Error");
			System.err.println(e.getMessage());
			
			return false;
		}
	}
	
//	public static ArrayList<TransactionItem> getTransactionItemsByTransactionID (Transaction transaction){
//		ArrayList<TransactionItem> transitemsList = new ArrayList<TransactionItem>();
//		try{
//			Connection c = Database.getConnection();
//			String query = "SELECT * "
//							+ " FROM " + Database.TRANSACTIONITEM_TABLE
//							+ " WHERE " + Database.TRANSACTIONITEM_TRANSACTIONID + " = " + transaction.getID();
//		
//			PreparedStatement st = c.prepareStatement(query);
//			ResultSet rs = st.executeQuery(query);
//
//			while (rs.next()) {
//				transitemsList.add(toTransItem(rs));
//			}
//
//			rs.close();
//			st.close();
//			//conn.close();
//			
//			return transitemsList;
//		} catch (Exception e) {
//			System.err.println("Error");
//			System.err.println(e.getMessage());
//			return null;
//		}
//	}
	
	public static ArrayList<TransactionItem> getAllTransactionItems() {
		 ArrayList<TransactionItem> transItemList = new ArrayList<TransactionItem>();
		try{
			Connection c = Database.getConnection();
			String query = "SELECT * "
					+ " FROM " + Database.TRANSACTIONITEM_TABLE;
			
			PreparedStatement st = c.prepareStatement(query);
			ResultSet rs = st.executeQuery(query);
			
			while (rs.next()) {
				transItemList.add(toTransItem(rs));
			}
			
			rs.close();
			st.close();
			
			
		}catch (Exception e){
			System.err.println("Error");
			System.err.println(e.getMessage());
		}

		return transItemList;
	}
	
//	public static int newTransactedItemQuantity(TransactionItem transItem, int quantity){
//		
//		int newQuantity = transItem.getQuantity() - quantity;
//		
//		if (newQuantity > 0){
//			transItem.setQuantity(newQuantity);
//			return transItem.getQuantity();
//		}
//		
//		return -1;
//	}
//	
//	public static float computeTransaction(ArrayList<TransactionItem> transItemsList){
//		//ArrayList<TransactionItem> transitemsList = TransactionItemService.getTransactionItems(transaction);
//		
//		float total = 0;
//		float unitPrice = 0;
//		int qty = 0;
//		
//		for(int i=0; i<transItemsList.size(); i++){
//			unitPrice = transItemsList.get(i).getUnitPrice();
//			qty = transItemsList.get(i).getQuantity();
//			total += unitPrice * qty;
//		}
//		
//		return total;
//	}
}
