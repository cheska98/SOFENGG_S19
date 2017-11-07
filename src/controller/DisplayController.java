package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import model.Product;

public class DisplayController implements Initializable{

    @FXML
    private TextField tf_search;

    @FXML
    private TextField tf_Quantity;

    @FXML
    private AnchorPane display;

    @FXML
    private Button searchBtn;

    @FXML
    private TableColumn<Product, Integer> quantityCol;

    @FXML
    private Label itemLabel;

    @FXML
    private Label quantityLabel;

    @FXML
    private Button addBtn;

    @FXML
    private TextField tf_Item;

    @FXML
    private TableView<Product> displayTable;

    @FXML
    private TableColumn<Product, String> itemCol;
    
    
    @FXML
    void onSearchClick(ActionEvent event) {

    }
    
    @FXML
    void onAddClick(ActionEvent event) {
    	 Product nproduct = new Product("",0,0);
    	 SimpleStringProperty name = new SimpleStringProperty(tf_Item.getText());
         nproduct.setItem(name);
         SimpleIntegerProperty quant = new SimpleIntegerProperty(Integer.parseInt(tf_Quantity.getText()));
         nproduct.setQuantity(quant);
         displayTable.getItems().add(nproduct);
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {

    	itemCol.setCellValueFactory(
		    new PropertyValueFactory<Product, String>("item")
		);
		quantityCol.setCellValueFactory(
		    new PropertyValueFactory<Product, Integer>("quantity")
		    
		);
		displayTable.setItems(getProducts());
		quantityCol.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>(){
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
        products.add(new Product("Boysen Paint Green", 100));
        return products;
    }
   

}
