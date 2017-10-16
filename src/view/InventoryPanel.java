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

public class InventoryPanel extends Application {

    Stage window;
    Scene scene;
    Button button;
    Button buttonadd;
    TableView<Product> table;
    TextField nameInput, priceInput, quantityInput;


    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Inventory");

        //Name column
        TableColumn<Product, String> itemColumn = new TableColumn<>("Item");
        itemColumn.setMinWidth(200);
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("item"));

        //Price column
        TableColumn<Product, Double> unitcostColumn = new TableColumn<>("Unit Cost");
        unitcostColumn.setMinWidth(150);
        unitcostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Quantity column
        TableColumn<Product, String> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(120);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
           
        //Form
        TextField SearchField = new TextField();
        SearchField.setPrefWidth(400);

        button = new Button("Search");
        button.setOnAction( e -> Search(SearchField.getText()));
        
        table = new TableView<>();
        table.setItems(getProduct());
        table.getColumns().addAll(itemColumn, unitcostColumn, quantityColumn);
        table.setRowFactory( tv -> {
            TableRow<Product> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Product rowData = row.getItem();
                    InventoryEdit.display(rowData,"Edit Item");
                }
            });
            return row;
        });
        
        buttonadd = new Button("Add Item");
        buttonadd.setOnAction( e -> InventoryAdd.display(new Product("None",0.0,0),"Add Item",table));

        
        //Layout
        AnchorPane layout = new AnchorPane();
        layout.setPadding(new Insets(20, 20, 20, 20));
        AnchorPane.setRightAnchor(button, 550.0);
        AnchorPane.setTopAnchor(button, 50.0);
        AnchorPane.setLeftAnchor(buttonadd, 300.0);
        AnchorPane.setTopAnchor(buttonadd, 510.0);
        AnchorPane.setTopAnchor(SearchField, 50.0);
        AnchorPane.setLeftAnchor(SearchField, 300.0);
        AnchorPane.setLeftAnchor(table, 300.0);
        AnchorPane.setTopAnchor(table, 100.0);
              
        layout.getChildren().addAll(table,SearchField, button,buttonadd);
        
        scene = new Scene(layout, 1920, 1080);
        scene.getStylesheets().add("InventoryPanelStyle.css");
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
        products.add(new Product("Laptop", 859.00, 20));
        products.add(new Product("Bouncy Ball", 2.49, 198));
        products.add(new Product("Toilet", 99.00, 74));
        products.add(new Product("The Notebook DVD", 19.99, 12));
        products.add(new Product("Corn", 1.49, 856));
        return products;
    }


}