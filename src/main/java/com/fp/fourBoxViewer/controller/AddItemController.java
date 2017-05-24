package com.fp.fourBoxViewer.controller;



import com.fp.fourBoxViewer.Entity.Item;
import com.fp.fourBoxViewer.Manager.AbstractContainer;
import com.fp.fourBoxViewer.Util.FX_LookUp;
import com.fp.fourBoxViewer.Util.MyLogger;
import com.github.jlarrieux.main.NumericValidator;
import com.github.jlarrieux.main.ValidationObject.AbstractComponentValidationObject;
import com.github.jlarrieux.main.ValidationObject.JavaFXValidationObject;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;



/**
 * Created by jlarrieux on 5/18/2017.
 */
public class AddItemController extends AbstractContainer{
    @FXML private JFXTextField name, boxLocation;
    @FXML private JFXDatePicker startDatePicker;
    @FXML private Label label;
    private Stage dialogStage;
    private Item item;
    private ItemNonCompleteController itemNonCompleteController;
    private ItemControllerHandler handler;
    private MODE mode;

    public enum MODE{
        EDIT, ADD
    }



    public AddItemController(Stage primaryStage, ItemControllerHandler handler, MODE mode, ItemNonCompleteController controller){
        this.controller = controller;
        this.mode = mode;
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
            if(mode == MODE.ADD) executeAdd();
            else if(mode ==MODE.EDIT) executeEdit();
        }


    }



    private void executeAdd() {
        item = new Item();
        populateItem();
        MyLogger.log.trace(item.toString());
        ItemNonCompleteController controller = new ItemNonCompleteController(primaryStage);
        controller.setItem(item);
        handler.handleController(controller);
        dialogStage.close();

    }

    private void executeEdit(){
        item = controller.getItem();
        populateItem();
        controller.setItem(item);
        handler.handleController(controller);
        dialogStage.close();
    }


    private void populateItem(){
        item.setName(name.getText());
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
         if(mode== MODE.EDIT){
             populateGUI();
             label.setText("Edit Item");
         }
         dialogStage.showAndWait();
    }

    private void populateGUI(){
        Item item1 =controller. getItem();
        name.setText(item1.getName());
        boxLocation.setText(String.valueOf(item1.getBox()));
        startDatePicker.setValue(item1.getDateStarted());
    }

    @FXML
    private void HandleCancel(){
        MyLogger.log.trace("handle cancel");
        dialogStage.close();
    }



    @Override
    public void handleController(ItemNonCompleteController controller) {
        this.itemNonCompleteController = controller;
    }



}
