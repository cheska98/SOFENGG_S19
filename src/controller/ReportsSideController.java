package controller;

import java.util.Optional;

<<<<<<< HEAD
import javafx.event.ActionEvent;
=======
>>>>>>> 42a4e614c3972c5e9ca3720e31a279414f3a165d
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
	
<<<<<<< HEAD
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
	
=======
	@FXML
	public void initialize() {
		
		//get input from dp or cb
		
		lv = new ListView<String>();
		lv.getItems().add("<Date and Time>");
		lv.getItems().add("<List of Items Bought>");
		lv.getItems().add("Top Items Bought");
		
		rdBtnDownload = new Button();
		rdBtnDownload.setOnAction(event -> {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Download");
			String s = "Download daily report on 10/18/2017?";
			alert.setContentText(s);
			Optional<ButtonType> result = alert.showAndWait();
			if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
			    //download excel file
			}

		});
	}
	
>>>>>>> 42a4e614c3972c5e9ca3720e31a279414f3a165d
}
