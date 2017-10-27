import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DebtListController {

    @FXML
    private TextField tf_search;

    @FXML
    private Button AddBtn;

    @FXML
    private Button SearchBtn;

    @FXML
    private TableView<?> DebtListTable;

    @FXML
    void onSearchClick(ActionEvent event) {

    }

    @FXML
    void onAddClick(ActionEvent event) {
    	try{
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddCustPopUp.fxml"));
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
