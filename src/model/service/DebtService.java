package model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Database;
import model.entity.Debt;

public class DebtService {

	private static Debt toDebt(ResultSet rs) throws SQLException {
		Debt debt = new Debt();

		debt.setTransactionID(rs.getInt("TransactionID"));
		debt.setCustomerID(rs.getInt("CustomerID"));
		debt.setPaid(rs.getFloat("Paid"));
		debt.setDate(rs.getDate("Date"));

		return debt;
	}

	public static boolean payDebt(Debt debt) throws SQLException {
		Connection connect = Database.getConnection();
		String query = "DELETE FROM "
				+ Debt.TABLE_NAME
				+ " WHERE " + Debt.DEBT_TRANSID + " = " + debt.getTransactionID()
				+ " AND " + Debt.DEBT_CUSTOMERID + " = " + debt.getCustomerID()
				+ " AND " + Debt.DEBT_DATE + " = " + debt.getDate();

		try {
			PreparedStatement statement = connect.prepareStatement(query);

			statement.setInt(1, debt.getTransactionID());
			statement.setInt(2, debt.getCustomerID());
			statement.setFloat(3, debt.getPaid());
			statement.setDate(4, debt.getDate());

			statement.executeUpdate();
			System.out.println("[CONTACTS] INSERT SUCCESS!");
			return true;
		} catch (SQLException ev) {
			ev.printStackTrace();
			System.out.println("[CONTACTS] INSERT FAILED!");
		}
		return false;
	}

	public static boolean countAsDebt(Debt debt) throws SQLException {
		Connection connect = Database.getConnection();
		String query = "INSERT INTO "
				+ Debt.TABLE_NAME
				+ " VALUES (?, ?, ?, ?)";

		try {
			PreparedStatement statement = connect.prepareStatement(query);

			statement.setInt(1, debt.getTransactionID());
			statement.setInt(2, debt.getCustomerID());
			statement.setFloat(3, debt.getPaid());
			statement.setDate(4, debt.getDate());

			statement.executeUpdate();
			System.out.println("[CONTACTS] INSERT SUCCESS!");
			return true;
		} catch (SQLException ev) {
			ev.printStackTrace();
			System.out.println("[CONTACTS] INSERT FAILED!");
		}
		return false;
	}

//	public static ArrayList<Debt> getDebtsOf(int customerID) throws SQLException {
//		ArrayList<Debt> debts = new ArrayList<>();
//		Connection connect = Database.getConnection();
//		String query = "SELECT * "
//				+ " FROM " + Debt.TABLE_NAME
//				+ " WHERE " + Debt.DEBT_CUSTOMERID + " = ?";
//
//		try {
//			PreparedStatement statement = connect.prepareStatement(query);
//			statement.setInt(1, customerID);
//			ResultSet rs = statement.executeQuery();
//
//			while (rs.next()) {
//				debts.add(toDebt(rs));
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
//		return debts;
//	}
//
//	public static ArrayList<Debt> getSpecificDebt(int transactionID) throws SQLException {
//		ArrayList<Debt> debts = new ArrayList<>();
//		Connection connect = Database.getConnection();
//		String query = "SELECT * "
//				+ " FROM " + Debt.TABLE_NAME
//				+ " WHERE " + Debt.DEBT_TRANSID + " = ?";
//
//		try {
//			PreparedStatement statement = connect.prepareStatement(query);
//			statement.setInt(1, transactionID);
//			ResultSet rs = statement.executeQuery();
//
//			while (rs.next()) {
//				debts.add(toDebt(rs));
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
//		return debts;
//	}

	public static ArrayList<Debt> getAllDebts() throws SQLException {
		ArrayList<Debt> debts = new ArrayList<>();
		Connection connect = Database.getConnection();
		String query = "SELECT * "
				+ " FROM " + Debt.TABLE_NAME;

		try {
			PreparedStatement statement = connect.prepareStatement(query);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				debts.add(toDebt(rs));
				System.out.println("debt service 144");
			}

			rs.close();
			statement.close();
			connect.close();

			System.out.println("[CONTACTS] SELECT SUCCESS!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[CONTACTS] SELECT FAILED!");
			return null;
		}

		return debts;
	}
}
