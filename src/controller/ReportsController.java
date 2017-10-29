package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ReportsController {

    @FXML
    private TableColumn<?, ?> purchaseCol;

    @FXML
    private Button downloadButton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TableView<?> reportsTable;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<?> monthCB;

    @FXML
    private TableColumn<?, ?> dateTimeCol;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label dateMonthLabel;

    @FXML
    private AnchorPane reportsPane;

    @FXML
    private Label reportLabel;

    @FXML
    private Button viewButton;

    @FXML
    private TableColumn<?, ?> transIDCol;

    @FXML
    void handleView(ActionEvent event) {

    }

    @FXML
    void handleDownload(ActionEvent event) {

    }

}
