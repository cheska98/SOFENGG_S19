package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class SuperMainController {
	
	@FXML AnchorPane sidebar;
	@FXML AnchorPane sales;
	@FXML AnchorPane display;
	@FXML AnchorPane inventory;
	@FXML AnchorPane reports;
	@FXML AnchorPane debtList;
	@FXML AnchorPane refundReplace;
	
	@FXML AnchorPane addPopUp;
	@FXML AnchorPane reportsPopUp;
	@FXML AnchorPane topItemsPopUp;
	@FXML AnchorPane alertDebtList;
	@FXML AnchorPane debtListCust;
	@FXML AnchorPane addCustPop;
	
	@FXML SidebarController sidebarCtr;
	@FXML SalesController salesCtr;
	@FXML DisplayController displayCtr;
	@FXML InventoryController inventoryCtr;
	@FXML ReportsController reportsCtr;
	@FXML DebtListController debtListCtr;
	@FXML RefundReplaceController refundReplaceCtr;
	
	@FXML AddPopUpController addPopUpCtr;
	@FXML ReportsPopUpController reportsPopUpCtr;
	@FXML TopItemsPopUpController topItemsPopUpCtr;
	@FXML AlertDebtListController alertDebtListCtr;
	@FXML DebtListCustController debtListCustCtr;
	@FXML AddCustController addCustCtr;
	
	public SuperMainController() {
		
	}
	
	public void initialize() {
		sidebarCtr.initialize();
		sidebarCtr.initializeButtons(this);
		setPopUps();
		setSalesVisible();
	}
	
	public void setPopUps() {
		addPopUp.setVisible(false);
		reportsPopUp.setVisible(false);
		topItemsPopUp.setVisible(false);
		alertDebtList.setVisible(false);
		debtListCust.setVisible(false);
		addCustPop.setVisible(false);
	}
	
	public void setSalesVisible() {
		sales.setVisible(true);
		display.setVisible(false);
		inventory.setVisible(false);
		reports.setVisible(false);
		debtList.setVisible(false);
		refundReplace.setVisible(false);
	}
	
	public void setDisplayVisible() {
		sales.setVisible(false);
		display.setVisible(true);
		inventory.setVisible(false);
		reports.setVisible(false);
		debtList.setVisible(false);
		refundReplace.setVisible(false);
	}

	public void setInventoryVisible() {
		sales.setVisible(false);
		display.setVisible(false);
		inventory.setVisible(true);
		reports.setVisible(false);
		debtList.setVisible(false);
		refundReplace.setVisible(false);
	}

	public void setReportsVisible() {
		sales.setVisible(false);
		display.setVisible(false);
		inventory.setVisible(false);
		reports.setVisible(true);
		debtList.setVisible(false);
		refundReplace.setVisible(false);
	}

	public void setDebtListVisible() {
		sales.setVisible(false);
		display.setVisible(false);
		inventory.setVisible(false);
		reports.setVisible(false);
		debtList.setVisible(true);
		refundReplace.setVisible(false);
	}

	public void setRefundReplaceVisible() {
		sales.setVisible(false);
		display.setVisible(false);
		inventory.setVisible(false);
		reports.setVisible(false);
		debtList.setVisible(false);
		refundReplace.setVisible(true);
	}
	
}
