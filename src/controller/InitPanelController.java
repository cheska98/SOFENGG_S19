package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InitPanelController {

	@FXML private AnchorPane initial;
    @FXML private Button loginbtn; 
    @FXML private Button registerBtn; 
    @FXML private TextField username; 
    @FXML private PasswordField password;
    private Stage primaryStage;
    private Stage stage;
    private Scene initScene;
    private Scene mainScene;
    private String uname = null;
    private String logUser = null;
    private String logPass = null;
    private String regUser = null;
    private String regPass = null;
    private FXMLLoader initLoader;
    private FXMLLoader mainLoader;
    private Alert alert;
    SuperMainController mainPaneController;
    MainController mainController;
    

    @FXML
    void handlelogin(ActionEvent event) {
    	
    	logUser = username.getText();
    	logPass = password.getText();
    	uname = logUser;
    	
    	System.out.println(logUser);
    	if (logUser != null && logPass != null)
    		setMainStage();
    	else {
    		alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERROR");
    		alert.setHeaderText(null);
    		alert.setContentText("Invalid username or password.");
    		alert.showAndWait();
    		setFields();
    	}

    }

    @FXML
    void handleregister(ActionEvent event) {
    	
    	regUser = username.getText();
    	regPass = password.getText();
    	
    	if (regUser != null && regPass != null) {
    		alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("NEW ACCOUNT CREATED");
        	alert.setHeaderText(null);
        	alert.setContentText("You may now login using your new account.");
        	alert.showAndWait();
        	setFields();
    	}
    	
    }
    
    public void setUsername() {
    	
    	mainPaneController.setUsername(uname);
    
    }
    
    private void setFields() {
    	
    	username.clear();
    	password.clear();
    	
    }
    
    public void setPrimaryStage(Stage primaryStage) {
    	
    	this.primaryStage = primaryStage;
    	
    }
    
    public void setInitialStage(InitPanelController initialController) {
    	
    	try {
			initLoader = new FXMLLoader();
			initLoader.setLocation(getClass().getResource("/view/Initial.fxml"));
			initScene = new Scene(initLoader.load(), 1541, 1080);
			
			initialController = initLoader.getController();
			stage = new Stage();
			setPrimaryStage(stage);
			stage.setTitle("Capatiran Point of Sales/Inventory System");
			stage.setResizable(true);
			stage.setScene(initScene);
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void closeStage() {
    	
    	primaryStage.close();
    
    }
    
    private void setMainStage() {
    	
    	try {
			mainLoader = new FXMLLoader();
			mainLoader.setLocation(getClass().getResource("/view/Main.fxml"));
			mainScene = new Scene(mainLoader.load(), 1541, 1080);
		
			mainPaneController = mainLoader.getController();
			mainPaneController.setController(this);
	    	setUsername();

			primaryStage.setScene(mainScene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }

}
