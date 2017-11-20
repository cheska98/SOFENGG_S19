package model;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jilyan
 */
public class Model {

	private ArrayList<Customer> customers;
	private ArrayList<Debt> debts;
	private ArrayList<Transaction> transactions;

	Model() throws SQLException {
		customers = CustomerService.getAllCustomers();
		debts = DebtService.getAllDebts();
		transactions = TransactionService.getAllTransactions();
	}

	public boolean newCustomer(Customer customer) throws SQLException {
		int id = CustomerService.addCustomer(customer);
		if (id != -1) {
			customer.setId(id);
			customers.add(customer);
			return true;
		}
		return false;
	}

	public Customer findCustomer(int customerID) throws SQLException {
		for (int i = 0; i < customers.size(); ++i) {
			if (customers.get(i).getId() == customerID) {
				return customers.get(i);
			}
		}
		return null;
	}

	public ArrayList<Customer> getAllCustomers() throws SQLException {
		return customers;
	}

	public ArrayList<Transaction> getAllDebts() {
		return TransactionService.getUnpaidTransactions();
	}

	public boolean payDebt(Debt debt) throws SQLException {
		boolean success = DebtService.updateDebt(debt);
		if (success) {
			debts.add(debt);
		}
		return success;
	}

	public ArrayList<Debt> getDebtPaymentList() throws SQLException {
		return DebtService.getAllDebts();
	}

	public ArrayList<Debt> getDebtPaymentListOf(int customerID) throws SQLException {
		ArrayList<Debt> debtsOf = new ArrayList<>();
		for (int i = 0; i < debts.size(); ++i) {
			if (debts.get(i).getCustomerID() == customerID) {
				debtsOf.add(debts.get(i));
			}
		}
		return debtsOf;
	}

	public ArrayList<Debt> getDebtPaymentListFor(int transactionID) throws SQLException {
		ArrayList<Debt> debtsOf = new ArrayList<>();
		for (int i = 0; i < debts.size(); ++i) {
			if (debts.get(i).getTransactionID() == transactionID) {
				debtsOf.add(debts.get(i));
			}
		}
		return debtsOf;
	}

}
