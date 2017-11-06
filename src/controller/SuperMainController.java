package controller;

import java.util.Calendar;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
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
	
	@FXML TransactionController transactionCtr;
	@FXML DisplayController displayCtr;
	@FXML ReportsController reportsCtr = new ReportsController();
	@FXML DebtListController debtListCtr;
	@FXML RefundReplaceController refundReplaceCtr;
	
	Calendar now = Calendar.getInstance();
	String currDate = null;
	String month = null;
	String day = null;
	String year = Integer.toString(now.get(Calendar.YEAR));
	
	
	@FXML
	public void initialize() {
		
		setCurrDate();
		setTransactionVisible();
		
	}
	
	public void setUsername(String username) {
		
		nameLabel.setText(username);
		
	}
	
	public void setCurrDate() {
		
		if (now.get(Calendar.MONTH)+1 < 10)
			month = 0 + Integer.toString(now.get(Calendar.MONTH)+1);
		else
			month = Integer.toString(now.get(Calendar.MONTH)+1);
		
		if (now.get(Calendar.DATE) < 10)
			day = 0 + Integer.toString(now.get(Calendar.DATE));
		else
			day = Integer.toString(now.get(Calendar.DATE));
		
		currDate = month + "/" + day + "/" + year;
		
		currDateLabel.setText(currDate);
		
		reportsCtr.setCurrDate(currDate);
		
	}
	
	@FXML
    void logoutAction(ActionEvent event) {
		
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("LOGOUT");
		String s = "Are you sure?";
		alert.setContentText(s);
		Optional<ButtonType> result = alert.showAndWait();
		if ((result.isPresent()) && (result.get() == ButtonType.YES)) {
		    //go back to initPanel
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
