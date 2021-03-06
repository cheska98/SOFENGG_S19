package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import model.BooleanCell;
import model.Product;

public class InventoryController implements Initializable{

    @FXML private TableColumn<Product, Float> pricecol;
    @FXML private TextField tf_Unitcost;
    @FXML private Button searchBtn;
    @FXML private Button Clearbtn;
    @FXML private Label itemLabel;
    @FXML private Label quantityLabel;
    @FXML private TableColumn<Product, String> itemcol;
    @FXML private TableColumn<Product, String> daterestockcol;
    @FXML private TableColumn<Product, String> dateupdatecol;
    @FXML private TableColumn<Product, Boolean> selectedcol;
    @FXML private TableView<Product> inventoryTable;
    @FXML private TextField tf_search;
    @FXML private Label UnitCostLabel;
    @FXML private Button addBtn1;
    @FXML private Button saveBtn;
    @FXML private Button deleteBtn;
    @FXML private TextField tf_Quantity;
    @FXML private TableColumn<Product, Integer> quantitycol;
    @FXML private TextField tf_Item;

    
    @FXML
    void onSearchClick(ActionEvent event) {

    }
    
    @FXML
    void onClickClear(ActionEvent event) {
    	tf_search.clear();
    }
    
    @FXML
    void onSaveClick(ActionEvent event) {
    	try {
    		Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/EnterPassword.fxml"));
			Scene scene = new Scene(loader.load(), 272, 200);
			
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void onClickDelete(ActionEvent event) {
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
    	Product nproduct = new Product("","","",0,0);
    	SimpleStringProperty name = new SimpleStringProperty(tf_Item.getText());
        nproduct.setItem(name);
        SimpleIntegerProperty quant = new SimpleIntegerProperty(Integer.parseInt(tf_Quantity.getText()));
        nproduct.setQuantity(quant);
        SimpleFloatProperty cost = new SimpleFloatProperty(Float.valueOf(tf_Unitcost.getText()));
        nproduct.setPrice(cost);
        inventoryTable.getItems().add(nproduct);
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {

    	itemcol.setCellValueFactory(new PropertyValueFactory<Product, String>("item"));
    	quantitycol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
    	pricecol.setCellValueFactory(new PropertyValueFactory<Product, Float>("price"));
    	daterestockcol.setCellValueFactory(new PropertyValueFactory<Product, String>("restock"));
    	dateupdatecol.setCellValueFactory(new PropertyValueFactory<Product, String>("update"));
    	
		Callback<TableColumn<Product, Boolean>, TableCell<Product, Boolean>> booleanCellFactory = 
                new Callback<TableColumn<Product, Boolean>, TableCell<Product, Boolean>>() {
                @Override
                    public TableCell<Product, Boolean> call(TableColumn<Product, Boolean> p) {
                        return new BooleanCell();
                }
            };
            
        selectedcol.setCellValueFactory(new PropertyValueFactory<Product, Boolean>("checkbox"));
        selectedcol.setCellFactory(booleanCellFactory);
        selectedcol.setEditable(true);
        daterestockcol.setCellValueFactory(c-> new SimpleStringProperty(getProducts().get(0).getDateRestock()));
		dateupdatecol.setCellValueFactory(c-> new SimpleStringProperty(getProducts().get(0).getDateUpdate()));
		daterestockcol.setCellValueFactory(c-> new SimpleStringProperty(getProducts().get(1).getDateRestock()));
		dateupdatecol.setCellValueFactory(c-> new SimpleStringProperty(getProducts().get(1).getDateUpdate()));
        inventoryTable.setItems(getProducts());
        itemcol.setCellFactory(TextFieldTableCell.forTableColumn());
        daterestockcol.setCellFactory(TextFieldTableCell.forTableColumn());
        dateupdatecol.setCellFactory(TextFieldTableCell.forTableColumn());
        pricecol.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Float>(){
            @Override
            public String toString(Float object) {
                return object.toString();
            }

            @Override
            public Float fromString(String string) {
                return Float.valueOf(string);
            }

        }));

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
        products.add(new Product("10-11-2015","10-01-2016","Boysen Paint Green", 100, 600));
        products.add(new Product("10-11-2015","10-01-2016","Hammer", 100, 350));
        return products;
    }
}
