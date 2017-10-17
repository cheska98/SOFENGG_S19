package view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ReportsPane extends Application{

	Stage window;
    Scene scene;

    Label sbLbl1;
    Label sbLblName;
    Label sbLblCurrDate;
    Button sbBtnLogout;
    Button sbBtnSales;
    Button sbBtnDisplay;
    Button sbBtnInventory;
    Button sbBtnReports;
    Button sbBtnDebtList;
    Image sbISales;
    Image sbIDisplay;
    Image sbIInventory;
    Image sbIReports;
    Image sbIDebtList;
    ImageView sbIvSales;
    ImageView sbIvDisplay;
    ImageView sbIvInventory;
    ImageView sbIvReports;
    ImageView sbIvDebtList;
    
    Label label1;
	DatePicker dp;
	Label label2;
	ComboBox<String> cb;
	
	ListView<String> lv;
	
	
    @Override
    public void start(Stage primaryStage) {
    	window = primaryStage;
        window.setTitle("Reports Pane"); 
        
        sbLbl1 = new Label("Welcome");
        sbLbl1.setFont(Font.font("Calibri", 20));
        sbLbl1.setAlignment(Pos.CENTER);
        sbLbl1.setLayoutY(25.0);
        sbLbl1.setPrefHeight(40.0);
        sbLbl1.setPrefWidth(175.0);
        
        sbLblName = new Label("Cris!");
        sbLblName.setFont(Font.font("Calibri", FontWeight.BOLD, 33));
        sbLblName.setAlignment(Pos.CENTER);
        sbLblName.setLayoutY(73.0);
        sbLblName.setPrefHeight(48.0);
        sbLblName.setPrefWidth(175.0);
        
        sbBtnLogout = new Button("Logout");
        sbBtnLogout.setFont(Font.font("Calibri", 18));
        sbBtnLogout.setLayoutX(14.0);
        sbBtnLogout.setLayoutY(140.0);
        sbBtnLogout.setPrefHeight(34.0);
        sbBtnLogout.setPrefWidth(147.0);
        sbBtnLogout.setStyle("-fx-background-color: #289b01;");
        sbBtnLogout.setTextFill(Color.WHITE);
        
        sbISales = new Image(getClass().getResourceAsStream("/img/sales.png"));
        sbIvSales = new ImageView(sbISales);
        sbIvSales.setFitHeight(155.0);
        sbIvSales.setFitWidth(159.0);
        sbBtnSales = new Button();
        sbBtnSales.setGraphic(sbIvSales);
        sbBtnSales.setLayoutY(195.0);
        
        sbIDisplay = new Image(getClass().getResourceAsStream("/img/display.png"));
        sbIvDisplay = new ImageView(sbIDisplay);
        sbIvDisplay.setFitHeight(155.0);
        sbIvDisplay.setFitWidth(159.0);
        sbBtnDisplay = new Button();
        sbBtnDisplay.setGraphic(sbIvDisplay);
        sbBtnDisplay.setLayoutY(358.0);
        
        sbIInventory = new Image(getClass().getResourceAsStream("/img/inventory.png"));
        sbIvInventory = new ImageView(sbIInventory);
        sbIvInventory.setFitHeight(155.0);
        sbIvInventory.setFitWidth(159.0);
        sbBtnInventory = new Button();
        sbBtnInventory.setGraphic(sbIvInventory);
        sbBtnInventory.setLayoutY(515.0);
        
        sbIReports = new Image(getClass().getResourceAsStream("/img/reports.png"));
        sbIvReports = new ImageView(sbIReports);
        sbIvReports.setFitHeight(155.0);
        sbIvReports.setFitWidth(159.0);
        sbBtnReports = new Button();
        sbBtnReports.setGraphic(sbIvReports);
        sbBtnReports.setLayoutY(670.0);
        
        sbIDebtList = new Image(getClass().getResourceAsStream("/img/debt_list.png"));
        sbIvDebtList = new ImageView(sbIDebtList);
        sbIvDebtList.setFitHeight(155.0);
        sbIvDebtList.setFitWidth(159.0);
        sbBtnDebtList = new Button();
        sbBtnDebtList.setGraphic(sbIvDebtList);
        sbBtnDebtList.setLayoutY(825.0);
        
        sbLblCurrDate = new Label("10/18/2017");
        sbLblCurrDate.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
        sbLblCurrDate.setAlignment(Pos.CENTER);
        sbLblCurrDate.setLayoutY(1005.0);
        sbLblCurrDate.setPrefHeight(57.0);
        sbLblCurrDate.setPrefWidth(175.0);
        
        label1 = new Label("Select date to view daily report.");
        label1.setFont(Font.font("Calibri", 18));
        label1.setLayoutX(20);
        label1.setLayoutY(20.0);
        label1.setPrefHeight(40.0);
        label1.setPrefWidth(300.0);
        
        label1 = new Label("Select date to view daily report.");
        label1.setFont(Font.font("Calibri", 20));
        label1.setLayoutX(200);
        label1.setLayoutY(20.0);
        label1.setPrefHeight(40.0);
        label1.setPrefWidth(300.0);
        
        dp = new DatePicker();
        dp.setLayoutX(200);
        dp.setLayoutY(55);
        dp.setPrefWidth(253);
        dp.setStyle("-fx-font-size: 18;");
        dp.setPromptText("10/18/2017");
        
        
        label2 = new Label("Select month to view monthly report.");
        label2.setFont(Font.font("Calibri", 20));
        label2.setLayoutX(200);
        label2.setLayoutY(400.0);
        label2.setPrefHeight(40.0);
        label2.setPrefWidth(350.0);
        
        ObservableList<String> months = 
        	    FXCollections.observableArrayList(
        	        "January",
        	        "February",
        	        "March",
        	        "April",
        	        "May",
        	        "June",
        	        "July",
        	        "August",
        	        "September",
        	        "October",
        	        "November",
        	        "December"
        	    );
    	cb = new ComboBox<String>(months);
    	cb.setLayoutX(200);
    	cb.setLayoutY(435);
    	cb.setPromptText("Month");
    	cb.setPrefWidth(253);
    	cb.setStyle("-fx-font-size: 18;");
        	
    	ObservableList<String> list = 
        	    FXCollections.observableArrayList(
        	        "Daily Report for 10/18/2017"
        	    );
    	
    	lv = new ListView<String>(list);
    	lv.setStyle("-fx-font-size: 18;");
    	lv.setPrefHeight(1900);
    	lv.setPrefWidth(820);
    	lv.setLayoutX(530);
    	lv.setLayoutY(20);
        
        AnchorPane layout = new AnchorPane();

        layout.getChildren().addAll(sbLbl1, sbLblName, sbBtnLogout, sbBtnSales, 
        		sbBtnDisplay, sbBtnInventory, sbBtnReports, sbBtnDebtList, sbLblCurrDate,
        		label1, dp, label2, cb, lv);

        scene = new Scene(layout, 1920, 1080);
        window.setScene(scene);
        window.show();
    }
}
