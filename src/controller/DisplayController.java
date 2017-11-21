package controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.StringConverter;
import model.BooleanCell;
import model.Product;

public class DisplayController implements Initializable{

    @FXML private TextField tf_search;
    @FXML private TextField tf_price;
    @FXML private TextField tf_item;
    @FXML private TextField tf_quantity;
    @FXML private AnchorPane display;
    @FXML private Button searchBtn;
    @FXML private Button deleteBtn;
    @FXML private Button Clearbtn;
    @FXML private TableColumn<Product, Integer> quantitycol;
    @FXML private Label itemLabel;
    @FXML private Label priceLabel;
    @FXML private Label quantityLabel;
    @FXML private Button addBtn;
    @FXML private TableView<Product> displayTable;
    @FXML private TableColumn<Product, String> itemcol;
    @FXML private TableColumn<Product, String> daterestockcol;
    @FXML private TableColumn<Product, String> dateupdatecol;
    @FXML private TableColumn<Product, Float> pricecol;
    @FXML private TableColumn<Product, Boolean> SelectCol;
    
    @FXML
    void onSearchClick(ActionEvent event) {

    }
    
    @FXML
    void onClickClear(ActionEvent event) {
    	tf_search.clear();
    }
    
    @FXML
    void onDeleteClick(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Item");
		String s = "Are you sure you want to remove these items?";
		alert.setContentText(s);
		Optional<ButtonType> result = alert.showAndWait();
		if ((result.isPresent()) && (result.get() == ButtonType.YES)) {
		    System.out.println("Removing item from inventory...");
		}
    	
    }
    
    @FXML
    void onAddClick(ActionEvent event) {
    	 Product nproduct = new Product("","",0,0);
    	 SimpleStringProperty name = new SimpleStringProperty(tf_item.getText());
         nproduct.setItem(name);
         SimpleIntegerProperty quant = new SimpleIntegerProperty(Integer.parseInt(tf_quantity.getText()));
         nproduct.setQuantity(quant);
         SimpleFloatProperty price = new SimpleFloatProperty(Float.valueOf(tf_price.getText()));
         nproduct.setPrice(price);
         nproduct.setDateRestock(new SimpleStringProperty("11/10/2017"));
         displayTable.getItems().add(nproduct);
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {

    	daterestockcol.setCellValueFactory(new PropertyValueFactory<Product, String>("datestock"));
		dateupdatecol.setCellValueFactory(new PropertyValueFactory<Product, String>("dateupdate"));
    	itemcol.setCellValueFactory(new PropertyValueFactory<Product, String>("item"));
		quantitycol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
		pricecol.setCellValueFactory(new PropertyValueFactory<Product, Float>("price"));
		
		Callback<TableColumn<Product, Boolean>, TableCell<Product, Boolean>> booleanCellFactory = 
                new Callback<TableColumn<Product, Boolean>, TableCell<Product, Boolean>>() {
                @Override
                    public TableCell<Product, Boolean> call(TableColumn<Product, Boolean> p) {
                        return new BooleanCell();
                }
            };
            
        SelectCol.setCellValueFactory(new PropertyValueFactory<Product, Boolean>("checkbox"));
        SelectCol.setCellFactory(booleanCellFactory);
        SelectCol.setEditable(true);
		displayTable.setItems(getProducts());
		itemcol.setCellFactory(TextFieldTableCell.forTableColumn());
		daterestockcol.setCellFactory(TextFieldTableCell.forTableColumn());
		dateupdatecol.setCellFactory(TextFieldTableCell.forTableColumn());
		quantitycol.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>(){
            @Override
            public String toString(Integer object) {
                return object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }

        }));

	}
    
    public ObservableList<Product> getProducts(){
        ObservableList<Product> products = FXCollections.observableArrayList();
        products.add(new Product("10-15-2017","Boysen Paint Green", 100, Float.valueOf(300)));
        return products;
    }
   

}
