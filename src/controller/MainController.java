package controller;
	
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;

public class MainController extends Application {
	
	private Model model;
	private DebtListController debtListController;
	private DebtListLoansController debtListLoansController;
	private DisplayController displayController;
	private InventoryController inventoryController;
	private RefundReplaceController refundReplaceController;
	private ReportsController reportsController;
	private TopItemsPopUpController topItemsPopUpController;
	private TransactionController transactionController;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			initializeControllers();
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Initial.fxml"));
			
			Scene scene = new Scene(loader.load(), 1541, 1080);
			
			InitPanelController initialController = loader.getController();
			initialController.setPrimaryStage(primaryStage);
			
			primaryStage.setTitle("Capatiran Point of Sales/Inventory System");
			primaryStage.setResizable(true);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initializeControllers(){
		try {
			model = new Model();
			
			debtListController = new DebtListController();
			debtListController.setModel(model);
			
			//debtListLoansController = new DebtListLoansController
			//debtListLoansController.setModel(model);
			
			displayController = new DisplayController();
			displayController.setModel(model);
			
			inventoryController = new InventoryController();
			inventoryController.setModel(model);
			
			//refundReplaceController = new RefundReplaceController();
			//refundReplaceController.setModel(model);
			
			//reportsController = new ReportsController();
			//reportsController.setModel(model);
			
			//topItemsPopUpController = new TopItemsPopUpController();
			//topItemsPopUpController.setModel(model);
			
			transactionController = new TransactionController();
			transactionController.setModel(model);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
