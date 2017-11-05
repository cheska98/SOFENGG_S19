package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DisplayController{

    @FXML private TextField tf_search;
    @FXML private Button addBtn;
    @FXML private Button searchBtn;

    
    public DisplayController() {
		// TODO Auto-generated constructor stub

	}
    
    @FXML
    void onSearchClick(ActionEvent event) {

    }

    @FXML
    void onAddClick(ActionEvent event) {
    	try{
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddPopUp.fxml"));
    		Parent root1 = (Parent) fxmlLoader.load();
    		Stage stage = new Stage();
    		stage.setTitle("Add item");
    		stage.setScene(new Scene(root1));
    		stage.show();	
    	}catch(Exception e){
    		System.out.println("Cant load new window");
    	}
    }

}
