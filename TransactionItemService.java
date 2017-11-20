package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TransactionItemService {
	public static final String driver = "com.mysql.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/sampledb";
	public static final String user = "root";
	public static final String pw = "FrenchFries";
	public static final String tablename = "transaction_items";
	
	public static TransactionItem toTransItem(ResultSet rs) throws SQLException {
		TransactionItem transItem = new TransactionItem();

		transItem.setTransactionID(rs.getInt("transactionID"));
		transItem.setItemID(rs.getInt("itemID"));
		transItem.setUnitPrice(rs.getFloat("unitPrice"));
		transItem.setQuantity(rs.getInt("quantity"));
		transItem.setAction(rs.getString("action"));

		return transItem;
	}
	
	public static boolean addTransactionItem(TransactionItem transItem){		
		try{
			boolean flag = false;
			
			Connection conn = DriverManager.getConnection(url, user, pw);

			String query = "INSERT INTO " + tablename
					+ " (transactionID, itemID, unitPrice, quantity, action) VALUES ('"
					+ transItem.getTransactionID() + "', " 
					+ transItem.getItemID() + ","
					+ transItem.getUnitPrice() + ","
					+ transItem.getQuantity() + ","
					+ "'" + transItem.getAction() + "');";

			PreparedStatement st = conn.prepareStatement(query);
			
			if(st.executeUpdate(query) > 0) {
				flag = true;
			}
			
			st.close();
			
			return flag;
		} catch (Exception e){
			System.err.println("Error");
			System.err.println(e.getMessage());
			
			return false;
		}
	}
	
	public static int newTransactedItemQuantity(TransactionItem transItem, int quantity){
		
		int newQuantity = transItem.getQuantity() - quantity;
		
		if (newQuantity > 0){
			transItem.setQuantity(newQuantity);
			return transItem.getQuantity();
		}
		
		return -1;
	}
	
	/*This method updates the quantity of Transacted Item. You have to call newTransactedItemQuantity first*/
	public static boolean updateTransactedItem(TransactionItem transItem){
		try{
			boolean flag = true;
			
			Connection conn = DriverManager.getConnection(url, user, pw);

			String query = "UPDATE INTO " + tablename
							+ " SET quantity = " + transItem.getQuantity()
							+ " SET action = " + transItem.getAction()
							+  "WHERE transactionID = " + transItem.getTransactionID()
							+ " AND itemID = " + transItem.getItemID();

			PreparedStatement st = conn.prepareStatement(query);
			
			if(st.executeUpdate(query) > 0) {
				flag = true;
			}
			
			st.close();
			
			return flag;
			
		}catch (Exception e){
			System.err.println("Error");
			System.err.println(e.getMessage());
			
			return false;
		}
	}
	
	public static ArrayList<TransactionItem> getTransactionItems (Transaction transaction){
		ArrayList<TransactionItem> transitemsList = new ArrayList<TransactionItem>();
		try{
			Connection conn = DriverManager.getConnection(url, user, pw);
			String query = "SELECT * "
							+ " FROM " + "transaction_items"
							+ " WHERE TransactionID = " + transaction.getID();
		
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				transitemsList.add(toTransItem(rs));
			}

			rs.close();
			st.close();
			//conn.close();
			
			return transitemsList;
		} catch (Exception e) {
			System.err.println("Error");
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	public static ArrayList<TransactionItem> getAllTransactionItems() {
		 ArrayList<TransactionItem> transItemList = new ArrayList<TransactionItem>();
		try{
			Connection conn = DriverManager.getConnection(url, user, pw);
			String query = "SELECT * "
					+ " FROM " + "transaction_items";
			
			PreparedStatement st = conn.prepareStatement(query);
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
}
