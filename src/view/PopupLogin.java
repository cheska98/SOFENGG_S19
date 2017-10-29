package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupLogin {
	
	public void display() {
		try {
			
			Stage popStage = new Stage();
			popStage.initModality(Modality.APPLICATION_MODAL);
			AnchorPane popPane = FXMLLoader.load(getClass().getClassLoader().getResource("view/Login.fxml"));
			Scene popScene = new Scene(popPane, 400, 420);
			
			popStage.setScene(popScene);
			popStage.showAndWait();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
