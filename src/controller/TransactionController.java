package controller;

import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
import model.Model;
import model.entity.Item;
import model.entity.TransactionItem;
import model.entries.SaleEntry;
import model.service.ItemService;
import org.controlsfx.control.textfield.TextFields;

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
    private static float ucost = 0;
    private static int qty = 1;
    private int changeqty = 1;
    private float changeucost = 0;
    SaleEntry newEntry;
    DecimalFormat f = new DecimalFormat("##.00");
    private static Model model; 
    
    public TransactionController() {
    	System.out.println("2");
        //se.setItemName(new SimpleStringProperty("Test"));
        //se.setUcost(new SimpleFloatProperty(35));		
        

        //fe.setItemName(new SimpleStringProperty("tt"));
        //fe.setUcost(new SimpleFloatProperty(45));	
    	
    }
    public void setModel(Model model) { 
    	this.model = model; 
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
	        
	        data = FXCollections.observableArrayList(); // create the data Insert DB here
		    salesTable.setItems(data); 
		    debtCustomer = FXCollections.observableArrayList();
	    	debtCustomer.add("Takashi Shirogane");
	    	debtCustomer.add("Keith Kogane");
	    	debtListCB.setItems(debtCustomer);
	    	debtListCB.getItems().add("New Customer in Debt");
	    	completePopup.setVisible(false);
	    	totalAmount.setText("00.00");

	    	TextFields.bindAutoCompletion(itemText, "hello", "poop"); //this is will do the auto-complete search
	    	
	}

	@FXML
    void handlecomplete(ActionEvent event) {
    	
		if(data.isEmpty()) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Cannot Complete Action!");
	    	alert.setHeaderText(null);
	    	alert.setContentText("No items added to transaction!");

	    	alert.showAndWait();
			
		} else {
			
		completePopup.setVisible(true);
		}


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
	    
	    if(selectedPerson.equalsIgnoreCase("New Customer in Debt")) {
	    	
	    } else {
	    	
	    	
	    }
	    

    }

    @FXML
    void handleOK(ActionEvent event) throws SQLException {
    	
    	completePopup.setVisible(false);
    	
    	
    	float change = Float.parseFloat((paidTF.getText())) - Float.parseFloat(totalAmount.getText());    	
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Customer's Change");
    	alert.setHeaderText(null);
    	alert.setContentText("Change: ₱ "+ f.format(change));

    	alert.showAndWait();
    	ArrayList<TransactionItem> tranItems = convertSalesTable(salesTable);
    	model.newTransaction(tranItems, convertPaid());
    	paidTF.clear();
    	totalAmount.clear();
    	salesTable.getItems().clear();
    }
    
    /*Pangit code ko sorry*/
    private ArrayList<TransactionItem> convertSalesTable(TableView<SaleEntry> salesTable) throws SQLException{
    	ArrayList<TransactionItem> tranItems = new ArrayList<>();
    	for(int i = 0; i < salesTable.getItems().size(); i++){
    		int transID = -1;
    		int itemID = model.findItemByDesc(salesTable.getItems().get(i).getItemName()).getId();
    		float unitPrice = salesTable.getItems().get(i).getUcost();
    		int quantity = salesTable.getItems().get(i).getQty();
    		TransactionItem tItem = new TransactionItem(transID, itemID, unitPrice, quantity, "bought");
    		tranItems.add(tItem);
    	}
    	return tranItems;
    }
    
    private int convertPaid(){
    	if(Integer.parseInt(paidTF.getText()) > 0)
    		return 1;
    	return 0;
    }
    
    @FXML
    void handleCancel(ActionEvent event) {
    	completePopup.setVisible(false);
    	salesTable.getItems().clear();

    }
    
    @FXML
    void handleCart(ActionEvent event) throws SQLException {
    	newEntry = new SaleEntry();
    	System.out.println("Add to cart clicked!");
    	Item item = new Item();
    	//ayaw gumana pag model nag call
    	item = model.findItemByDesc(itemText.getText());
    	
    	newEntry.itemName.set(item.getDesc());
    	newEntry.qty.set(1);
    	newEntry.setUcost(new SimpleFloatProperty(item.getUnitPrice()));
    	
    	qty = newEntry.getQty();
    	ucost = newEntry.getUcost();
    	generateSaleEntry(newEntry);
    	
    	setTotal();
    	itemText.clear();
    }
	
	private void generateSaleEntry(SaleEntry entry) {
		data.add(entry);
		int i = data.indexOf(entry);
		entry.price.set((setPrice(qty, ucost, i)));
	  
	}
	
	private float setPrice(int newqty, float cost, int pos) {
		
		float price = 0;
		System.out.println("Cost = " +cost +" New qty = " +newqty);
		
		price = cost * newqty ;
		System.out.println("Price = "+price );
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
