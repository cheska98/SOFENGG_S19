package controller;

import java.util.Calendar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class SuperMainController {
	
	@FXML private AnchorPane sidebar;
    @FXML private Label label1;
    @FXML private Label nameLabel;
    @FXML private Label currDateLabel;
    @FXML private Button helpButton;
    @FXML private Button transactionButton; ////////// edit from 'sales' to 'transaction'
    @FXML private Button displayButton;
    @FXML private Button logoutButton;
    @FXML private Button inventoryButton;
    @FXML private Button debtListButton;
    @FXML private Button reportsButton;
    @FXML private Button refundReplaceButton;
    @FXML private ImageView helpIcon; //////////// add this
    @FXML private ImageView transactionIcon; ////////// edit from 'sales' to 'transaction'
    @FXML private ImageView displayIcon;
    @FXML private ImageView inventoryIcon;
    @FXML private ImageView reportsIcon;
    @FXML private ImageView debtListIcon;
    @FXML private ImageView refundReplaceIcon;
    
	@FXML AnchorPane mainPane;
	@FXML StackPane stackPane;
	@FXML AnchorPane transaction;
	@FXML AnchorPane display;
	@FXML AnchorPane inventory;
	@FXML AnchorPane reports;
	@FXML AnchorPane debtList;
	@FXML AnchorPane refundReplace;
	
	@FXML TransactionController transactionController;
	@FXML DisplayController displayController;
	@FXML ReportsController reportsController;
	@FXML DebtListController debtListController;
	@FXML RefundReplaceController refundReplaceController;
	InitPanelController initialController;
/////////////// from this
    private Image transactionIconLight;
    private Image displayIconLight;
    private Image inventoryIconLight;
    private Image reportsIconLight;
    private Image debtListIconLight;
    private Image refundReplaceIconLight;
    private Image transactionIconDark;
    private Image displayIconDark;
    private Image inventoryIconDark;
    private Image reportsIconDark;
    private Image debtListIconDark;
    private Image refundReplaceIconDark;
///////////////// to this	
	private Calendar now = Calendar.getInstance();
	private String currDate = null;
	private String month = null;
	private String day = null;
	private String year = Integer.toString(now.get(Calendar.YEAR));
	private int pane = 0; ///////////// add this
	private Alert alert; ///////////// add this
	
	
	@FXML
	private void initialize() {
		
		setCurrDate();
		setLightImages(); /////////// add this
		setDarkImages(); /////////// add this
		setTransactionVisible();
		
	}
	
	public void setController(InitPanelController initialController) {
		this.initialController = initialController;
	}
	
	private void setInitialStage() {
		
		System.out.println("init");
		initialController.setInitialStage(initialController);
	
	}
	
	private void closeCurrStage() {
		
		initialController.closeStage();
		
	}
	
	public void setUsername(String username) {
		
		nameLabel.setText(username + "!");
		
	}
	
	private void setCurrDate() {
		
		if (now.get(Calendar.MONTH)+1 < 10)
			month = 0 + Integer.toString(now.get(Calendar.MONTH)+1);
		else
			month = Integer.toString(now.get(Calendar.MONTH)+1);
		
		if (now.get(Calendar.DATE) < 10)
			day = 0 + Integer.toString(now.get(Calendar.DATE));
		else
			day = Integer.toString(now.get(Calendar.DATE));
		
		currDate = year + "-" + month + "-" + day;
		
		currDateLabel.setText(currDate);
		
	}
	/////////// from this
	private void setLightImages() {
		
		transactionIconLight = new Image(getClass().getResource("../img/transaction.png").toExternalForm());
		displayIconLight = new Image(getClass().getResource("../img/display.png").toExternalForm());
		inventoryIconLight = new Image(getClass().getResource("../img/inventory.png").toExternalForm());
		reportsIconLight = new Image(getClass().getResource("../img/reports.png").toExternalForm());
		debtListIconLight = new Image(getClass().getResource("../img/debt_list.png").toExternalForm());
		refundReplaceIconLight = new Image(getClass().getResource("../img/refund-replace.png").toExternalForm());
		
	}
	
	
	private void setDarkImages() {
		
		transactionIconDark = new Image(getClass().getResource("../img/transaction_selected.png").toExternalForm());
		displayIconDark = new Image(getClass().getResource("../img/display_selected.png").toExternalForm());
		inventoryIconDark = new Image(getClass().getResource("../img/inventory_selected.png").toExternalForm());
		reportsIconDark = new Image(getClass().getResource("../img/reports_selected.png").toExternalForm());
		debtListIconDark = new Image(getClass().getResource("../img/debt_list_selected.png").toExternalForm());
		refundReplaceIconDark = new Image(getClass().getResource("../img/refund-replace_selected.png").toExternalForm());
		
	}
	////////////// to this
	@FXML
    void logoutAction(ActionEvent event) {
	
		Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
		alert.setTitle("LOGOUT");
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
			closeCurrStage();
		    setInitialStage();
		}
		
    }
	/////////////// from this
	@FXML
	void helpAction(ActionEvent event) {
		
		switch(pane) {
			case 1: alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("HELP");
					alert.setHeaderText("Transaction: where the transaction of each customer can be made");
					alert.setContentText("Add an item to the cart by entering the name of the item, then click the 'Add to Cart' button.\n\n"
										+ "Change the quantity by clicking the item's quantity and entering the quantity you want.\n\n"
										+ "Discounts on each item can be applied by clicking the unit cost and entering the doscounted unit cost\n\n"
										+ "Delete an item in the cart by checking the checkbox and clicking the 'Delete' button.\n\n"
										+ "After adding all the items in the cart, click the 'Complete' button.\n\n"
										+ "When completing a transaction, you can enter the amount paid by the customer or choose to add the customer to the debt list.");
					alert.showAndWait();
				break;
			case 2: alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("HELP");
					alert.setHeaderText("Display: where all the items in the store can be viewed\n\n");
					alert.setContentText("Add an item in the display by entering the name of the item, price, and quantity and clicking the 'Add Item' button.\n\n"
										+ "Search an item by entering a keyword and clicking the 'Search' button.\n\n"
										+ "Clear the search table by clicking the 'CLEAR' button.\n\n"
										+ "Save all the changes done by clicking the 'Save Changes' button.\n\n"
										+ "Delete an item in the display by checking the checkbox and clicking the 'Delete' button.");
					alert.showAndWait();
				break;
			case 3: alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("HELP");
					alert.setHeaderText("Inventory: where all the items in the inventory can be viewed");
					alert.setContentText("Add an item in the inventory by entering the name of the item, price, and quantity and clicking the 'Add Item' button.\n\n"
										+ "Search an item by entering a keyword and clicking the 'Search' button.\n\n"
										+ "Clear the search table by clicking the 'CLEAR' button.\n\n"
										+ "Save all the changes done by clicking the 'Save Changes' button.\n\n"
										+ "Delete an item in the display by checking the checkbox and clicking the 'Delete' button.");
					alert.showAndWait();
				break;
			case 4: alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("HELP");
					alert.setHeaderText("Reports: where the reports can be viewed or downloaded");
					alert.setContentText("View a daily report by select a date, then click the 'Ok' button.\n\n"
										+ "View a monthly report by select a month and year, then click the 'Ok' button.\n\n"
										+ "View top items for the day by clicking the 'View Top Items' button.\n\n"
										+ "Download a report by clicking the 'Download' button, the excel file can be found in __.");
					alert.showAndWait();
				break;
			case 5: alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("HELP");
					alert.setHeaderText("Debt List: where the debts of each customer can be viewed\n\n");
					alert.setContentText("Search a transaction ID or customer name by entering either one and clicking the 'Search' button.\n\n"
										+ "Double click a row to view all pending transactions of a customer.\n\n"
										+ "To mark a customer's debt as paid, click the 'Paid' button.");
					alert.showAndWait();
				break;
			default: alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("HELP");
					alert.setHeaderText("Refund/Replace: where items can be replaced or refunded\n\n");
					alert.setContentText("Search a transaction ID by entering it and clicking the 'Search' button to view list of items bought.\n\n"
										+ "Select an item to be refunded or replaced by selecting a row.\n\n"
										+ "Choose between refund and replace by selecting a button.\n\n"
										+ "Enter the quantity to be refunded or replaced by entering it and clicking the button.");
					alert.showAndWait();
				break;
		}
		
	}
    ////////// to this
    @FXML
    void transactionAction(ActionEvent event) {
    	setTransactionVisible();
    }

    @FXML
    void displayAction(ActionEvent event) {
    	setDisplayVisible();
    }

    @FXML
    void inventoryAction(ActionEvent event) {
    	setInventoryVisible();
    }

    @FXML
    void reportsAction(ActionEvent event) {
    	setReportsVisible();
    }

    @FXML
    void debtListAction(ActionEvent event) {
    	setDebtListVisible();
    }

    @FXML
    void refundReplaceAction(ActionEvent event) {
    	setRefundReplaceVisible();
    }
	///////////////////// from this 
	private void setTransactionVisible() {
		
    	pane = 1;
    	setTransactionSelected();
		transaction.setVisible(true);
		display.setVisible(false);
		inventory.setVisible(false);
		reports.setVisible(false);
		debtList.setVisible(false);
		refundReplace.setVisible(false);
		
	}
	
	private void setDisplayVisible() {
		
    	pane = 2;
    	setDisplaySelected();
		transaction.setVisible(false);
		display.setVisible(true);
		inventory.setVisible(false);
		reports.setVisible(false);
		debtList.setVisible(false);
		refundReplace.setVisible(false);
		
	}

	private void setInventoryVisible() {
		
    	pane = 3;
    	setInventorySelected();
		transaction.setVisible(false);
		display.setVisible(false);
		inventory.setVisible(true);
		reports.setVisible(false);
		debtList.setVisible(false);
		refundReplace.setVisible(false);
		
	}

	private void setReportsVisible() {
		
    	pane = 4;
    	setReportsSelected();
		reportsController.initialize();
		transaction.setVisible(false);
		display.setVisible(false);
		inventory.setVisible(false);
		reports.setVisible(true);
		debtList.setVisible(false);
		refundReplace.setVisible(false);
		
	}

	private void setDebtListVisible() {
		
    	pane = 5;
    	setDebtListSelected();
		transaction.setVisible(false);
		display.setVisible(false);
		inventory.setVisible(false);
		reports.setVisible(false);
		debtList.setVisible(true);
		refundReplace.setVisible(false);
		
	}

	private void setRefundReplaceVisible() {
		
    	pane = 6;
    	setRefindReplaceSelected();
		transaction.setVisible(false);
		display.setVisible(false);
		inventory.setVisible(false);
		reports.setVisible(false);
		debtList.setVisible(false);
		refundReplace.setVisible(true);
		
	}
	
	private void setTransactionSelected() {
		
    	transactionIcon.setImage(transactionIconDark);
    	displayIcon.setImage(displayIconLight);
    	inventoryIcon.setImage(inventoryIconLight);
    	reportsIcon.setImage(reportsIconLight);
    	debtListIcon.setImage(debtListIconLight);
    	refundReplaceIcon.setImage(refundReplaceIconLight);
    	
	}
	
	private void setDisplaySelected() {

    	transactionIcon.setImage(transactionIconLight);
    	displayIcon.setImage(displayIconDark);
    	inventoryIcon.setImage(inventoryIconLight);
    	reportsIcon.setImage(reportsIconLight);
    	debtListIcon.setImage(debtListIconLight);
    	refundReplaceIcon.setImage(refundReplaceIconLight);
    	
	}
	
	private void setInventorySelected() {

    	transactionIcon.setImage(transactionIconLight);
    	displayIcon.setImage(displayIconLight);
    	inventoryIcon.setImage(inventoryIconDark);
    	reportsIcon.setImage(reportsIconLight);
    	debtListIcon.setImage(debtListIconLight);
    	refundReplaceIcon.setImage(refundReplaceIconLight);
    	
	}
	
	private void setReportsSelected() {

    	transactionIcon.setImage(transactionIconLight);
    	displayIcon.setImage(displayIconLight);
    	inventoryIcon.setImage(inventoryIconLight);
    	reportsIcon.setImage(reportsIconDark);
    	debtListIcon.setImage(debtListIconLight);
    	refundReplaceIcon.setImage(refundReplaceIconLight);
    	
	}
	
	private void setDebtListSelected() {

    	transactionIcon.setImage(transactionIconLight);
    	displayIcon.setImage(displayIconLight);
    	inventoryIcon.setImage(inventoryIconLight);
    	reportsIcon.setImage(reportsIconLight);
    	debtListIcon.setImage(debtListIconDark);
    	refundReplaceIcon.setImage(refundReplaceIconLight);
    	
	}
	
	private void setRefindReplaceSelected() {
		
    	transactionIcon.setImage(transactionIconLight);
    	displayIcon.setImage(displayIconLight);
    	inventoryIcon.setImage(inventoryIconLight);
    	reportsIcon.setImage(reportsIconLight);
    	debtListIcon.setImage(debtListIconLight);
    	refundReplaceIcon.setImage(refundReplaceIconDark);
    	
	}
	///////////// to this
}
