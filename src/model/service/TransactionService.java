package model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;

import model.Database;
import model.entity.Transaction;

public class TransactionService {

	public static Transaction toTransaction(ResultSet rs) throws SQLException {
		Transaction transaction = new Transaction();

		transaction.setID(rs.getInt(Database.TRANSACTION_ID));
		transaction.setDate(rs.getDate(Database.TRANSACTION_DATE));
		transaction.setTotal(rs.getFloat(Database.TRANSACTION_TOTAL));
		transaction.setIsPaid(rs.getInt(Database.TRANSACTION_IS_PAID));

		return transaction;
	}

	/* This method inserts a new Transaction. 
	 * total and isPaid is set to 0 because no items added no paid yet
	 * Call this function first before adding items
	 */
	public static int addTransaction(Transaction transaction) {
		int latestTransID = -1;
		try {
			Connection c = Database.getConnection();

			String query = "INSERT INTO "
					+ Database.TRANSACTION_TABLE
					+ " VALUES (?, ?, ?, ?)";

			PreparedStatement statement = c.prepareStatement(query);

			statement.setNull(1, Types.INTEGER);
			statement.setDate(2, null);
			statement.setFloat(3, transaction.getTotal());
			statement.setInt(4, transaction.getIsPaid());

			statement.executeUpdate();
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					latestTransID = (int) (generatedKeys.getLong(1));
				} else {
					throw new SQLException("Creating user failed, no ID obtained.");
				}
			}

		} catch (Exception e) {
			System.err.println("Error");
			System.err.println(e.getMessage());
		}
		System.out.println(latestTransID);
		return latestTransID;
	}

	public static boolean updateTransaction(Transaction transaction) {
		try {
			boolean flag = true;

			Connection c = Database.getConnection();

			String query = "UPDATE " + Database.TRANSACTION_TABLE
					+ " SET " + Database.TRANSACTION_TOTAL + " = ?,"
					+ Database.TRANSACTION_DATE + "  = ?"
					+ " WHERE " + Database.TRANSACTION_ID + " = ?";

			PreparedStatement st = c.prepareStatement(query);

			st.setFloat(1, transaction.getTotal());
			st.setInt(2, transaction.getIsPaid());
			st.setInt(3, transaction.getID());

			st.executeUpdate();

			st.close();

			return flag;

		} catch (Exception e) {
			System.err.println("Error");
			System.err.println(e.getMessage());

			return false;
		}
	}

	public static ArrayList<Transaction> getAllTransactions() {
		ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
		try {
			Connection c = Database.getConnection();
			String query = "SELECT * "
					+ " FROM " + Database.TRANSACTION_TABLE;

			PreparedStatement st = c.prepareStatement(query);
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				transactionList.add(toTransaction(rs));
			}

			rs.close();
			st.close();

		} catch (Exception e) {
			System.err.println("Error");
			System.err.println(e.getMessage());
		}

		return transactionList;
	}
	
//	public static Transaction findTransaction(int transID) throws SQLException {
//		Transaction transaction = null;
//		Connection connect = Database.getConnection();
//		String query = "SELECT * "
//				+ " FROM " + Database.TRANSACTION_TABLE
//				+ " WHERE " + Database.TRANSACTION_ID + "= " + transID;
//
//		try {
//			PreparedStatement statement = connect.prepareStatement(query);
//			ResultSet rs = statement.executeQuery(query);
//			
//			if(rs.next()){
//				transaction = toTransaction(rs);
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
//		return transaction;
//	}
}
