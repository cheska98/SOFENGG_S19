package model;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import model.entries.Product;

public class BooleanCell extends TableCell<Product, Boolean>{

	 private CheckBox checkBox;
     public BooleanCell() {
         checkBox = new CheckBox();
         checkBox.setDisable(false);

         checkBox.selectedProperty().addListener(new ChangeListener<Boolean> () {
             public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                 if(isEditing())
                 {
                     commitEdit(newValue == null ? false : newValue);
                 }
             }
         });
        
         this.setGraphic(checkBox);
         this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
         this.setEditable(true);
     }
     @Override
     public void startEdit() {
         super.startEdit();
         if (isEmpty()) {
             return;
         }
         checkBox.setDisable(false);
         checkBox.requestFocus();
     }
     @Override
     public void cancelEdit() {

         super.cancelEdit();
         checkBox.setDisable(true);
     }
     public void commitEdit(Boolean value) {
         super.commitEdit(value);
         checkBox.setDisable(true);
     }
     @Override
     public void updateItem(Boolean item, boolean empty) {
         super.updateItem(item, empty);
         if (isEmpty()) {
        	 setGraphic(null);
         }else{
        	 this.setGraphic(checkBox);
         }
     }
 }
	
