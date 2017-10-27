package controller;

import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ReportsController {

    @FXML
    private TableColumn<?, ?> purchaseCol;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TableView<?> reportsTable;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<String> monthCB;
    @FXML
    private TableColumn<?, ?> dateTimeCol;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label dateMonthLabel;
    @FXML
    private AnchorPane reportsPane;
    @FXML
    private Label reportLabel;
    @FXML
    private Button viewButton;
    @FXML
    private Button downloadButton;
    @FXML
    private TableColumn<?, ?> transIDCol;
	private String date = null;
	private String month = null;
    
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
    	monthCB.getItems().addAll(months);
    	
	}
    
    @FXML
    void handleDate(ActionEvent event) {

    	date = datePicker.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
		//pass date to reports
    	
    }

    @FXML
    void handleMonth(ActionEvent event) {
    	
    	month = monthCB.getValue();
    
    }

    @FXML
    void handleView(ActionEvent event) {

    }

    @FXML
    void handleDownload(ActionEvent event) {

    }
}


