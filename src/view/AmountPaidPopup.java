package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AmountPaidPopup {
	
	public void display() {
		try {
			
			Stage popStage = new Stage();
			popStage.initModality(Modality.APPLICATION_MODAL);
			AnchorPane popPane = FXMLLoader.load(getClass().getClassLoader().getResource("view/CompletePopUp.fxml"));
			Scene popScene = new Scene(popPane, 521, 231);
			
			popStage.setScene(popScene);
			popStage.showAndWait();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}