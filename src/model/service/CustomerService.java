package model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import model.Database;
import model.entity.Customer;

public class CustomerService {

	private static Customer toCustomer(ResultSet rs) throws SQLException {
		Customer customer = new Customer();

		customer.setId(rs.getInt(Database.CUSTOMER_ID));
		customer.setName(rs.getString(Database.CUSTOMER_NAME));
		customer.setCompany(rs.getString(Database.CUSTOMER_COMPANY));
		customer.setContact(rs.getString(Database.CUSTOMER_CONTACT));

		return customer;
	}

	public static int addCustomer(Customer customer) throws SQLException {
		Connection connect = Database.getConnection();

		boolean customerExist = customerExists(customer);

		if (!customerExist || getAllCustomers().isEmpty()) {
			String query = "INSERT INTO "
					+ Database.CUSTOMER_TABLE
					+ " VALUES (?, ?, ?, ?)";

			try {
				PreparedStatement statement = connect.prepareStatement(query);

				statement.setNull(1, Types.INTEGER);
				statement.setString(2, customer.getName());
				statement.setString(3, customer.getCompany());
				statement.setString(4, customer.getContact());

				statement.executeUpdate();
				System.out.println("[CONTACTS] INSERT SUCCESS!");

				try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						return (int) (generatedKeys.getLong(1));
					} else {
						throw new SQLException("Creating user failed, no ID obtained.");
					}
				}
			} catch (SQLException ev) {
				ev.printStackTrace();
				System.out.println("[CONTACTS] INSERT FAILED!");
			}
		}
		return -1;
	}

	private static boolean customerExists(Customer customer) throws SQLException {
		ArrayList<Customer> customers = new ArrayList<>();
		Connection connect = Database.getConnection();
		String query = "SELECT * "
				+ " FROM " + Database.CUSTOMER_TABLE
				+ " WHERE " + Database.CUSTOMER_NAME
				+ " LIKE CONCAT('%', ?, '%') AND "
				+ Database.CUSTOMER_COMPANY
				+ " LIKE CONCAT('%', ?, '%') AND "
				+ Database.CUSTOMER_CONTACT + " = ?";

		try {
			PreparedStatement statement = connect.prepareStatement(query);

			statement.setString(1, customer.getName());
			statement.setString(2, customer.getCompany());
			statement.setString(3, customer.getContact());
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				customers.add(toCustomer(rs));
			}

			rs.close();
			statement.close();
			connect.close();

			System.out.println("[CONTACTS] SELECT SUCCESS!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[CONTACTS] SELECT FAILED!");
			return false;
		}

		return true;
	}

	public static ArrayList<Customer> getAllCustomers() throws SQLException {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		Connection connect = Database.getConnection();
		String query = "SELECT * "
				+ " FROM " + Database.CUSTOMER_TABLE;

		try {
			PreparedStatement statement = connect.prepareStatement(query);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				customers.add(toCustomer(rs));
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

		return customers;
	}
	
//	public static Customer getCustomer(int customerID) throws SQLException {
//		Customer customer = null;
//		Connection connect = Database.getConnection();
//		String query = "SELECT * "
//				+ " FROM " + Database.CUSTOMER_TABLE
//				+ " WHERE " + Database.CUSTOMER_ID + "= " + customerID;
//
//		try {
//			PreparedStatement statement = connect.prepareStatement(query);
//
//			ResultSet rs = statement.executeQuery(query);
//
//			if(rs.next()){
//				customer = toCustomer(rs);
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
//		return customer;
//	}
}
