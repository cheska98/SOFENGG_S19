package controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TextField;
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
    @FXML
    private AnchorPane rrpane;
    @FXML
    private TextField refundreplacetext;
    @FXML
    private Button refundreplacebtn;

    ObservableList<RefundReplaceEntry> data;
    RefundReplaceEntry entry;
    RefundReplaceEntry enter;
    static RefundReplaceEntry selected;
    static int REFUND;
    static int pos;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		itemColumn.setCellValueFactory(
			    new PropertyValueFactory<RefundReplaceEntry, String>("itemName")
			);
		
		quantityColumn.setCellValueFactory(
			    new PropertyValueFactory<RefundReplaceEntry, Integer>("qty")
			);

		transColumn.setCellValueFactory(
			    new PropertyValueFactory<RefundReplaceEntry, Integer>("id")
			);
		
		dateColumn.setCellValueFactory(
				    new PropertyValueFactory<RefundReplaceEntry, String>("date")
					);
		
		data = FXCollections.observableArrayList();

	    tableRR.setItems(data);
	    
	    rrpane.setVisible(false);
	    
	    selected = new RefundReplaceEntry();
		
	    //for testing
    	entry = new RefundReplaceEntry();
    	
    	entry.id.set(1);
    	entry.date.set("October");
    	entry.itemName.set("paimt");
    	entry.qty.set(20);
    	
    	rrGenerator(entry);

    	enter = new RefundReplaceEntry();
    	
    	enter.id.set(2);
    	enter.date.set("Nov");
    	enter.itemName.set("Pitou");
    	enter.qty.set(30);
    	rrGenerator(enter);
		
	}
	

    @FXML
    void handleRefundReplace(ActionEvent event) {
    	
    	int i = Integer.parseInt(refundreplacetext.getText());
    	System.out.println(selected.getQty());
    	
    	if (i > selected.getQty() || i < 1) {
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Invalid Quantity Entered");
    		String s = "The quantity you have entered is invalid";
    		alert.setHeaderText(null);
    		alert.setContentText(s);
    		alert.showAndWait();
    		
    	} else {
    		
    		if(REFUND == 1) {
    			
    			System.out.println("helloooo");
    			int y = selected.getQty() - i;
    			System.out.println(y);
    			data.get(pos).setQty(new SimpleIntegerProperty(y));
    		}
    		rrpane.setVisible(false);
    	}
    	
    	
    	System.out.println(i);
    	refundreplacetext.clear();
    	
    }

    @FXML
    void handleOK(ActionEvent event) {
    	
    	RadioButton selectedRadioButton = (RadioButton) rrGroup.getSelectedToggle();
    	String togleValue = selectedRadioButton.getText();
    	
    	if(togleValue.equalsIgnoreCase("Refund")) {
    		
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("REFUND");
    		String s = "Are you sure?";
    		alert.setHeaderText(null);
    		alert.setContentText(s);
    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK) {
    			System.out.println("Refund");
    			
    			REFUND = 1;
    			selected = tableRR.getSelectionModel().getSelectedItem();
    			pos = tableRR.getSelectionModel().getSelectedIndex();
    			System.out.println(pos);
    			System.out.println(selected.getItemName());
    			
    			refundreplacebtn.setText("Refund");
    			rrpane.setVisible(true);
    			
    		}
    		
    		
    	}
    	
    	else if(togleValue.equalsIgnoreCase("Replace")) {
    		
    		
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("REPLACE");
    		String s = "Are you sure?";
    		alert.setHeaderText(null);
    		alert.setContentText(s);
    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK) {
    			System.out.println("Replace");
    			
    			REFUND = 0;
    			
    			selected = tableRR.getSelectionModel().getSelectedItem();
    			System.out.println(selected.getItemName());
    			
    			refundreplacebtn.setText("Replace");
    			rrpane.setVisible(true);
    		}
    		
    	}
    	
    		rrGroup.selectToggle(null);
    }
    
    
    private void rrGenerator(RefundReplaceEntry entry) {
    	
    	data.add(entry);
    	
    	
    }

}
