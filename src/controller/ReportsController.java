package controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.ReportEntry;

public class ReportsController implements Initializable {

    @FXML private AnchorPane reports;
    @FXML private ScrollPane scrollPane;
    @FXML private Label dateMonthLabel;
    @FXML private Label reportLabel;
    @FXML private DatePicker datePicker;
    @FXML private ComboBox<String> monthCB;
    @FXML private ComboBox<String> yearCB;
    @FXML private TableView<ReportEntry> reportsTable;
    @FXML private TableColumn<ReportEntry, String> itemCol;
    @FXML private TableColumn<ReportEntry, String> quantityCol;
    @FXML private TableColumn<ReportEntry, String> unitCostCol;
    @FXML private TableColumn<ReportEntry, String> priceCol;
    @FXML private Button dateOkButton;
    @FXML private Button monthYearOkButton;
    @FXML private Button viewButton;
    @FXML private Button downloadButton;
    
    private Stage stage;
    private ObservableList<ReportEntry> data;
    private Calendar now = Calendar.getInstance();
	private String currDate = null;
	private String month = null;
	private String day = null;
	private String year = Integer.toString(now.get(Calendar.YEAR));
	
	
	private void setCurrDate() {
		
		if (now.get(Calendar.MONTH)+1 < 10)
			month = 0 + Integer.toString(now.get(Calendar.MONTH)+1);
		else
			month = Integer.toString(now.get(Calendar.MONTH)+1);
		
		if (now.get(Calendar.DATE) < 10)
			day = 0 + Integer.toString(now.get(Calendar.DATE));
		else
			day = Integer.toString(now.get(Calendar.DATE));

		currDate = year + "-" + month + "-" + day;
		dateMonthLabel.setText(currDate);
		datePicker.setPromptText(currDate);
		
	}
	
	private void setMonthCB() {
		
		monthCB.getItems().add("January");
		monthCB.getItems().add("February");
		monthCB.getItems().add("March");
		monthCB.getItems().add("April");
		monthCB.getItems().add("June");
		monthCB.getItems().add("July");
		monthCB.getItems().add("August");
		monthCB.getItems().add("September");
		monthCB.getItems().add("October");
		monthCB.getItems().add("November");
		monthCB.getItems().add("December");
		monthCB.getSelectionModel().selectFirst();
		
	}
	
	private void setYearCB() {
		
		yearCB.getItems().add(year);
		yearCB.getSelectionModel().selectFirst();
		
	}
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
		setCurrDate();
		setMonthCB();
		setYearCB();
		
    	itemCol.setCellValueFactory(
		    new PropertyValueFactory<ReportEntry, String>("item")
		);
    	
		quantityCol.setCellValueFactory(
		    new PropertyValueFactory<ReportEntry, String>("quantity")
		    
		);
		
		unitCostCol.setCellValueFactory(
		    new PropertyValueFactory<ReportEntry, String>("unitCost")
		);
		
		priceCol.setCellValueFactory(
				new PropertyValueFactory<ReportEntry, String>("price")
		);
			
    	reportsTable.setRowFactory( tv -> {
            TableRow<ReportEntry> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    ReportEntry rowData = row.getItem();
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
    	
    	ReportEntry row = new ReportEntry();
    	row.item.set(item);
    	row.quantity.set(quantity);
    	row.unitCost.set(unitCost);
    	row.price.set(price);
    	data.add(row);
    	
    }
    
    @FXML
    void handleDateOk(ActionEvent event) {
    	
    	if (datePicker.getValue() != null) {
    		LocalDate localDate = datePicker.getValue();
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        	String selectedDate = localDate.format(formatter);
    		reportLabel.setText("Daily Report for");
    		dateMonthLabel.setText(selectedDate);
    		datePicker.setValue(LocalDate.parse(formatter.format(LocalDate.now())));
    	} else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERROR");
    		alert.setHeaderText(null);
    		alert.setContentText("No date was selected!");
    		alert.showAndWait();
    	}
    	
    }

    @FXML
    void handleMonthYearOk(ActionEvent event) {
    	
    	if (monthCB.getValue() != null && yearCB.getValue() != null) {
    		String selectedMonth = monthCB.getValue();
    		String selectedYear = yearCB.getValue();
    		reportLabel.setText("Monthly Report for");
    		dateMonthLabel.setText(selectedMonth + " " + selectedYear);
    		monthCB.getSelectionModel().selectFirst();
    		yearCB.getSelectionModel().selectFirst();
    	} else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERROR");
    		alert.setHeaderText(null);
    		alert.setContentText("No month or year was selected!");
    		alert.showAndWait();
    	}
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
			Scene scene = new Scene(loader.load(), 550, 500);
		
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
