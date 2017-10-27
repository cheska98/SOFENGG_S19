package controller;

import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
<<<<<<< HEAD
import javafx.event.ActionEvent;
=======
>>>>>>> 42a4e614c3972c5e9ca3720e31a279414f3a165d
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ReportsController {

	@FXML private AnchorPane reportsPane;
	@FXML private Label rpLbl1;
	@FXML private DatePicker dp;
	@FXML private Label rpLbl2;
	@FXML private ComboBox<String> cb;
	private String date = null;
	private String month = null;
	
<<<<<<< HEAD
	public ReportsController() {
		
	}
	
	@FXML
	void initialize() {
		ObservableList<String> months = 
        	    FXCollections.observableArrayList(
        	        "January",
        	        "February",
        	        "March",
        	        "April",
        	        "May",
        	        "June",
        	        "July",
        	        "August",
        	        "September",
        	        "October",
        	        "November",
        	        "December"
        	    );
    	cb.getItems().addAll(months);
    	
	}
	
	@FXML
	void handleDate(ActionEvent event) {
		
		date = dp.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
		//pass date to reports
		
	}

	
    @FXML
    void handleMonth(ActionEvent event) {
    
    	month = cb.getValue();
    	
    }
=======
	@FXML
	public void initialize() {
		
		 ObservableList<String> months = 
	        	    FXCollections.observableArrayList(
	        	        "January",
	        	        "February",
	        	        "March",
	        	        "April",
	        	        "May",
	        	        "June",
	        	        "July",
	        	        "August",
	        	        "September",
	        	        "October",
	        	        "November",
	        	        "December"
	        	    );
	    cb = new ComboBox<String>(months);
	    	
	    dp = new DatePicker();
	    dp.setOnAction(event -> {
	    	date = dp.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
		});
	    
	    cb.setOnAction(event -> {
	    	month = cb.getValue();
		});
	}
>>>>>>> 42a4e614c3972c5e9ca3720e31a279414f3a165d
	
}
