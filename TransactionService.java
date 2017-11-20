package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionService {
	public static final String driver = "com.mysql.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/sampledb";
	public static final String user = "root";
	public static final String pw = "FrenchFries";
	public static final String tablename = "transactions";
	
	public static Transaction toTransaction(ResultSet rs) throws SQLException {
		Transaction transaction = new Transaction();

		transaction.setID(rs.getInt("id"));
		transaction.setDate(rs.getDate("date"));
		transaction.setTotal(rs.getFloat("total"));
		transaction.setIsPaid(rs.getInt("idPaid"));

		return transaction;
	}
	
	/* This method inserts a new Transaction. 
	 * total and isPaid is set to 0 because no items added no paid yet
	 * Call this function first before adding items
	 */
	public static int newTransaction(Transaction transaction){
		int latestTransID = -1;
		try{
			
			Connection conn = DriverManager.getConnection(url, user, pw);

			String query = "INSERT INTO " + tablename
					+ " (date, total, isPaid) VALUES ('"
					+ transaction.getDate() + ","
					+ transaction.getTotal() + ","
					+ transaction.getIsPaid() + "');";

			PreparedStatement st = conn.prepareStatement(query);
			
			if(st.executeUpdate(query) > 0) {
				PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				stmt.executeUpdate();

				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()){
				    latestTransID = rs.getInt(1);
				}
				stmt.close();
			}
			
			st.close();
			
		} catch (Exception e){
			System.err.println("Error");
			System.err.println(e.getMessage());
		}
		return latestTransID;
	}
	
	public static boolean updateTransactionTotal(Transaction transaction){
		try{
			boolean flag = true;
			
			Connection conn = DriverManager.getConnection(url, user, pw);

			String query = "UPDATE INTO " + tablename
							+ " SET total = " + transaction.getTotal()
							+  "WHERE id = " + transaction.getID();

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
	
	public static boolean updateTransactionStatus(Transaction transaction){
		try{
			boolean flag = true;
			
			Connection conn = DriverManager.getConnection(url, user, pw);

			String query = "UPDATE INTO " + tablename
							+ " SET isPaid = " + transaction.getIsPaid()
							+  "WHERE id = " + transaction.getID();

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
	
	public static Transaction computeTransaction(Transaction transaction){
		ArrayList<TransactionItem> transitemsList = TransactionItemService.getTransactionItems(transaction);
		
		float total = 0;
		float unitPrice = 0;
		int qty = 0;
		
		for(int i=0; i<transitemsList.size(); i++){
			unitPrice = transitemsList.get(i).getUnitPrice();
			qty = transitemsList.get(i).getQuantity();
			total += unitPrice * qty;
		}
		
		transaction.setTotal(total);
		
		return transaction;
	}
	
	
	public static ArrayList<Transaction> getAllTransactions() {
		 ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
		try{
			Connection conn = DriverManager.getConnection(url, user, pw);
			String query = "SELECT * "
					+ " FROM " + "transactions";
			
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery(query);
			
			while (rs.next()) {
				transactionList.add(toTransaction(rs));
			}
			
			rs.close();
			st.close();
			
			
		}catch (Exception e){
			System.err.println("Error");
			System.err.println(e.getMessage());
		}

		return transactionList;
	}
}
