package controller;

import java.util.Calendar;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class SidebarController {

    @FXML private AnchorPane sidebarPane;
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

    public void initialize() {
		
    	//get username
    	
		//set nameLabel to username
		
		Calendar now = Calendar.getInstance();
		String currDate = null;
		String month = null;
		String day = null;
		String year = Integer.toString(now.get(Calendar.YEAR));
		
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
		
    }
    
    public void initializeButtons(SuperMainController smc) {
    	
    	logoutButton.setOnAction(event -> {
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("Logout");
    		String s = "Are you sure?";
    		alert.setContentText(s);
    		Optional<ButtonType> result = alert.showAndWait();
    		if ((result.isPresent()) && (result.get() == ButtonType.YES)) {
    		    //go back to initPanel
    		}
    	});
    	
    	salesButton.setOnAction(event -> {
    		smc.setTransactionVisible();
    	});

    	displayButton.setOnAction(event -> {
    		smc.setDisplayVisible();
    	});

    	inventoryButton.setOnAction(event -> {
    		smc.setInventoryVisible();
    	});

    	reportsButton.setOnAction(event -> {
    		smc.setReportsVisible();
    	});

    	debtListButton.setOnAction(event -> {
    		smc.setDebtListVisible();
    	});

    	refundReplaceButton.setOnAction(event -> {
    		smc.setRefundReplaceVisible();
    	});
    	
    }

}
