package model;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Model {
	private ArrayList<Customer> customers;
	private ArrayList<Debt> debts;
	private ArrayList<Transaction> transactions;
	private ArrayList<TransactionItem> transactionItems;
	
	Model() throws SQLException{
		customers = CustomerService.getAllCustomers();
		debts = DebtService.getAllDebts();
		transactions = TransactionService.getAllTransactions();
		transactionItems = TransactionItemService.getAllTransactionItems();
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
	
	public int newTransaction(Date date){
		Transaction transaction = new Transaction();
		transaction.setIsPaid(0);
		transaction.setTotal(0);
		transaction.setDate(date);
		
		int id = TransactionService.newTransaction(transaction);
		if(id != -1){
			transaction.setID(id);
			transactions.add(transaction);
			return id;
		}
		return -1;
	}
	
	public Customer findCustomer(int customerID) throws SQLException {
		for (int i = 0; i < customers.size(); ++i) {
			if (customers.get(i).getId() == customerID) {
				return customers.get(i);
			}
		}
		return null;
	}
	
	public Transaction findTransaction(int transactionID){
		for (int i = 0; i < transactions.size(); ++i) {
			if (transactions.get(i).getID() == transactionID) {
				return transactions.get(i);
			}
		}
		return null;
	}
	
	public TransactionItem findTransactionItem(int transactionID, int itemID){
		for (int i = 0; i < transactionItems.size(); ++i) {
			if (transactionItems.get(i).getTransactionID() == transactionID &&
				transactionItems.get(i).getItemID() == itemID) {
				return transactionItems.get(i);
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
	
	public ArrayList<Transaction> getAllTransactions(){
		return TransactionService.getAllTransactions();
	}
	
	public ArrayList<TransactionItem> getAllTransactionItems(){
		return TransactionItemService.getAllTransactionItems();
	}
	
	/*call this method after you are done adding items into the transaction*/
	public boolean updateTransactionTotal(Transaction transaction){
		//update arrayList
		transaction.setTotal(TransactionService.computeTransaction(transaction).getTotal());
		return TransactionService.updateTransactionTotal(transaction);
	}
	
	public boolean updateTransactionStatus(Transaction transaction){
		//update arrayList
		return TransactionService.updateTransactionStatus(transaction); 
		
	}
	
	public boolean addTransactionItem(TransactionItem transItem){
		if (TransactionItemService.addTransactionItem(transItem) == true){
			transactionItems.add(transItem);
			//after adding, decrease the inventory
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean refundTransactedItem(TransactionItem transItem, int quantity){
		//update arrayList
		int newQuantity = TransactionItemService.newTransactedItemQuantity(transItem, quantity);
		if (newQuantity != - 1){
			transItem.setQuantity(TransactionItemService.newTransactedItemQuantity(transItem, quantity));
			 updateTransactionTotal(findTransaction(transItem.getTransactionID()));
		}
	
		return TransactionItemService.updateTransactedItem(transItem);
	}
	
	/* This is when you replace the items with the same kind of item*/
	public void replaceTransactedItem(TransactionItem transItem, int quantity){
		//update arrayList
		int newQuantity = TransactionItemService.newTransactedItemQuantity(transItem, quantity);
		
		if (newQuantity != - 1){
			transItem.setQuantity(TransactionItemService.newTransactedItemQuantity(transItem, quantity));
		}
		
		if (TransactionItemService.updateTransactedItem(transItem)){
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			//??? format of date?
			
			TransactionItem replaceTransItem = transItem;
			replaceTransItem.setTransactionID(newTransaction(date));
			replaceTransItem.setAction("replace");
			
			addTransactionItem(replaceTransItem);
		}
	}	
	
	public void updateTransactionList(Transaction oldTransaction, Transaction newTransaction){
		transactions.set(transactions.indexOf(oldTransaction), newTransaction);
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
