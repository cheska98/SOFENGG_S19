package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class TopItemsPopUpController {

    @FXML private AnchorPane topItemsPopUp;
    @FXML private ScrollPane scrollPane;
    @FXML private Label label1;
    @FXML private TableView<?> topItemsTable;
    @FXML private TableColumn<?, ?> itemCol;
    @FXML private TableColumn<?, ?> quantityCol;
    @FXML private Button okButton;
    private ReportsController rc;

    
    public void initialize(ReportsController rc) {
    	this.rc = rc;
    }
    
    @FXML
    void handleOk(ActionEvent event) {
    	rc.closeView();
    }
    
}
