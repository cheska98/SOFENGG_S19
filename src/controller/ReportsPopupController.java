package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ReportsPopUpController {

	@FXML private TableColumn<?, ?> dateCol;
    @FXML private AnchorPane reportsPopUp;
    @FXML private Button okButton;
    @FXML private TableColumn<?, ?> timeCol;
    @FXML private TableView<?> reportsPopUpTable;
    @FXML private TableColumn<?, ?> transIDCol;
    private ReportsController rc;

    
    public void initialize(ReportsController rc) {
    	this.rc = rc;
    }
    
    @FXML
    void handleOk(ActionEvent event) {
    	rc.closeView();
    }

}
