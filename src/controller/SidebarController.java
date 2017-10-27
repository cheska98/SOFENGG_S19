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

public class SidebarController {
	
	@FXML private AnchorPane sidebarPane;
	@FXML private Label sbLblCurrDate;
	@FXML private Label sbLbl1;
	@FXML private Label sbLblName;
	@FXML private Button sbBtnLogout;
	@FXML private Button sbBtnSales;
	@FXML private Button sbBtnDisplay;
	@FXML private Button sbBtnInventory;
	@FXML private Button sbBtnReports;
	@FXML private Button sbBtnDebtList;
	@FXML private ImageView salesIcon;
	@FXML private ImageView displayIcon;
	@FXML private ImageView inventoryIcon;
	@FXML private ImageView reportsIcon;
	@FXML private ImageView debtListIcon;

	public SidebarController() {
		
	}
	
	@FXML
	void initialize() {
		
		//get first name
		//set sbLblName to first name
		
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
		
		//System.out.println(currDate);
		sbLblCurrDate.setText(currDate);
		
	}
	
 	@FXML
    void handleLogout(ActionEvent event) {
 		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		String s = "Are you sure?";
		alert.setContentText(s);
		Optional<ButtonType> result = alert.showAndWait();
		if ((result.isPresent()) && (result.get() == ButtonType.YES)) {
		    //go back to initPanel
		}
		
    }

    @FXML
    void handleSales(ActionEvent event) {

    }

    @FXML
    void handleDisplay(ActionEvent event) {

    }

    @FXML
    void handleInventory(ActionEvent event) {

    }

    @FXML
    void handleReports(ActionEvent event) {

    }

    @FXML
    void handleDebtList(ActionEvent event) {

    }
}
