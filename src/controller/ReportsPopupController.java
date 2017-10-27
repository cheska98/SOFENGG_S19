package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ReportsPopupController {

    @FXML
    private AnchorPane reportsPopupPane;
    
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TableView<?> reportsPopupTable;

    @FXML
    private TableColumn<?, ?> itemCol;

    @FXML
    private TableColumn<?, ?> quantityCol;

    @FXML
    private TableColumn<?, ?> unitCostCol;

    @FXML
    private TableColumn<?, ?> totalCostCol;

}
