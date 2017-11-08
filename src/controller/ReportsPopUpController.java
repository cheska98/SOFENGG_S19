package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.ReportEntry;
import model.ReportsPopUpEntry;

public class ReportsPopUpController implements Initializable {

    @FXML private TableView<ReportsPopUpEntry> reportsPopUpTable;
    @FXML private TableColumn<ReportsPopUpEntry, String> transIDCol;
	@FXML private TableColumn<ReportsPopUpEntry, String> dateCol;
    @FXML private TableColumn<ReportsPopUpEntry, String>  timeCol;
    @FXML private AnchorPane reportsPopUp;
    @FXML private Button okButton;
    
    private ReportsController rc;
    private ObservableList<ReportsPopUpEntry> data;

    
    public void initialize(ReportsController rc) {
    	this.rc = rc;
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
    	transIDCol.setCellValueFactory(
		    new PropertyValueFactory<ReportsPopUpEntry, String>("transID")
		);
		dateCol.setCellValueFactory(
		    new PropertyValueFactory<ReportsPopUpEntry, String>("date")
		    
		);
		timeCol.setCellValueFactory(
		    new PropertyValueFactory<ReportsPopUpEntry, String>("time")
		);
    	
    	data = FXCollections.observableArrayList();
		reportsPopUpTable.setItems(data);
		
		generateReportsPopUpRow("115", "2017-11-08", "12:22:35");
		
	}
    
    private void generateReportsPopUpRow(String transID, String date, String time) {
    	
    	ReportsPopUpEntry row = new ReportsPopUpEntry();
    	row.transID.set(transID);
    	row.date.set(date);
    	row.time.set(time);
    	data.add(row);
    	
    }
    
    @FXML
    void handleOk(ActionEvent event) {
    	rc.closeView();
    }

}
