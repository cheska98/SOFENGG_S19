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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Loaned;

public class DebtListLoansController implements Initializable{

	 @FXML private Button Addtransbtn;
	 @FXML private AnchorPane LoanAnchorPane;
	 @FXML private TableColumn<Loaned, Float> UnitCostCol;
	 @FXML private TableView<Loaned> DebtListTransactionTable;
	 @FXML private Label CompanyLabel;
	 @FXML private TextField tf_Unitcost;
	 @FXML private TableColumn<Loaned, String> TransDateCol;
	 @FXML private DatePicker dp_Transactiondate;
	 @FXML private Label itemLabel;
	 @FXML private Label totalLabel;
	 @FXML private Label dateLabel;
	 @FXML private Label total;
	 @FXML private Label UnitCostLabel;
	 @FXML private Label CustomerLabel;
	 @FXML private Button DLbtn;
	 @FXML private TableColumn<Loaned, String> ItemCol;
	 @FXML private Label company;
	 @FXML private TextField tf_Item;
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
    
    @FXML
    void onAdd(ActionEvent event) {
    	Loaned nproduct = new Loaned("",Float.valueOf(100),"");
    	SimpleStringProperty itemname = new SimpleStringProperty(tf_Item.getText());
        nproduct.setItem(itemname);
        SimpleStringProperty transdate = new SimpleStringProperty(dp_Transactiondate.getValue().toString());
        nproduct.setTransdate(transdate);
        SimpleFloatProperty cost = new SimpleFloatProperty(Float.valueOf(tf_Unitcost.getText()));
        nproduct.setUnitcost(cost);
        DebtListTransactionTable.getItems().add(nproduct);
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	UnitCostCol.setCellValueFactory(new PropertyValueFactory<Loaned, Float>("unitcost"));
    	ItemCol.setCellValueFactory(new PropertyValueFactory<Loaned, String>("item"));
    	TransDateCol.setCellValueFactory(new PropertyValueFactory<Loaned, String>("transdate"));
    	DebtListTransactionTable.setItems(getProducts());
    }
    
    public ObservableList<Loaned> getProducts(){
        ObservableList<Loaned> products = FXCollections.observableArrayList();
        products.add(new Loaned("Boysen Paint Green", Float.valueOf(100), "10-13-2017"));
        products.add(new Loaned("Hammer", Float.valueOf(100), "10-17-2017"));
        return products;
    }

}
