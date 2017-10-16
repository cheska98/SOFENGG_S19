import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DebtListPopUp {
	public static void display(Person person,String title) {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(400);
        window.setMinHeight(400);
        
        //Debt Input
        TextField amtInput = new TextField("Enter amount");
        GridPane.setConstraints(amtInput, 1, 0);
        
        Button deductButton = new Button("Deduct");
        deductButton.setOnAction(e -> window.close());
        deductButton.setMinHeight(30);
        deductButton.setMinWidth(80);
        
        AnchorPane layout = new AnchorPane();
        layout.setPadding(new Insets(20, 20, 20, 20));
        AnchorPane.setRightAnchor(deductButton, 50.0);
        AnchorPane.setTopAnchor(deductButton, 48.0);
        AnchorPane.setTopAnchor(amtInput, 50.0);
        AnchorPane.setRightAnchor(amtInput, 170.0);
              
        
        layout.getChildren().addAll(amtInput,deductButton);
        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
