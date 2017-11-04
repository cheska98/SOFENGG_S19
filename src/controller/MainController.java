package controller;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController extends Application {
	
	Stage window;
	Scene initPane;
	Scene salePane;
	Scene startPane;
	//@FXML LoginController logController;
	//RegisterController regController;
	private AnchorPane paneSale;
	
	public static final int INIT_PANE = 1;
	public static final int SALE_PANE = 2;
	
	public MainController() {
	
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			
			init(primaryStage);
			window.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void init(Stage primaryStage) {
		
		try { 
			
			window = primaryStage;
			AnchorPane paneInit = FXMLLoader.load(getClass().getClassLoader().getResource("view/Initial.fxml"));
			paneSale = FXMLLoader.load(getClass().getClassLoader().getResource("view/Sales.fxml"));
			
			initPane = new Scene(paneInit,1541,1080);
			salePane = new Scene(paneSale, 1541, 1080);
			
			//logController = new LoginController(getInstance());
			
			//initPane.getStylesheets().add(gtClass().getClassLoader().getResource("css/initPanelStyle.css").toExternalForm());
			window.setScene(initPane);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
