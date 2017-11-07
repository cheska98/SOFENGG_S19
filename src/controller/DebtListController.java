package controller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;
import model.Product;
import model.Report;

public class DebtListController implements Initializable{

    @FXML
    private TableColumn<Customer, Float> Balancecol;

    @FXML
    private TableColumn<Customer, String> contactcol;

    @FXML
    private TableColumn<Customer, String> LTDatecol;

    @FXML
    private TableView<Customer> DebtListTable;

    @FXML
    private TableColumn<Customer, Integer> transIDcol;

    @FXML
    private TableColumn<Customer, String> CustNamecol;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	transIDcol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("transID"));
    	contactcol.setCellValueFactory(new PropertyValueFactory<Customer, String>("contactno"));
    	LTDatecol.setCellValueFactory(new PropertyValueFactory<Customer, String>("lasttrans"));
    	CustNamecol.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
    	Balancecol.setCellValueFactory(new PropertyValueFactory<Customer, Float>("balance"));
    	
    	DebtListTable.setRowFactory( tv -> {
            TableRow<Customer> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Customer rowData = row.getItem();
                    
                }
            });
            return row;
        });
    	
    	
    	
    	DebtListTable.setItems(getCustomers());
	}
    
    public ObservableList<Customer> getCustomers(){
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        customers.add(new Customer("09234567789","10/11/2017","John Doe",Float.valueOf(500),115));
        return customers;
    }

}
