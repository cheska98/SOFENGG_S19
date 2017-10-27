import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class DisplayPanel extends Application {

    Stage window;
    Scene scene;
    Button button;
    TableView<Product> table;
    TextField nameInput, priceInput, quantityInput;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Display");

        //Name column
        TableColumn<Product, String> itemColumn = new TableColumn<>("Item");
        itemColumn.setMinWidth(300);
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("item"));

        //Quantity column
        TableColumn<Product, Integer> quantityColumn = new TableColumn<>("Quantity/CM/KG");
        quantityColumn.setMinWidth(170);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        //Form
        TextField SearchField = new TextField();
        SearchField.setPrefWidth(400);

        button = new Button("Search");
        button.setOnAction( e -> Search(SearchField.getText()));

        table = new TableView<>();
        table.setItems(getProduct());
        table.setEditable(true);
        quantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>(){
            @Override
            public String toString(Integer object) {
                return object.toString();
            }

            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }

        }));
        table.getColumns().addAll(itemColumn,quantityColumn);
        
        
        //Layout
        AnchorPane layout = new AnchorPane();
        layout.setPadding(new Insets(20, 20, 20, 20));
        AnchorPane.setRightAnchor(button, 550.0);
        AnchorPane.setTopAnchor(button, 50.0);
        AnchorPane.setTopAnchor(SearchField, 50.0);
        AnchorPane.setLeftAnchor(SearchField, 300.0);
        AnchorPane.setLeftAnchor(table, 300.0);
        AnchorPane.setTopAnchor(table, 100.0);
              
        layout.getChildren().addAll(table,SearchField, button);
        
        scene = new Scene(layout, 1920, 1080);
        scene.getStylesheets().add("DisplayPanelStyle.css");
        window.setScene(scene);
        window.show();
    }
 
    
    //Get searched item 
    private void Search(String product){
            System.out.println("Product: " + product);
    }
    
    //Dummy data
    public ObservableList<Product> getProduct(){
        ObservableList<Product> products = FXCollections.observableArrayList();
        products.add(new Product("Laptop",20));
        products.add(new Product("Bouncy Ball",198));
        products.add(new Product("Toilet",74));
        products.add(new Product("The Notebook DVD",12));
        products.add(new Product("Corn",856));
        return products;
    }

}