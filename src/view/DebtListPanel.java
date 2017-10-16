import java.awt.Color;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DebtListPanel extends Application {

    Stage window;
    Scene scene;
    Button button;
    Button buttonadd;
    TableView<Person> table;
    TextField nameInput, priceInput, quantityInput;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Debt List");

        //Name column
        TableColumn<Person, String> itemColumn = new TableColumn<>("Last Transaction Date");
        itemColumn.setMinWidth(150);
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        //Price column
        TableColumn<Person, Double> unitcostColumn = new TableColumn<>("Customer Name");
        unitcostColumn.setMinWidth(160);
        unitcostColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Quantity column
        TableColumn<Person, String> balanceColumn = new TableColumn<>("Outstanding Balance");
        balanceColumn.setMinWidth(160);
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("debt"));
           
        //Form
        TextField SearchField = new TextField();
        SearchField.setPrefWidth(400);

        button = new Button("Search");
        button.setOnAction( e -> Search(SearchField.getText()));

        table = new TableView<>();
        table.setItems(getProduct());
        table.getColumns().addAll(itemColumn, unitcostColumn, balanceColumn);
        table.setRowFactory( tv -> {
            TableRow<Person> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Person rowData = row.getItem();
                    DebtListPopUp.display(rowData,"List of transactions");
                }
            });
            return row;
        });
        
        buttonadd = new Button("Add Customer");
        buttonadd.setOnAction( e -> PersonAdd.display(new Person("None","None",0),"Add Customer",table));
        
        //Layout
        AnchorPane layout = new AnchorPane();
        layout.setPadding(new Insets(20, 20, 20, 20));
        AnchorPane.setRightAnchor(button, 550.0);
        AnchorPane.setTopAnchor(button, 50.0);
        AnchorPane.setTopAnchor(SearchField, 50.0);
        AnchorPane.setLeftAnchor(SearchField, 300.0);
        AnchorPane.setLeftAnchor(buttonadd, 300.0);
        AnchorPane.setTopAnchor(buttonadd, 510.0);
        AnchorPane.setLeftAnchor(table, 300.0);
        AnchorPane.setTopAnchor(table, 100.0);
              
        layout.getChildren().addAll(table,SearchField, button,buttonadd);
        
        scene = new Scene(layout, 1920, 1080);
        scene.getStylesheets().add("DebtListStyle.css");
        window.setScene(scene);
        window.show();
    }

    //Get searched item 
    private void Search(String product){
            System.out.println("Product: " + product);
    }
    
    //Dummy data
    public ObservableList<Person> getProduct(){
        ObservableList<Person> products = FXCollections.observableArrayList();
        products.add(new Person("10/11/1998", "John Doe", 20));
        products.add(new Person("10/12/1998", "Mary Jane", 198));
        products.add(new Person("10/13/1998", "Elizabeth Arden", 74));
        products.add(new Person("10/14/1998", "edaj", 12));
        products.add(new Person("10/15/1998", "Fran", 856));
        return products;
    }


}