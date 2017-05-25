package com.fp.fourBoxViewer.controller;



import com.fp.fourBoxViewer.Util.ConfirmationDialogObject;
import com.fp.fourBoxViewer.Util.FX_LookUp;
import com.fp.fourBoxViewer.Util.MyLogger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;



/**
 * Created by jlarrieux on 5/23/2017.
 */
public class ItemNonCompleteController extends AbstractItemController implements ItemControllerHandler{


    public static final String ITEM_NON_COMPLETE_FXML = "item.fxml";
    private ContextMenu menu = new ContextMenu();
    private Stage primaryStage;


    public ItemNonCompleteController(Stage primaryStage){
        this.primaryStage = primaryStage;
        load();
    }



    @FXML
    private void initialize(){
        MenuItem editItem = new MenuItem("Edit");
        MenuItem markComplete = new MenuItem("Mark as complete");
        MenuItem delete = new MenuItem("Delete");
        menu.getItems().addAll(editItem,markComplete,delete);
        
        editItem.addEventHandler(ActionEvent.ACTION, event -> handleEditItem());
        markComplete.addEventHandler(ActionEvent.ACTION, event -> handleCompleteItem());
        delete.addEventHandler(ActionEvent.ACTION, event -> handleDeleteItem());
    }



    private void handleDeleteItem() {
        ConfirmationDialogObject object = new ConfirmationDialogObject();
        object.setTitle("Confirm Delete");
        object.setHeader("Confirming Delete Item");
        object.setContentText("Are you sure you want to delete this item?\nPlease consider marking this as complete instead.");
        if(FX_LookUp.ConfirmationDialog(object)){
            item.setToBeDeleted(true);
            item.notifyObservers();
        }
    }



    private void handleCompleteItem() {
        ConfirmationDialogObject object = new ConfirmationDialogObject();
        object.setTitle("Confirm Completed");
        object.setHeader("Confirming Complete");
        object.setContentText("Are you sure you want to mark this Item complete?");
        if(FX_LookUp.ConfirmationDialog(object)){
            item.setComplete(true);
            item.notifyObservers();
        }
    }



    private void handleEditItem() {
        new AddItemController(primaryStage, this, AddItemController.MODE.EDIT, this);
    }



    @FXML
    private void handleRightClick(MouseEvent event){
        if(event.getButton() == MouseButton.SECONDARY) menu.show(pane,event.getScreenX(),event.getScreenY());
    }



    @Override
    protected void populate(){
        name.setText( item.getName());
        name.setFont(Font.font("Arial", FontPosture.ITALIC, 12));

    }



    @Override
    public ItemNonCompleteController getController() {
        return this;
    }



    @Override
    public void setController(ItemNonCompleteController controller) {

    }



    @Override
    public void handleController(ItemNonCompleteController controller) {
        MyLogger.log.info("handling controller");
        item.notifyObservers();
    }



    @Override
    String setView() {
        MyLogger.log.info(FX_LookUp.PREFIX2+ITEM_NON_COMPLETE_FXML);
        return FX_LookUp.PREFIX2 + ITEM_NON_COMPLETE_FXML;
    }
}
