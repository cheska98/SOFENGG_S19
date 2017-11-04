package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class InitPanelController {

    @FXML
    private Button loginbtn;

    @FXML
    private Button registerBtn;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    void handlelogin(ActionEvent event) {
    	
    	String logUser = username.getText();
    	String logPass = password.getText();
    	
    	System.out.println(logUser);

    }

    @FXML
    void handleregister(ActionEvent event) {
    	
    	String regUser = username.getText();
    	String regPass = password.getText();

    }

}
