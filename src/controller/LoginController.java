package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import controller.MainController;

public class LoginController {

    @FXML
    private TextField loginun;

    @FXML
    private PasswordField loginpwd;

    @FXML
    private Button loginbtn;
    
    public LoginController() {
    	
    }

    @FXML
    void loginuser(ActionEvent event) {
    	
    	//controller.changeScene("view/initPanel.fxml");

    }

}
