package controller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.CustomerEntry;
import model.Product;

public class DebtListController implements Initializable{

	@FXML private AnchorPane AnchorDebtList;
    @FXML private TableColumn<CustomerEntry, Float> Balancecol;
    @FXML private TableColumn<CustomerEntry, String> contactcol;
    @FXML private TableColumn<CustomerEntry, String> LTDatecol;
    @FXML private TableView<CustomerEntry> DebtListTable;
    @FXML private TableColumn<CustomerEntry, Integer> transIDcol;
    @FXML private TableColumn<CustomerEntry, String> CustNamecol;
    @FXML private Button Clearbtn;
    @FXML private TextField tf_search;
    
    @FXML
    void onSearchClick(ActionEvent event) {

    }
    
    @FXML
    void onClickClear(ActionEvent event) {

    }
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	transIDcol.setCellValueFactory(new PropertyValueFactory<CustomerEntry, Integer>("transID"));
    	contactcol.setCellValueFactory(new PropertyValueFactory<CustomerEntry, String>("contactno"));
    	LTDatecol.setCellValueFactory(new PropertyValueFactory<CustomerEntry, String>("lasttrans"));
    	CustNamecol.setCellValueFactory(new PropertyValueFactory<CustomerEntry, String>("name"));
    	Balancecol.setCellValueFactory(new PropertyValueFactory<CustomerEntry, Float>("balance"));
    	
    	DebtListTable.setRowFactory( tv -> {
            TableRow<CustomerEntry> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    CustomerEntry rowData = row.getItem();
                    try {
						AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/DebtListLoans.fxml"));
						AnchorDebtList.getChildren().setAll(pane);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            });
            return row;
        });
    	
    	DebtListTable.setItems(getCustomers());
	}
    
    public ObservableList<CustomerEntry> getCustomers(){
        ObservableList<CustomerEntry> customers = FXCollections.observableArrayList();
        customers.add(new CustomerEntry("09234567789","10-11-2017","John Doe",Float.valueOf(500),115));
        return customers;
    }

}
