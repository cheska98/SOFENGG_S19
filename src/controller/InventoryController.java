package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleFloatProperty;
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
import javafx.util.StringConverter;
import model.Product;

public class InventoryController implements Initializable{

    @FXML
    private TableColumn<Product, Float> unitcostcol;

    @FXML
    private TextField tf_Unitcost;

    @FXML
    private Button searchBtn;

    @FXML
    private Label itemLabel;

    @FXML
    private Label quantityLabel;

    @FXML
    private TableColumn<Product, String> itemcol;

    @FXML
    private TableView<Product> inventoryTable;

    @FXML
    private TextField tf_search;

    @FXML
    private Label UnitCostLabel;

    @FXML
    private Button addBtn1;

    @FXML
    private TextField tf_Quantity;

    @FXML
    private TableColumn<Product, Integer> quantitycol;

    @FXML
    private TextField tf_Item;

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
        SimpleFloatProperty cost = new SimpleFloatProperty(Float.valueOf(tf_Unitcost.getText()));
        nproduct.setUnitcost(cost);
        inventoryTable.getItems().add(nproduct);
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	itemcol.setCellValueFactory(new PropertyValueFactory<Product, String>("item"));
    	quantitycol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
    	unitcostcol.setCellValueFactory(new PropertyValueFactory<Product, Float>("unitcost"));
    	inventoryTable.setItems(getProducts());
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
        products.add(new Product("Boysen Paint Green", 100, 600));
        products.add(new Product("Hammer", 100, 350));
        return products;
    }
}
