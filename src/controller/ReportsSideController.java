package controller;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class ReportsSideController {

	@FXML private AnchorPane reportsSidePane;
	@FXML private Label rdLbl1;
	@FXML private Label rdLblDate;
	@FXML private ListView<String> lv;
	@FXML private Button rdBtnDownload;
	
	public ReportsSideController() {
		
	}
	
	@FXML
	void initialize() {
		
		//get input from dp or cb
		
		lv.getItems().add("<Date and Time>");
		lv.getItems().add("<List of Items Bought>");
		lv.getItems().add("<Top Items Bought>");
		
	}
	
	@FXML
    void handleDownload(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Download");
		String s = "Download daily report on 10/18/2017?";
		alert.setContentText(s);
		Optional<ButtonType> result = alert.showAndWait();
		if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
		    //download excel file
		}
    }
	
}
