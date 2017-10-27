import com.sun.glass.ui.TouchInputSupport;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {

    private String item;
    private int quantity;
    private double unitcost;
    
    public Product(){
        this.item = "";
        this.quantity=0;
    }

    public Product(String name,int quantity){
        this.item = name;
        this.quantity = quantity;
    }
    
    public Product(String name, double price, int quantity){
        this.item = name;
        this.unitcost = price;
        this.quantity = quantity;
    }

    public String getItem() {
        return item;
    }

    public void setItemName(String name) {
        this.item = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return unitcost;
    }

    public void setPrice(double price) {
        this.unitcost = price;
    }

}