package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InitPanelController {

    @FXML private Button loginbtn; 
    @FXML private Button registerBtn; 
    @FXML private TextField username; 
    @FXML private PasswordField password;
    private Stage primaryStage;
    private String uname = null;
    SuperMainController smc;
    

    @FXML
    void handlelogin(ActionEvent event) {
    	
    	String logUser = username.getText();
    	String logPass = password.getText();
    	uname = logUser;
    	
    	System.out.println(logUser);
    	if (logUser != null && logPass != null)
    		setMainStage();
    	else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERROR");
    		alert.setHeaderText(null);
    		alert.setContentText("Invalid username or password.");
    		alert.showAndWait();
    		setFields();
    	}

    }

    @FXML
    void handleregister(ActionEvent event) {
    	
    	String regUser = username.getText();
    	String regPass = password.getText();
    	
    	if (regUser != null && regPass != null) {
    		Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("NEW ACCOUNT CREATED");
        	alert.setHeaderText(null);
        	alert.setContentText("You may now login using your new account.");
        	alert.showAndWait();
        	setFields();
    	}
    	
    }
    
    public void setUsername() {
    	smc.setUsername(uname);
    }
    
    private void setFields() {
    	
    	username.clear();
    	password.clear();
    	
    }
    
    public void setPrimaryStage(Stage primaryStage) {
    	
    	this.primaryStage = primaryStage;
    	
    }
    
    private void setMainStage() {
    	
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Main.fxml"));
			Scene scene = new Scene(loader.load(), 1541, 1080);
		
			smc = loader.getController();
	    	setUsername();
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }

}
