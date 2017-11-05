package controller;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Initial.fxml"));
			
			Scene scene = new Scene(loader.load(), 1541, 1080);
			
			InitPanelController initCtr = loader.getController();
			initCtr.setPrimaryStage(primaryStage);
			
			primaryStage.setTitle("Capatiran Point of Sales/Inventory System");
			primaryStage.setResizable(true);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
