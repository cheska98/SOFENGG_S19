package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DebtListCustController {

    @FXML
    private Label CustNameLabel;

    @FXML
    private Label Item;

    @FXML
    private Label ItemNameLabel;

    @FXML
    private Label balance;

    @FXML
    private Label BalanceLabel;

    @FXML
    private Label name;

    @FXML
    private Label Title;

    @FXML
    private Button ConfirmBtn;

    @FXML
    void onConfirm(ActionEvent event) {
    	try{
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AlertDebtList.fxml"));
    		Parent root1 = (Parent) fxmlLoader.load();
    		Stage stage = new Stage();
    		stage.setTitle("Add customer");
    		stage.setScene(new Scene(root1));
    		stage.show();	
    	}catch(Exception e){
    		System.out.println("Cant load new window");
    	}
    }

}
