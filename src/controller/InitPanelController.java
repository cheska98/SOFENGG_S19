package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.PopupLogin;
import view.PopupRegister;

public class InitPanelController {
	
	private PopupLogin popUpLogin;
	private PopupRegister popupRegister;

    @FXML
    private Button loginbtn;

    
    
    public InitPanelController() {
    	
    	popUpLogin = new PopupLogin();
    	popupRegister = new PopupRegister();
    	
    }
    
    @FXML
    void handlelogin(ActionEvent event) {
    	
    	    popUpLogin.display();
    }
    
    @FXML
    void handleregister(ActionEvent event) {
    	
    	popupRegister.display();
    	
    }

}
