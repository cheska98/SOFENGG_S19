import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InventoryAdd {
	
	public static void display(Product product,String title,TableView table) {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(400);
        window.setMinHeight(450);
        
        //Amt Input
        TextField amtInput = new TextField(product.getPrice()+"");
        GridPane.setConstraints(amtInput, 1, 0);
        
        //Item Name Input
        TextField itemInput = new TextField(product.getItem());
        GridPane.setConstraints(itemInput, 1, 0);
        
        //Quantity Input
        TextField quantityInput = new TextField(product.getQuantity()+"");
        GridPane.setConstraints(quantityInput, 1, 0);
      
        Label amtLabel = new Label("Unit Cost:");
        Label itemLabel = new Label("Item Name:");
        Label quantityLabel = new Label("Quantity:");
        
        
        Button okButton = new Button("Add");
        okButton.setOnAction(e -> addButtonClicked(new Product(itemInput.getText(),
        		Double.parseDouble(amtInput.getText()),Integer.parseInt(quantityInput.getText())),table,window));
        okButton.setMinHeight(30);
        okButton.setMinWidth(100);
        
        AnchorPane layout = new AnchorPane();
        layout.setPadding(new Insets(20, 20, 20, 20));
        AnchorPane.setRightAnchor(okButton, 10.0);
        AnchorPane.setBottomAnchor(okButton, 10.0);
        
        AnchorPane.setTopAnchor(amtLabel, 40.0);
        AnchorPane.setLeftAnchor(amtLabel, 50.0);
        AnchorPane.setTopAnchor(amtInput, 70.0);
        AnchorPane.setLeftAnchor(amtInput, 50.0);
        
        AnchorPane.setTopAnchor(itemLabel, 120.0);
        AnchorPane.setLeftAnchor(itemLabel, 50.0);
        AnchorPane.setTopAnchor(itemInput, 160.0);
        AnchorPane.setLeftAnchor(itemInput, 50.0);
        
        AnchorPane.setTopAnchor(quantityLabel, 220.0);
        AnchorPane.setLeftAnchor(quantityLabel, 50.0);
        AnchorPane.setTopAnchor(quantityInput, 260.0);
        AnchorPane.setLeftAnchor(quantityInput, 50.0);
        
        layout.getChildren().addAll(amtInput,amtLabel,okButton, itemInput,itemLabel,quantityInput,quantityLabel);
        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
	
	public static void addButtonClicked(Product product,TableView t,Stage window){
        Product nproduct = new Product();
        nproduct.setItemName(product.getItem());
        nproduct.setPrice(product.getPrice());
        nproduct.setQuantity(product.getQuantity());
        t.getItems().add(product);
        window.close();
    }
}
