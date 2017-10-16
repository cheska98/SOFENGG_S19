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

public class PersonAdd {
	
	public static void display(Person person,String title,TableView table) {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(400);
        window.setMinHeight(450);
        
        TextField amtInput = new TextField(person.getDebt()+"");
        GridPane.setConstraints(amtInput, 1, 0);
        
        TextField nameInput = new TextField(person.getName());
        GridPane.setConstraints(nameInput, 1, 0);
        
        TextField dateInput = new TextField(person.getDate());
        GridPane.setConstraints(dateInput, 1, 0);
      
        Label amtLabel = new Label("Outstanding Balance");
        Label nameLabel = new Label("Customer Name:");
        Label dateLabel = new Label("Last Transaction Date:");
        
        
        Button okButton = new Button("Add");
        okButton.setOnAction(e -> addButtonClicked(new Person(dateInput.getText(),nameInput.getText()
        		                                   ,Double.parseDouble(amtInput.getText())),table,window));
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
        
        AnchorPane.setTopAnchor(nameLabel, 120.0);
        AnchorPane.setLeftAnchor(nameLabel, 50.0);
        AnchorPane.setTopAnchor(nameInput, 160.0);
        AnchorPane.setLeftAnchor(nameInput, 50.0);
        
        AnchorPane.setTopAnchor(dateLabel, 220.0);
        AnchorPane.setLeftAnchor(dateLabel, 50.0);
        AnchorPane.setTopAnchor(dateInput, 260.0);
        AnchorPane.setLeftAnchor(dateInput, 50.0);
        
        layout.getChildren().addAll(amtInput,amtLabel,okButton, nameInput,nameLabel,dateInput,dateLabel);
        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
	
	public static void addButtonClicked(Person person,TableView t,Stage window){
        Person nperson = new Person();
        nperson.setName(person.getName());
        nperson.setDate(person.getDate());
        nperson.setdebt(person.getDebt());
        t.getItems().add(person);
        window.close();
    }
}
