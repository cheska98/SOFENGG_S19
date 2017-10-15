package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PopupLogin {
	
	public void display() {
		try {
			
			Stage popStage = new Stage();
			AnchorPane popPane = FXMLLoader.load(getClass().getClassLoader().getResource("view/loginPane.fxml"));
			Scene popScene = new Scene(popPane, 400, 500);
			
			popStage.setScene(popScene);
			popStage.showAndWait();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
