package controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

public class TransactionController implements Initializable {

    @FXML
    private AnchorPane sales;

    @FXML
    private TableView<SaleEntry> salesTable;

    @FXML
    private TableColumn<SaleEntry, String> itemCol;

    @FXML
    private TableColumn<SaleEntry, Integer> quantityCol;

    @FXML
    private TableColumn<SaleEntry, String> costCol;

    @FXML
    private TableColumn<SaleEntry, Integer> priceCol;

    @FXML
    private TextField itemText;

    @FXML
    private TextField priceText;

    @FXML
    private TextField qtyText;

    @FXML
    private Button btnCart;
    
    @FXML
    private Button btnComplete;

    @FXML
    private Label totalAmount;
    
    ObservableList<SaleEntry> data;
    private float amtPaid;
    private float totalAmt = 0;

    @FXML
    void handlecomplete(ActionEvent event) {
    	
    	TextInputDialog dialog = new TextInputDialog();
    	dialog.setTitle("Complete Transaction");
    	dialog.setContentText("Total Amount Paid: ");
    	
    	Optional<String> result = dialog.showAndWait();
    	
    	result.ifPresent(amount -> amtPaid = Integer.parseInt(amount)
    	);
    	
    	//System.out.println(amtPaid);
    	
    }

    @FXML
    void handleCart(ActionEvent event) {
    	
    	String item = itemText.getText();
    	float price = Integer.parseInt(priceText.getText());
    	int qty = Integer.parseInt(qtyText.getText()); 
    	String ucost = "";
    	
    	DecimalFormat f = new DecimalFormat("##.00");
    	totalAmt += price * qty;
    	f.format(totalAmt);	
    	totalAmount.setText(f.format(totalAmt));
    	
    	generateSaleEntry(item, price * qty, qty, ucost);
    	
    	itemText.clear();
    	priceText.clear();
    	qtyText.clear();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
			itemCol.setCellValueFactory(
			    new PropertyValueFactory<SaleEntry, String>("itemName")
			);
			quantityCol.setCellValueFactory(
			    new PropertyValueFactory<SaleEntry, Integer>("qty")
			    
			);
			priceCol.setCellValueFactory(
			    new PropertyValueFactory<SaleEntry, Integer>("price")
			);
			
			costCol.setCellValueFactory(
					new PropertyValueFactory<SaleEntry, String>("ucost")
			);
			
			costCol.setCellFactory(TextFieldTableCell.<SaleEntry>forTableColumn());
	        costCol.setOnEditCommit(
	                new EventHandler<CellEditEvent<SaleEntry, String>>() {
	                    @Override
	                    public void handle(CellEditEvent<SaleEntry, String> t) {
	                        ((SaleEntry) t.getTableView().getItems().get(
	                                t.getTablePosition().getRow())
	                                ).setUcost(new SimpleStringProperty(t.getNewValue()));
	                    }
	                }
	           );
			
			data = FXCollections.observableArrayList(); // create the data
			salesTable.setItems(data); //
		
	}
	
	
	private void generateSaleEntry(String item, float price, int qty, String ucost) {
		
		SaleEntry entry = new SaleEntry();
	    entry.itemName.set(item);
	    entry.price.set(price);
	    entry.qty.set(qty);
	    //entry.ucost.set(ucost);
	    data.add(entry);
	}
	
	

}
