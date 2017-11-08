package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class RefundReplaceController implements Initializable {

    @FXML private AnchorPane refundReplace;
    @FXML private TableView<?> tableRR;
    @FXML private TableColumn<?, ?> itemColumn;
    @FXML private TableColumn<?, ?> quantityColumn;
    @FXML private RadioButton refundRB;
    @FXML private RadioButton replaceRB;
    @FXML private Button okButton;
    private ToggleGroup refundOrReplace;

    
    @FXML
    void handleOK(ActionEvent event) {
    	
    	refundOrReplace.selectToggle(refundRB);

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
    	refundOrReplace = new ToggleGroup();
    	refundRB.setToggleGroup(refundOrReplace);
    	replaceRB.setToggleGroup(refundOrReplace);
    	refundOrReplace.selectToggle(refundRB);
		
	}

}
