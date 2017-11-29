package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.entries.TopItemEntry;

public class TopItemsPopUpController implements Initializable {

    @FXML private AnchorPane topItemsPopUp;
    @FXML private ScrollPane scrollPane;
    @FXML private Label label1;
    @FXML private TableView<TopItemEntry> topItemsTable;
    @FXML private TableColumn<TopItemEntry, String> itemCol;
    @FXML private TableColumn<TopItemEntry, String> quantityCol;
    @FXML private Button okButton;
    
    private ReportsController rc;
    private ObservableList<TopItemEntry> data;
    
    
    public void initialize(ReportsController rc) {
    	this.rc = rc;
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
    	itemCol.setCellValueFactory(
		    new PropertyValueFactory<TopItemEntry, String>("item")
		);
    	
		quantityCol.setCellValueFactory(
		    new PropertyValueFactory<TopItemEntry, String>("quantity")
	
		);
    	
    	data = FXCollections.observableArrayList();
		topItemsTable.setItems(data);
		
		generateTopItemRow("Hammer", 6);
		
	}
    
    private void generateTopItemRow(String item, int quantity) {
    	
    	TopItemEntry row = new TopItemEntry();
    	row.item.set(item);
    	row.quantity.set(quantity);
    	data.add(row);
    	
    }
    
    @FXML
    void handleOk(ActionEvent event) {
    	rc.closeView();
    }
    
}
