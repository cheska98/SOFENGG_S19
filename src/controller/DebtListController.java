package controller;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import model.Model;
import model.entity.Customer;
import model.entity.Debt;
import model.entity.Transaction;
import model.entries.CustomerEntry;
import model.entries.Product;
import model.service.CustomerService;
import model.service.DebtService;
import model.service.TransactionService;

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
    
    private static Model model; 
    
    @FXML
    void onSearchClick(ActionEvent event) {
    }
    
    @FXML
    void onClickClear(ActionEvent event) {
    	tf_search.clear();
    }
    
    public void setModel(Model model) { 
    	System.out.println("debt list controller 55");
    	this.model = model; 
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
    	
			try {
				System.out.println("debt list controller 86");
				DebtListTable.setItems(getCustomers());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
    
    public ObservableList<CustomerEntry> getCustomers() throws SQLException{
        ObservableList<CustomerEntry> customers = FXCollections.observableArrayList();
        /*search for debt - connect with transaction and customer
        get all debts
        look dor customer
        lok for transaction*/
        ArrayList<Debt> debts = new ArrayList<>();
        System.out.println("debt list controller 100");
        
        //debts = model.getAllDebts();
        debts = DebtService.getAllDebts();
        /*if(!debts.isEmpty()) {
        	debts.addAll(debts);
    		System.out.println("debt list controller 105");
        }*/
        
        customers.addAll(convert_customer(debts));
        customers.add(new CustomerEntry("09234567789","10-11-2017","John Doe",Float.valueOf(500),115));
        return customers;
    }
    
    public ArrayList<CustomerEntry> convert_customer(ArrayList<Debt> debts) throws SQLException{
    	ArrayList<CustomerEntry> custEntryList = new ArrayList<CustomerEntry>();
    	for(int i=0; i<debts.size(); i++){
    		Customer c = new Customer();
    		c = getCustomerDB(debts.get(i));
    		Transaction t = new Transaction();
    		t = getTransaction(debts.get(i));
    		
    		CustomerEntry custEntry = new CustomerEntry(c.getContact(), null, c.getName(), debts.get(i).getPaid(), t.getID());
    		custEntryList.add(custEntry);
    	}
    	return custEntryList;
    }
    
    public Customer getCustomerDB(Debt debt) throws SQLException{
    	int customerID = debt.getCustomerID();
		return model.findCustomer(customerID);
    }
    
    public Transaction getTransaction(Debt debt) throws SQLException{
    	int transactionID = debt.getTransactionID();
    	return model.findTransaction(transactionID);
    }

}
