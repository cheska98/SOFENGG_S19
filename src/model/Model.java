package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.entity.Customer;
import model.entity.Debt;
import model.entity.Item;
import model.entity.Stock;
import model.entity.Transaction;
import model.entity.TransactionItem;
import model.service.CustomerService;
import model.service.DebtService;
import model.service.ItemService;
import model.service.StockService;
import model.service.TransactionItemService;
import model.service.TransactionService;

public class Model {

	private ArrayList<Customer> customers = new ArrayList<>();
	private ArrayList<Debt> debts;
	private ArrayList<Transaction> transactions;
	private ArrayList<TransactionItem> transactionItems;
	private ArrayList<Item> items;
	private ArrayList<Stock> inventory;
	private ArrayList<Stock> display;

	public Model() throws SQLException {
		customers = CustomerService.getAllCustomers();
		debts = DebtService.getAllDebts();
		transactions = TransactionService.getAllTransactions();
		transactionItems = TransactionItemService.getAllTransactionItems();
		items = ItemService.getAllItemList();
		inventory = StockService.getStockList(Database.INVENTORY_TABLE);
		display = StockService.getStockList(Database.DISPLAY_TABLE);
	}

	/*Getters*/
	public ArrayList<Customer> getAllCustomers() {
		return customers;
	}

	public ArrayList<Debt> getAllDebts() {
		return debts;
	}

	public ArrayList<Transaction> getAllTransactions() {
		return transactions;
	}

	public ArrayList<TransactionItem> getAllTransactionItems() {
		return transactionItems;
	}

	public ArrayList<Item> getAllItems() {
		return items;
	}

	public ArrayList<Stock> getAllInventoryItems() {
		return inventory;
	}

	public ArrayList<Stock> getAllDisplayItems() {
		return display;
	}
	
	/*--Customer Functions--*/
	
	/*This method adds customers to the database*/
	public boolean addCustomer(Customer customer) throws SQLException {
		int id = CustomerService.addCustomer(customer);
		if (id != -1) {
			customer.setId(id);
			customers.add(customer);
			return true;
		}
		return false;
	}

	/*This method finds a specific customer in the database
	 * @param customerID is the primary key of the customer to be found*/
	public Customer findCustomer(int customerID) throws SQLException {
		for (int i = 0; i < customers.size(); ++i) {
			if (customers.get(i).getId() == customerID) {
				return customers.get(i);
			}
		}
		return null;
	}

	/*This method finds a specific customer in the database
	 * @param name is the name of the customer to be found*/
	public Customer findCustomerByName(String name) throws SQLException {
		for (int i = 0; i < customers.size(); ++i) {
			if (customers.get(i).getName().contains(name)) {
				return customers.get(i);
			}
		}
		return null;
	}
	
	/*--Debt Functions--*/

	/*This method edits the status of a customer's debt
	 * It updates the database*/
	public boolean payDebt(int debt) throws SQLException {
		boolean success = DebtService.payDebt(debts.get(debt));
		if (success) {
			debts.remove(debt);
		}
		return success;
	}

	/*This method adds a debt to the database*/
	public boolean countAsDebt(Debt debt) throws SQLException {
		boolean success = DebtService.countAsDebt(debt);
		if (success) {
			debts.add(debt);
		}
		return success;
	}

	/*This method gets all the debts of a specific customer*/
	public ArrayList<Debt> getDebtPaymentListOf(int customerID) throws SQLException {
		ArrayList<Debt> debtsOf = new ArrayList<>();
		for (int i = 0; i < debts.size(); ++i) {
			if (debts.get(i).getCustomerID() == customerID) {
				debtsOf.add(debts.get(i));
			}
		}
		return debtsOf;
	}

	/*This method gets a specific transaction that is labeled as debt*/
	public Debt getDebtPaymentListFor(int transactionID) throws SQLException {
		Debt debt = null;
		for (int i = 0; i < debts.size(); ++i) {
			if (debts.get(i).getTransactionID() == transactionID) {
				return debt;
			}
		}
		return debt;
	}
	
	/*--Transaction Functions*/

	/* This method adds a new Transaction 
	 * It calls addTransaction where it will create a new Transaction
	 * It calls addTransactionItems to add transaction items in the parameter transItemsList
	 */
	public void newTransaction(ArrayList<TransactionItem> transItemsList, int isPaid) {
		int transID = addTransaction(transItemsList, isPaid);
		transactionItems.addAll(addTransactionItems(transItemsList, transID));
	}

	/* This method adds a transaction to the database*/
	public int addTransaction(ArrayList<TransactionItem> transItemsList, int isPaid) {
		Transaction transaction = new Transaction();
		Date date = new Date(); //???
		int transID = -1;

		//compute first
		float totalAmount = computeTransaction(transItemsList);

		transaction.setIsPaid(isPaid);
		transaction.setTotal(totalAmount);
		transaction.setDate(date);

		//create Transaction
		transID = TransactionService.addTransaction(transaction);
		transaction.setID(transID);
		//add to arrayList
		transactions.add(transaction);
		return transID;
	}
	
	/*This method finds a specific transaction in the database*/
	public Transaction findTransaction(int transactionID) {
		for (int i = 0; i < transactions.size(); ++i) {
			if (transactions.get(i).getID() == transactionID) {
				return transactions.get(i);
			}
		}
		return null;
	}

	/*This function updates a specific transaction*/
	public boolean updateTransaction(Transaction transaction) {
		boolean success = TransactionService.updateTransaction(transaction);
		if (success) {
			for (int i = 0; i < transactions.size(); ++i) {
				if (transactions.get(i).getID() == transaction.getID()) {
					transactions.set(i, transaction);
					return true;
				}
			}
		}
		return false;
	}

	/* This method add transacted items of a transaction to the database*/
	public ArrayList<TransactionItem> addTransactionItems(ArrayList<TransactionItem> transItemsList, int transID) {
		for (int i = 0; i < transItemsList.size(); i++) {
			transItemsList.get(i).setTransactionID(transID);
			if (TransactionItemService.addTransactionItem(transItemsList.get(i))) {
				transactionItems.add(transItemsList.get(i));
				//TODO this needs checking if negative ba lalabas!!!
				StockService.decrementStock(transItemsList.get(i).getItemID(), 
											transItemsList.get(i).getQuantity(), "display");
				//TODO make sure to update the arrayList of Display!!!
			}
		}
		return transItemsList;
	}

	/*This method finds a specific transacted item in the database*/
	public TransactionItem findTransactionItem(int transactionID, int itemID) {
		for (int i = 0; i < transactionItems.size(); ++i) {
			if (transactionItems.get(i).getTransactionID() == transactionID
					&& transactionItems.get(i).getItemID() == itemID) {
				return transactionItems.get(i);
			}
		}
		return null;
	}

	/*This method gets all the transaction items of a specific transaction*/
	public ArrayList<TransactionItem> getTransactionItemsByTransID(Transaction transaction) {
		ArrayList<TransactionItem> transItemList = new ArrayList<>();
		for (int i = 0; i < transactionItems.size(); ++i) {
			if (transactionItems.get(i).getTransactionID() == transaction.getID()) {
				transItemList.add(transactionItems.get(i));
			}
		}

		return transItemList;
	}

	/*This method updates a specific transacted item of a transaction*/
	public boolean updateTransactedItem(TransactionItem transItem) {
		boolean success = TransactionItemService.updateTransactedItem(transItem);
		if (success) {
			for (int i = 0; i < transactionItems.size(); ++i) {
				if (transactionItems.get(i).getTransactionID() == transItem.getTransactionID() 
						&& transactionItems.get(i).getItemID() == transItem.getItemID()) {
					transactionItems.set(i, transItem);
					return true;
				}
			}
		}
		return false;
	}
	
	/*This method compute the total of all the transaction items of a transaction*/
	public float computeTransaction(ArrayList<TransactionItem> transItemsList){
		float total = 0;
		float unitPrice = 0;
		int qty = 0;
		
		for(int i=0; i<transItemsList.size(); i++){
			unitPrice = transItemsList.get(i).getUnitPrice();
			qty = transItemsList.get(i).getQuantity();
			total += unitPrice * qty;
		}
		
		return total;
	}
	
	/*This method checks if the new quantity of the transacted item is possible*/
	public int newTransactedItemQuantity(TransactionItem transItem, int quantity){
		int newQuantity = transItem.getQuantity() - quantity;
		
		if (newQuantity > 0){
			transItem.setQuantity(newQuantity);
			return transItem.getQuantity();
		}
		
		return -1;
	}

	/* This method refunds a transacted item
	 * @param quantity is the amount of transacted item to be refunded*/
	public boolean refundTransactedItem(TransactionItem transItem, int quantity) {
		int newQuantity = newTransactedItemQuantity(transItem, quantity);

		if (newQuantity != - 1) {
			transItem.setQuantity(newQuantity);
			updateTransactedItem(transItem);
			//TODO update in arrayList too

			//update new Total of Transaction
			Transaction transaction = findTransaction(transItem.getTransactionID());
			ArrayList<TransactionItem> transItemList = getTransactionItemsByTransID(transaction);
			float totalAmount = computeTransaction(transItemList);
			transaction.setTotal(totalAmount);

			return updateTransaction(transaction);
			//TODO update in ArrayList too
		}
		return false;
	}

	/* This method replaces a transacted item*/
	public void replaceTransactedItem(TransactionItem transItem) {
		transItem.setItemID(transItem.getItemID());
		transItem.setQuantity(0);
		transItem.setUnitPrice(0);
		transItem.setAction("refund");

		ArrayList<TransactionItem> replaceTransItem = new ArrayList<>();
		replaceTransItem.add(transItem);

		newTransaction(replaceTransItem, 1);
	}

	/*--Item Functions--*/
	
	/*This method adds items to the database*/
	public boolean addItem(String desc, float price) {
		Item item = new Item(desc, price);

		int id = ItemService.addItem(item);

		if (id != -1) {
			item.setId(id);
			items.add(item);
			return true;
		} else {
			return false;
		}
	}

	/*This method edits items in the database*/
	public boolean editItem(int id, String desc, float unitPrice) {
		Item item = new Item(id, desc, unitPrice);

		if (ItemService.editItem(item)) {
			return replaceItem(item);
		} else {
			return false;
		}
	}

	/*This method updates the item in the item arraylist*/
	public boolean replaceItem(Item item) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getId() == item.getId()) {
				items.set(i, item);
				return true;
			}
		}

		return false;
	}
	
	/*This method finds a specific item in the database
	 * @param itemID is the primary key of the item to be found*/
	public Item findItem(int itemID) throws SQLException {
		for (int i = 0; i < items.size(); ++i) {
			if (items.get(i).getId() == itemID) {
				System.out.println("HELLO");
				return items.get(i);
			}
		}
		return null;
	}
	
	/*This method finds a specific item in the database
	 * @param desc is the desc of the item to be found*/
	public Item findItemByDesc(String desc) throws SQLException {
		for (int i = 0; i < items.size(); ++i) {
			if (items.get(i).getDesc().contains(desc)) {
				return items.get(i);
			}
		}
		return null;
	}

	/*This method updates the inventory or display in the database*/
	public boolean updateStock(int id, int qty, String type, boolean increment) {
		switch (type) {
			case Database.INVENTORY_TABLE:
			case Database.DISPLAY_TABLE:
				break;
			default:
				return false;
		}

		Stock stock = null;

		if (increment) {
			stock = StockService.incrementStock(id, qty, type);
		} else {
			stock = StockService.decrementStock(id, qty, type);
		}

		if (stock != null) {
			switch (type) {
				case Database.INVENTORY_TABLE:
					return replaceStock(inventory, stock);
				case Database.DISPLAY_TABLE:
					return replaceStock(display, stock);
				default:
					return false;
			}
		} else {
			return false;
		}
	}

	/*This method updates the stock arraylist*/
	public boolean replaceStock(ArrayList<Stock> list, Stock s) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == s.getId()) {
				list.set(i, s);
				return true;
			}
		}

		return false;
	}
}
