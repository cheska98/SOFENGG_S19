package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Report;

public class ReportsController implements Initializable {

    @FXML private AnchorPane reportsPane;
    @FXML private ScrollPane scrollPane;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private Label dateMonthLabel;
    @FXML private Label reportLabel;
    @FXML private DatePicker datePicker;
    @FXML private ComboBox<String> monthCB;
    @FXML private TableView<Report> reportsTable;
    @FXML private TableColumn<Report, String> itemCol;
    @FXML private TableColumn<Report, String> quantityCol;
    @FXML private TableColumn<Report, String> unitCostCol;
    @FXML private TableColumn<Report, String> priceCol;
    @FXML private Button viewButton;
    @FXML private Button downloadButton;
    private Stage stage;
    private ObservableList<Report> data;
    private String currDate = null;
    private String month = null;
    private String year = null;
    
    public void setCurrDate(String date) {
    	currDate = date;
    }
    
    @FXML
    public void initialize() {
    	
    	dateMonthLabel.setText(currDate);
    	
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

    	itemCol.setCellValueFactory(
		    new PropertyValueFactory<Report, String>("item")
		);
		quantityCol.setCellValueFactory(
		    new PropertyValueFactory<Report, String>("quantity")
		    
		);
		unitCostCol.setCellValueFactory(
		    new PropertyValueFactory<Report, String>("unitCost")
		);
		
		priceCol.setCellValueFactory(
				new PropertyValueFactory<Report, String>("price")
		);
			
    	reportsTable.setRowFactory( tv -> {
            TableRow<Report> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Report rowData = row.getItem();
                    handleRow();
                }
            });
            return row;
        });
    	
    	data = FXCollections.observableArrayList();
		reportsTable.setItems(data);
		
		generateReportRow("Hammer", 3, 70, 210);
		
	}
    
    private void generateReportRow(String item, int quantity, float unitCost, float price) {
    	
    	Report row = new Report();
    	row.item.set(item);
    	row.quantity.set(quantity);
    	row.unitCost.set(unitCost);
    	row.price.set(price);
    	data.add(row);
    	
    }
    
    private void handleRow() {
    	
    	try {
    		stage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/ReportsPopUp.fxml"));
			Scene scene = new Scene(loader.load(), 511, 219);
		
			ReportsPopUpController rep = loader.getController();
			rep.initialize(this);
			
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    
    @FXML
    void handleView(ActionEvent event) {
    	
    	try {
    		stage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/TopItemsPopUp.fxml"));
			Scene scene = new Scene(loader.load(), 500, 550);
		
			TopItemsPopUpController top = loader.getController();
			top.initialize(this);
			
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }

    @FXML
    void handleDownload(ActionEvent event) {
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("DOWNLOAD");
		String s = "Are you sure?";
		alert.setContentText(s);
		Optional<ButtonType> result = alert.showAndWait();
		if ((result.isPresent()) && (result.get() == ButtonType.YES)) {
		    System.out.println("Downloading...");
		}

    }
    
    public void closeView() {
    	stage.close();
    }

}
