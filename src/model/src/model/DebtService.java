package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import static model.TransactionService.pw;
import static model.TransactionService.toTransaction;
import static model.TransactionService.url;
import static model.TransactionService.user;

/**
 *
 * @author Jilyan
 */
public class DebtService {

	private static final String url = "jdbc:mysql://localhost:3306/sampledb";
	private static final String user = "root";
	private static final String pw = "1234";

	private static Debt toDebt(ResultSet rs) throws SQLException {
		Debt debt = new Debt();

		debt.setTransactionID(rs.getInt("TransactionID"));
		debt.setCustomerID(rs.getInt("CustomerID"));
		debt.setPaid(rs.getFloat("Paid"));
		debt.setDate(rs.getDate("Date"));

		return debt;
	}

	public static boolean updateDebt(Debt debt) throws SQLException {
		Connection connect = DriverManager.getConnection(url, user, pw);
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

	public static ArrayList<Debt> getDebtsOf(int customerID) throws SQLException {
		ArrayList<Debt> debts = new ArrayList<>();
		Connection connect = DriverManager.getConnection(url, user, pw);
		String query = "SELECT * "
				+ " FROM " + Debt.TABLE_NAME
				+ " WHERE " + Debt.DEBT_CUSTOMERID + " = ?";

		try {
			PreparedStatement statement = connect.prepareStatement(query);
			statement.setInt(1, customerID);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				debts.add(toDebt(rs));
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

	public static ArrayList<Debt> getSpecificDebt(int transactionID) throws SQLException {
		ArrayList<Debt> debts = new ArrayList<>();
		Connection connect = DriverManager.getConnection(url, user, pw);
		String query = "SELECT * "
				+ " FROM " + Debt.TABLE_NAME
				+ " WHERE " + Debt.DEBT_TRANSID + " = ?";

		try {
			PreparedStatement statement = connect.prepareStatement(query);
			statement.setInt(1, transactionID);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				debts.add(toDebt(rs));
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

	public static ArrayList<Debt> getAllDebts() throws SQLException {
		ArrayList<Debt> debts = new ArrayList<>();
		Connection connect = DriverManager.getConnection(url, user, pw);
		String query = "SELECT * "
				+ " FROM " + Debt.TABLE_NAME;

		try {
			PreparedStatement statement = connect.prepareStatement(query);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				debts.add(toDebt(rs));
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
