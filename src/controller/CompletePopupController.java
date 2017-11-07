package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CompletePopupController implements Initializable {

    @FXML
    private TextField paidTF;

    @FXML
    private Button btnOK;

    @FXML
    private ComboBox<String> debtListCB;
    
    
    ObservableList<String> debtCustomer;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
    	
    	debtCustomer = FXCollections.observableArrayList();
    	
    	debtCustomer.add("Takashi Shirogane");
    	debtCustomer.add("Keith Kogane");
    	
    	
    	debtListCB.setItems(debtCustomer);
    	

    	debtListCB.getItems().add("New Customer in Debt");
	}

    @FXML
    void handleDebt(ActionEvent event) {
    	
    	String selectedPerson = debtListCB.getSelectionModel().getSelectedItem();
	    System.out.println("ComboBox Action (selected: " + selectedPerson.toString() + ")");

    }

    @FXML
    void handleOK(ActionEvent event) {
    	
    	Stage stage = (Stage) btnOK.getScene().getWindow();
    	stage.close();

    }

}
