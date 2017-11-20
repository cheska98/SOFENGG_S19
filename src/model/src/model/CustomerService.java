package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author Jilyan
 */
public class CustomerService {

	private static final String url = "jdbc:mysql://localhost:3306/sampledb";
	private static final String user = "root";
	private static final String pw = "1234";

	private static Customer toCustomer(ResultSet rs) throws SQLException {
		Customer customer = new Customer();

		customer.setId(rs.getInt(Customer.CUSTOMER_ID));
		customer.setName(rs.getString(Customer.CUSTOMER_NAME));
		customer.setCompany(rs.getString(Customer.CUSTOMER_COMPANY));
		customer.setContact(rs.getString(Customer.CUSTOMER_CONTACT));

		return customer;
	}

	public static int addCustomer(Customer customer) throws SQLException {
		Connection connect = DriverManager.getConnection(url, user, pw);

		ArrayList<Customer> customerList = customerExists(customer);

		if (!customerList.isEmpty()) {
			String query = "INSERT INTO "
					+ Customer.TABLE_NAME
					+ " VALUES (?, ?, ?, ?)";

			try {
				PreparedStatement statement = connect.prepareStatement(query);

				statement.setNull(1, Types.INTEGER);
				statement.setString(2, customer.getName());
				statement.setString(3, customer.getCompany());
				statement.setString(4, customer.getContact());

				statement.executeUpdate();
				System.out.println("[CONTACTS] INSERT SUCCESS!");
				
				return statement.getGeneratedKeys().getInt(Customer.CUSTOMER_ID);
			} catch (SQLException ev) {
				ev.printStackTrace();
				System.out.println("[CONTACTS] INSERT FAILED!");
			}
		}
		return -1;
	}

	private static ArrayList<Customer> customerExists(Customer customer) throws SQLException {
		ArrayList<Customer> customers = new ArrayList<>();
		Connection connect = DriverManager.getConnection(url, user, pw);
		String query = "SELECT * "
				+ " FROM " + Customer.TABLE_NAME
				+ " WHERE " + Customer.CUSTOMER_NAME
				+ " LIKE CONCAT('%', ?, '%') AND "
				+ Customer.CUSTOMER_COMPANY
				+ " LIKE CONCAT('%', ?, '%') AND "
				+ Customer.CUSTOMER_CONTACT + " = ?";

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
			return null;
		}

		return customers;
	}

	public static Customer getCustomer(int customerID) throws SQLException {
		Customer customer = null;
		Connection connect = DriverManager.getConnection(url, user, pw);
		String query = "SELECT * "
				+ " FROM " + Customer.TABLE_NAME
				+ " WHERE " + Customer.CUSTOMER_ID + "= ?";

		try {
			PreparedStatement statement = connect.prepareStatement(query);
			statement.setInt(1, customerID);

			ResultSet rs = statement.executeQuery();

			customer = toCustomer(rs);
			rs.close();
			statement.close();
			connect.close();

			System.out.println("[CONTACTS] SELECT SUCCESS!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[CONTACTS] SELECT FAILED!");
			return null;
		}

		return customer;
	}

	public static ArrayList<Customer> getAllCustomers() throws SQLException {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		Connection connect = DriverManager.getConnection(url, user, pw);
		String query = "SELECT * "
				+ " FROM " + Customer.TABLE_NAME;

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
}
