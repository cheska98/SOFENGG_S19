package model;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Model {
	private ArrayList<Transaction> transactions;
	private ArrayList<TransactionItem> transactionItems;
	
	public Model(){
		transactions = TransactionService.getAllTransactions();
		transactionItems = TransactionItemService.getAllTransactionItems();
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
	
	public ArrayList<Transaction> getAllTransactions(){
		return TransactionService.getAllTransactions();
	}
	
	public ArrayList<TransactionItem> getAllTransactionItems(){
		return TransactionItemService.getAllTransactionItems();
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
}
