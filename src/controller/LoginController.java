package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private AnchorPane login;

    @FXML
    private Label label1;

    @FXML
    private TextField loginUsername;

    @FXML
    private Label label2;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Button loginButton;

    @FXML
    void loginuser(ActionEvent event) {
    	
    	String logUser = loginUsername.getText();
    	String logPass = loginPassword.getText();
    	Stage stage = (Stage) loginButton.getScene().getWindow();
    	stage.close();

    }

}
