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
import javafx.scene.layout.AnchorPane;

public class RefundReplaceController implements Initializable {

    @FXML
    private AnchorPane refundReplace;

    @FXML
    private RadioButton replaceRB;

    @FXML
    private RadioButton refundRB;

    @FXML
    private Button okButton;

    @FXML
    private TableView<?> tableRR;

    @FXML
    private TableColumn<?, ?> itemColumn;

    @FXML
    private TableColumn<?, ?> quantityColumn;

    @FXML
    void handleOK(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
