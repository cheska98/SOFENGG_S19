package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class SalesController {

    @FXML
    private TableView<?> salesTable;

    @FXML
    private TableColumn<?, ?> quantityCol;

    @FXML
    private AnchorPane salesPanel;

    @FXML
    private TableColumn<?, ?> costCol;

    @FXML
    private TableColumn<?, ?> itemCol;

    @FXML
    private TableColumn<?, ?> priceCol;

}
