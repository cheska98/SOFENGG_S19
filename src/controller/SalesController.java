package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class SalesController implements Initializable {

    @FXML
    private AnchorPane sales;

    @FXML
    private TableView<SaleEntry> salesTable;

    @FXML
    private TableColumn<SaleEntry, String> itemCol;

    @FXML
    private TableColumn<SaleEntry, Integer> quantityCol;

    @FXML
    private TableColumn<?, ?> costCol;

    @FXML
    private TableColumn<SaleEntry, String> priceCol;

    @FXML
    private TextField itemText;

    @FXML
    private TextField priceText;

    @FXML
    private TextField qtyText;

    @FXML
    private Button btnCart;
    
    ObservableList<SaleEntry> data;

    @FXML
    void handleCart(ActionEvent event) {
    	
    	String item = itemText.getText();
    	String price = priceText.getText();
    	int qty = Integer.parseInt(qtyText.getText()); 
    	
    	generateSaleEntry(item, price, qty);
 

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
			itemCol.setCellValueFactory(
			    new PropertyValueFactory<SaleEntry,String>("itemName")
			);
			quantityCol.setCellValueFactory(
			    new PropertyValueFactory<SaleEntry,Integer>("qty")
			    
			);
			priceCol.setCellValueFactory(
			    new PropertyValueFactory<SaleEntry,String>("price")
			);
			
			data = FXCollections.observableArrayList(); // create the data
			salesTable.setItems(data); //
		
	}
	
	private void generateSaleEntry(String item, String price, int qty) {
		
		SaleEntry entry = new SaleEntry();
	    entry.itemName.set(item);
	    entry.price.set(price);
	    entry.qty.set(qty);
	    data.add(entry);
	}

}
