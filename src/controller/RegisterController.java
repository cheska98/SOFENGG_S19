package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class RegisterController {

    @FXML
    private AnchorPane register;

    @FXML
    private Label label1;

    @FXML
    private TextField registerUsername;

    @FXML
    private Label label2;

    @FXML
    private PasswordField registerPassword;

    @FXML
    private Button registerButton;

    @FXML
    void handleRegister(ActionEvent event) {
    	
    	String regUser = registerUsername.getText();
    	String regPass = registerPassword.getText();

    }

}
