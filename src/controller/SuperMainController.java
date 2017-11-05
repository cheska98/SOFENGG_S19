package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class SuperMainController {
	
	@FXML AnchorPane sidebar;
	@FXML AnchorPane transaction;
	@FXML AnchorPane display;
	@FXML AnchorPane inventory;
	@FXML AnchorPane reports;
	@FXML AnchorPane debtList;
	@FXML AnchorPane refundReplace;
	
	@FXML SidebarController sidebarCtr;
	@FXML TransactionController transactionCtr;
	@FXML DisplayController displayCtr;
	@FXML InventoryController inventoryCtr;
	@FXML ReportsController reportsCtr;
	@FXML DebtListController debtListCtr;
	@FXML RefundReplaceController refundReplaceCtr;
	
	public SuperMainController() {
		
	}
	
	public void initialize() {
		sidebarCtr.initialize();
		sidebarCtr.initializeButtons(this);
		setSalesVisible();
	}
	
	public void setTransactionVisible() {
		transaction.setVisible(true);
		display.setVisible(false);
		inventory.setVisible(false);
		reports.setVisible(false);
		debtList.setVisible(false);
		refundReplace.setVisible(false);
	}
	
	public void setDisplayVisible() {
		transaction.setVisible(false);
		display.setVisible(true);
		inventory.setVisible(false);
		reports.setVisible(false);
		debtList.setVisible(false);
		refundReplace.setVisible(false);
	}

	public void setInventoryVisible() {
		transaction.setVisible(false);
		display.setVisible(false);
		inventory.setVisible(true);
		reports.setVisible(false);
		debtList.setVisible(false);
		refundReplace.setVisible(false);
	}

	public void setReportsVisible() {
		transaction.setVisible(false);
		display.setVisible(false);
		inventory.setVisible(false);
		reports.setVisible(true);
		debtList.setVisible(false);
		refundReplace.setVisible(false);
	}

	public void setDebtListVisible() {
		transaction.setVisible(false);
		display.setVisible(false);
		inventory.setVisible(false);
		reports.setVisible(false);
		debtList.setVisible(true);
		refundReplace.setVisible(false);
	}

	public void setRefundReplaceVisible() {
		transaction.setVisible(false);
		display.setVisible(false);
		inventory.setVisible(false);
		reports.setVisible(false);
		debtList.setVisible(false);
		refundReplace.setVisible(true);
	}
	
}
