package controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import model.RefundReplaceEntry;
import model.SaleEntry;

public class RefundReplaceController implements Initializable{

    @FXML
    private AnchorPane refundReplace;

    @FXML
    private RadioButton replaceRB;

    @FXML
    private ToggleGroup rrGroup;

    @FXML
    private RadioButton refundRB;

    @FXML
    private Button okButton;

    @FXML
    private TableView<RefundReplaceEntry> tableRR;

    @FXML
    private TableColumn<RefundReplaceEntry, String> itemColumn;

    @FXML
    private TableColumn<RefundReplaceEntry, Integer> quantityColumn;
    
    @FXML
    private TableColumn<RefundReplaceEntry, Integer> transColumn;

    @FXML
    private TableColumn<RefundReplaceEntry, String> dateColumn;
    

    ObservableList<RefundReplaceEntry> data;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		itemColumn.setCellValueFactory(
			    new PropertyValueFactory<RefundReplaceEntry, String>("itemName")
			);
		
		dateColumn.setCellValueFactory(
				    new PropertyValueFactory<RefundReplaceEntry, String>("date")
					);
		
		transColumn.setCellValueFactory(
			    new PropertyValueFactory<RefundReplaceEntry, Integer>("price")
			);
		
		quantityColumn.setCellValueFactory(
			    new PropertyValueFactory<RefundReplaceEntry, Integer>("price")
			);
			
		
		data = FXCollections.observableArrayList(); // create the data

	    tableRR.setItems(data);
		
		
	}

    @FXML
    void handleOK(ActionEvent event) {
    	
    	RadioButton selectedRadioButton = (RadioButton) rrGroup.getSelectedToggle();
    	String togleValue = selectedRadioButton.getText();
    	
    	if(togleValue.equalsIgnoreCase("Refund")) {
    		
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("REFUND");
    		String s = "Are you sure?";
    		alert.setContentText(s);
    		Optional<ButtonType> result = alert.showAndWait();
    		if ((result.isPresent()) && (result.get() == ButtonType.YES)) {
    			System.out.println("Refund");
    		}
    		
    		
    	}
    	
    	else if(togleValue.equalsIgnoreCase("Replace")) {
    		
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("REPLACE");
    		String s = "Are you sure?";
    		alert.setContentText(s);
    		Optional<ButtonType> result = alert.showAndWait();
    		if ((result.isPresent()) && (result.get() == ButtonType.YES)) {
    			System.out.println("Replace");
    		}
    		
    	}
    	
    		//rrGroup.selectToggle(null);
    }
    
    private void rrGenerator(String date, String item, int id, int qty) {
    	
    	RefundReplaceEntry entry = new RefundReplaceEntry();
    	entry.date.set(date);
    	entry.itemName.set(item);
    	entry.id.set(id);
    	entry.qty.set(qty);
    	
    	data.add(entry);
    	
    	
    }

}





//package controller;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Button;
//import javafx.scene.control.RadioButton;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.ToggleGroup;
//import javafx.scene.layout.AnchorPane;
//
//public class RefundReplaceController implements Initializable {
//
//    @FXML private AnchorPane refundReplace;
//    @FXML private TableView<?> tableRR;
//    @FXML private TableColumn<?, ?> itemColumn;
//    @FXML private TableColumn<?, ?> quantityColumn;
//    @FXML private RadioButton refundRB;
//    @FXML private RadioButton replaceRB;
//    @FXML private Button okButton;
//    private ToggleGroup refundOrReplace;
//
//    
//    @FXML
//    void handleOK(ActionEvent event) {
//    	
//    	refundOrReplace.selectToggle(refundRB);
//
//    }
//
//	@Override
//	public void initialize(URL location, ResourceBundle resources) {
//		// TODO Auto-generated method stub
//    	refundOrReplace = new ToggleGroup();
//    	refundRB.setToggleGroup(refundOrReplace);
//    	replaceRB.setToggleGroup(refundOrReplace);
//    	refundOrReplace.selectToggle(refundRB);
//		
//	}
//
//}
