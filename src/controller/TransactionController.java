package controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleFloatProperty;
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
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.SaleEntry;

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
    private TableColumn<SaleEntry, Float> costCol;

    @FXML
    private TableColumn<SaleEntry, Float> priceCol;

    @FXML
    private TableColumn<SaleEntry, Boolean> checkColumn;

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
    private Button btnDelete;

    @FXML
    private AnchorPane completePopup;
    
    ObservableList<String> debtCustomer;

    ObservableList<SaleEntry> data;

    SaleEntry se;
    SaleEntry fe;
    
    private static float ucost = 0;
    private static int qty = 1;
    private int changeqty = 1;
    private float changeucost = 0;
    SaleEntry newEntry;
    DecimalFormat f = new DecimalFormat("##.00");
    
    public TransactionController() {
    	
    	se = new SaleEntry();
    	fe = new SaleEntry();
    	
        se.setItemName(new SimpleStringProperty("Test"));
        se.setUcost(new SimpleFloatProperty(35));		
        

        fe.setItemName(new SimpleStringProperty("tt"));
        fe.setUcost(new SimpleFloatProperty(45));	
    	
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

			quantityCol.setCellFactory((TextFieldTableCell.<SaleEntry, Integer>forTableColumn(new IntegerStringConverter())));
			
	        quantityCol.setOnEditCommit(
	                new EventHandler<CellEditEvent<SaleEntry, Integer>>() {
	                    @Override
	                    public void handle(CellEditEvent<SaleEntry, Integer> t) {
	                    	
	                    	try {
	                        ((SaleEntry) t.getTableView().getItems().get(
	                                t.getTablePosition().getRow())
	                                ).setQty(new SimpleIntegerProperty(t.getNewValue().intValue()));
	                    	} catch (Exception e) {
								// TODO: handle exception
	                    		System.out.println("Invalid input!");
							}
	                        changeqty = t.getNewValue().intValue();

	                        data.get(t.getTablePosition().getRow()).price.set(
	    	                        setPrice(changeqty, data.get(t.getTablePosition().getRow()).getUcost(),t.getTablePosition().getRow()));

	                        setTotal();
	                    }
	                }
	                );
			
			priceCol.setCellValueFactory(
			    new PropertyValueFactory<SaleEntry, Float>("price")
			);
			
			costCol.setCellValueFactory(
					new PropertyValueFactory<SaleEntry, Float>("ucost")
			);
			
			costCol.setCellFactory(TextFieldTableCell.<SaleEntry, Float>forTableColumn(new FloatStringConverter() {
		        @Override
		        public Float fromString(String value) {
		            try {
		                return super.fromString(value);
		            } catch(NumberFormatException e) {
		                return Float.NaN; // An abnormal value
		            }
		        }
		    }));
			
	        costCol.setOnEditCommit(
	                new EventHandler<CellEditEvent<SaleEntry, Float>>() {
	                    @Override
	                    public void handle(CellEditEvent<SaleEntry, Float> t) {
	                    	
	                    	if(t.getNewValue().isNaN()) {	
	                    		t.getRowValue().setUcost(new SimpleFloatProperty(t.getOldValue()));
	                    	}
	                    	
	                    	else {
	                    		
		                        ((SaleEntry) t.getTableView().getItems().get(
		                                t.getTablePosition().getRow())
		                                ).setUcost(new SimpleFloatProperty(t.getNewValue().floatValue()));

		                        changeucost = t.getNewValue().floatValue();
		                        
		                        data.get(t.getTablePosition().getRow()).price.set(
		    	                        setPrice(data.get(t.getTablePosition().getRow()).getQty(), changeucost,t.getTablePosition().getRow()));
	                    	}

	                        setTotal();

	                    }
	                }
	                );
	        
	        checkColumn.setCellFactory(
	                CheckBoxTableCell.forTableColumn(checkColumn)
	            );
	        
	        checkColumn.setCellValueFactory(
	                    //new PropertyValueFactory<SaleEntry, Boolean>("delete")
	        		cellData -> cellData.getValue().selectedProperty()
	            );
	        
	        data = FXCollections.observableArrayList(); // create the data

		    salesTable.setItems(data); 
		    
		    debtCustomer = FXCollections.observableArrayList();
	    	
	    	debtCustomer.add("Takashi Shirogane");
	    	debtCustomer.add("Keith Kogane");
	    	
	    	
	    	debtListCB.setItems(debtCustomer);
	    	

	    	debtListCB.getItems().add("New Customer in Debt");
	    	
	    	completePopup.setVisible(false);
	    	
	    	totalAmount.setText("00.00");
	    	
	}

	@FXML
    void handlecomplete(ActionEvent event) {
    	

		completePopup.setVisible(true);

    }
	
    @FXML
    void handleDelete(ActionEvent event) {
    	
    	  ObservableList<SaleEntry> dataListToRemove = FXCollections.observableArrayList();
          for (SaleEntry bean : data) {
              if (bean.getSelected()) {
                  dataListToRemove.add(bean);
              }
          }
          
          data.removeAll(dataListToRemove);
          setTotal();

    }
	

    @FXML
    void handleDebt(ActionEvent event) {
    	
    	String selectedPerson = debtListCB.getSelectionModel().getSelectedItem();
	    System.out.println("ComboBox Action (selected: " + selectedPerson.toString() + ")");

    }

    @FXML
    void handleOK(ActionEvent event) {
    	
    	completePopup.setVisible(false);
    	
    	float change = Float.parseFloat((paidTF.getText())) - Float.parseFloat(totalAmount.getText());
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Customer's Change");
    	alert.setHeaderText(null);
    	alert.setContentText("Change: ₱ "+ f.format(change));

    	alert.showAndWait();
    	
    	paidTF.clear();
    	totalAmount.clear();
    	salesTable.getItems().clear();

    }
    
    @FXML
    void handleCancel(ActionEvent event) {
    	

    	completePopup.setVisible(false);
    	salesTable.getItems().clear();

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
    	
    	newEntry = new SaleEntry();
    	newEntry.itemName.set(itemText.getText());
    	newEntry.qty.set(1);
    	
    	generateSaleEntry(newEntry);
    	qty = newEntry.getQty();
    	ucost = newEntry.getUcost();
    	
    	setTotal();
    	
    	itemText.clear();
    }

	
	private void generateSaleEntry(SaleEntry entry) {
		
//		SaleEntry entry = new SaleEntry();
//	    entry.itemName.set(item);
//	    entry.qty.set(qty);
//	    entry.ucost.set(ucost);
	    data.add(entry);
	    int i = data.indexOf(entry);
	    entry.price.set((setPrice(qty, ucost, i)));
	    
	}
	
	private float setPrice(int newqty, float cost, int pos) {
		
		float price = 0;
		
		price = cost * newqty ;
    	data.get(pos).setPrice(new SimpleFloatProperty(price));
    	return price;
	
	}
	
	private void setTotal() {

	    float totalAmt = 0;
	    
		for(int i = 0; i < data.size(); i++) {
			
			totalAmt += data.get(i).getPrice();
		}
		
    	totalAmount.setText(f.format(totalAmt));
	}
	
	

}
