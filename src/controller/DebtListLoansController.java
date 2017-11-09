package controller;


import javafx.scene.control.TextField;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import model.BooleanCell;
import model.Loaned;
import model.Product;

public class DebtListLoansController implements Initializable{

	 @FXML private Button Addtransbtn;
	 @FXML private AnchorPane LoanAnchorPane;
	 @FXML private TableColumn<Loaned, Float> UnitCostCol;
	 @FXML private TableView<Loaned> DebtListTransactionTable;
	 @FXML private Label CompanyLabel;
	 @FXML private TableColumn<Loaned, String> TransDateCol;
	 @FXML private Label itemLabel;
	 @FXML private Label totalLabel;
	 @FXML private Label dateLabel;
	 @FXML private Label total;
	 @FXML private TableColumn<Product, Boolean> SelectCol;
	 @FXML private Label UnitCostLabel;
	 @FXML private Label CustomerLabel;
	 @FXML private Button DLbtn;
	 @FXML private TableColumn<Loaned, String> ItemCol;
	 @FXML private Label company;
	 @FXML private Label customer;
	 @FXML private Button Paidbtn;
	 @FXML private Button backbtn;
	 
    @FXML
    void onCLickDL(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("DOWNLOAD");
		String s = "Are you sure?";
		alert.setContentText(s);
		Optional<ButtonType> result = alert.showAndWait();
		if ((result.isPresent()) && (result.get() == ButtonType.YES)) {
		    System.out.println("Downloading...");
		}
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
    void onClickBack(ActionEvent event) {
    	try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/DebtList.fxml"));
			LoanAnchorPane.getChildren().setAll(pane);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void onClickPaid(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Loaned");
		String s = "Confirm payment of this customer?";
		alert.setContentText(s);
		Optional<ButtonType> result = alert.showAndWait();
		if ((result.isPresent()) && (result.get() == ButtonType.YES)) {
		    System.out.println("Removing customer from debt list...");
		}
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	UnitCostCol.setCellValueFactory(new PropertyValueFactory<Loaned, Float>("unitcost"));
    	ItemCol.setCellValueFactory(new PropertyValueFactory<Loaned, String>("item"));
    	TransDateCol.setCellValueFactory(new PropertyValueFactory<Loaned, String>("transdate"));
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
    	DebtListTransactionTable.setItems(getProducts());
    }
    
    public ObservableList<Loaned> getProducts(){
        ObservableList<Loaned> products = FXCollections.observableArrayList();
        products.add(new Loaned("Boysen Paint Green", Float.valueOf(100), "10-13-2017"));
        products.add(new Loaned("Hammer", Float.valueOf(100), "10-17-2017"));
        return products;
    }

}
