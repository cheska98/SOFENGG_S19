package controller;

import java.util.Calendar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class SuperMainController {
	
	@FXML private AnchorPane sidebar;
    @FXML private Label label1;
    @FXML private Label nameLabel;
    @FXML private Label currDateLabel;
    @FXML private Button salesButton;
    @FXML private Button displayButton;
    @FXML private Button logoutButton;
    @FXML private Button inventoryButton;
    @FXML private Button debtListButton;
    @FXML private Button reportsButton;
    @FXML private Button refundReplaceButton;
    @FXML private ImageView salesIcon;
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
	
	private Calendar now = Calendar.getInstance();
	private String currDate = null;
	private String month = null;
	private String day = null;
	private String year = Integer.toString(now.get(Calendar.YEAR));
	
	
	@FXML
	private void initialize() {
		
		setCurrDate();
		setTransactionVisible();
		
	}
	
	public void setController(InitPanelController initialController) {
		this.initialController = initialController;
	}
	
	private void setInitialStage() {
		
		System.out.println("init");
		initialController.setInitialStage(initialController);
	
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
	
	@FXML
    void logoutAction(ActionEvent event) {
	
		Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
		alert.setTitle("LOGOUT");
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
		    setInitialStage();
		}
		
    }
    
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
		reportsController.initialize();
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
