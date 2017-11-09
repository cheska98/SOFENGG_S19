package controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.IntegerStringConverter;
import model.SaleEntry;
import view.AmountPaidPopup;

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
    private Button btnCart;
    
    @FXML
    private Button btnComplete;

    @FXML
    private TextField totalAmount;
    
    @FXML
    private TextField paidTF;

    @FXML
    private Button btnOK;

    @FXML
    private ComboBox<String> debtListCB;

    @FXML
    private Button btnCancel;

    @FXML
    private AnchorPane completePopup;
    
    
    ObservableList<String> debtCustomer;

    
    SaleEntry se;
    SaleEntry fe;
    
    private float totalAmt = 0;
    private String item;
    private String ucost;
    private int qty = 0;
    private float price = 0;
    DecimalFormat f = new DecimalFormat("##.00");
    
    private AmountPaidPopup tmp;
    
    public TransactionController() {
    	
    	tmp = new AmountPaidPopup();
    	se = new SaleEntry();
    	fe = new SaleEntry();
    	
        se.setItemName(new SimpleStringProperty("Test"));
        se.setUcost(new SimpleStringProperty("35"));		
        

        fe.setItemName(new SimpleStringProperty("tt"));
        fe.setUcost(new SimpleStringProperty("45"));	
    	
    }
    
    ObservableList<SaleEntry> data;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
			itemCol.setCellValueFactory(
			    new PropertyValueFactory<SaleEntry, String>("itemName")
			);
			quantityCol.setCellValueFactory(
			    new PropertyValueFactory<SaleEntry, Integer>("qty")
			    
			);

			quantityCol.setCellFactory((TextFieldTableCell.<SaleEntry, Integer>forTableColumn(new IntegerStringConverter())));
			
	        quantityCol.setOnEditCommit(
	                new EventHandler<CellEditEvent<SaleEntry, Integer>>() {
	                    @Override
	                    public void handle(CellEditEvent<SaleEntry, Integer> t) {
	                    	
	                        ((SaleEntry) t.getTableView().getItems().get(
	                                t.getTablePosition().getRow())
	                                ).setQty(new SimpleIntegerProperty(t.getNewValue().intValue()));
	                        
	                        //setTotalAmount(t.getNewValue().intValue(), t.getTablePosition().getRow());
	                        
	                        
	                        totalAmt += Float.parseFloat(ucost) * (t.getNewValue().intValue() - 1);

	                    	f.format(totalAmt);
	                    	totalAmount.setText(f.format(totalAmt));
	                    	
	                    }
	                }
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

		    salesTable.setItems(data); 
		    
		    debtCustomer = FXCollections.observableArrayList();
	    	
	    	debtCustomer.add("Takashi Shirogane");
	    	debtCustomer.add("Keith Kogane");
	    	
	    	
	    	debtListCB.setItems(debtCustomer);
	    	

	    	debtListCB.getItems().add("New Customer in Debt");
	    	
	    	completePopup.setVisible(false);
	    	
	    	
	}
	
	 @FXML
	    void handleDelete(MouseEvent event) {
		 
		 //System.out.println("Clicked on " + (salesTable.getSelectionModel().getSelectedCells().get(0)).getColumn());
		 	if(event.getClickCount() == 2) {
			    Alert alert = new Alert(AlertType.CONFIRMATION);
			    alert.setTitle("Remove Item");
			    alert.setContentText("Are you sure you want to remove this item?");
	
			    Optional<ButtonType> result = alert.showAndWait();
			    if (result.get() == ButtonType.OK){
			    	SaleEntry selectedItem = salesTable.getSelectionModel().getSelectedItem();
				    salesTable.getItems().remove(selectedItem);
				    
			    } else {
			        // ... user chose CANCEL or closed the dialog
			    }
		 	}

	    }
	
	@FXML
    void handlecomplete(ActionEvent event) {
    	

		completePopup.setVisible(true);
    	salesTable.getItems().clear();

    	totalAmount.setText("00.00");
    	
    }
	

    @FXML
    void handleDebt(ActionEvent event) {
    	
    	String selectedPerson = debtListCB.getSelectionModel().getSelectedItem();
	    System.out.println("ComboBox Action (selected: " + selectedPerson.toString() + ")");

    }

    @FXML
    void handleOK(ActionEvent event) {
    	
    	completePopup.setVisible(false);

    }
    
    @FXML
    void handleCancel(ActionEvent event) {
    	

//    	Stage stage = (Stage) btnCancel.getScene().getWindow();
//    	stage.close();

    	completePopup.setVisible(false);

    }
    
    @FXML
    void handleCart(ActionEvent event) {
    	
//    	item = se.getItemName();
//    	qty = 1;
//    	ucost = se.getUcost();
//    	totalAmt += Float.parseFloat(ucost) * qty;
//    	f.format(totalAmt);
//    	totalAmount.setText(f.format(totalAmt));
//    	
//    	generateSaleEntry(item, Float.parseFloat(ucost) * qty, qty, ucost);
//    	
//    	item = fe.getItemName();
//    	qty = 1;
//    	ucost = fe.getUcost();
//    	totalAmt += Float.parseFloat(ucost) * qty;
//    	f.format(totalAmt);
//    	totalAmount.setText(f.format(totalAmt));
//    	
//    	generateSaleEntry(item, Float.parseFloat(ucost) * qty, qty, ucost);
    	
    	SaleEntry newEntry = new SaleEntry();
    	newEntry.itemName.set(itemText.getText());
    	newEntry.qty.set(1);
    	
    	generateSaleEntry(newEntry);
    	itemText.clear();
    }

	
	private void generateSaleEntry(SaleEntry entry) {
		
//		SaleEntry entry = new SaleEntry();
//	    entry.itemName.set(item);
//	    entry.price.set(price);
//	    entry.qty.set(qty);
//	    entry.ucost.set(ucost);
	    data.add(entry);
	    
	}
	
	private void setTotalAmount (int newqty, int row) {
		
//		totalAmt += Float.parseFloat(ucost) * ;
//    	f.format(totalAmt);
//    	totalAmount.setText(f.format(totalAmt));
		
	
	}
	
	

}
