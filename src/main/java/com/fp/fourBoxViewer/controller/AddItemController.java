package com.fp.fourBoxViewer.controller;



import com.fp.fourBoxViewer.Entity.Item;
import com.fp.fourBoxViewer.Manager.AbstractContainer;
import com.fp.fourBoxViewer.Util.FX_LookUp;
import com.fp.fourBoxViewer.Util.MyLogger;
import com.github.jlarrieux.main.NumericValidator;
import com.github.jlarrieux.main.ValidationObject.AbstractComponentValidationObject;
import com.github.jlarrieux.main.ValidationObject.JavaFXValidationObject;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;



/**
 * Created by jlarrieux on 5/18/2017.
 */
public class AddItemController extends AbstractContainer{
    @FXML private JFXTextField name, boxLocation;
    @FXML private TextField boxLocation2;
    @FXML private JFXTextArea description;
    @FXML private JFXDatePicker startDatePicker;
    private Stage dialogStage;
    private Item item;
    private ItemController itemController;
    private ItemControllerHandler handler;



    public AddItemController(Stage primaryStage, ItemControllerHandler handler){
        this.handler = handler;
        this.primaryStage = primaryStage;
        buildAndShow();
        MyLogger.log.trace("Constructor");
    }


    @FXML
    private void initialize(){
        startDatePicker.setValue(LocalDate.now());
    }

    @FXML
    private void getInput(){
        MyLogger.log.debug("getinput");
        if(!validate()){
            if(itemController ==null) executeAdd();
//            else executeEdit();
        }


    }



    private void executeAdd() {
        item = new Item();
        populateItem();
        MyLogger.log.trace(item.toString());
        ItemController  controller = new ItemController();
        controller.setItem(item);
        handler.handleController(controller);
        dialogStage.close();

    }



    private void populateItem(){
        item.setName(name.getText());
        item.setDescription(description.getText());
        item.setBox(Integer.parseInt(boxLocation.getText()));
        item.setDateStarted(startDatePicker.getValue());
    }



    private boolean validate(){
        NumericValidator val = new NumericValidator();
        ArrayList<AbstractComponentValidationObject> validationObjects = new ArrayList<>();
        validationObjects.add(new JavaFXValidationObject(boxLocation, "Box Location", NumericValidator.NumberType.INTEGER));
        validationObjects.add(new JavaFXValidationObject(name,"Name", NumericValidator.NumberType.Plain));
        return val.validate(validationObjects);
    }

    private void buildAndShow(){
        MyLogger.log.trace("building + " );
        if(primaryStage==null) MyLogger.log.trace("null primary stage");
         dialogStage= FX_LookUp.getDialogStage(FX_LookUp.ADD_ITEM_DIALOG_FXML,this,dialogStage);
         dialogStage.showAndWait();
    }


    @FXML
    private void HandleCancel(){
        MyLogger.log.trace("handle cancel");
        dialogStage.close();
    }



    @Override
    public void handleController(ItemController controller) {
        this.itemController = controller;
    }
}
