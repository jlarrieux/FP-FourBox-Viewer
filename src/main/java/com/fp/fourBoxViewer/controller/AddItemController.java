package com.fp.fourBoxViewer.controller;



import com.fp.fourBoxViewer.Entity.Item;
import com.fp.fourBoxViewer.Entity.Type;
import com.fp.fourBoxViewer.Manager.AbstractContainer;
import com.fp.fourBoxViewer.Util.FX_LookUp;
import com.fp.fourBoxViewer.Util.MyLogger;
import com.github.jlarrieux.main.NumericValidator;
import com.github.jlarrieux.main.ValidationObject.AbstractComponentValidationObject;
import com.github.jlarrieux.main.ValidationObject.JavaFXValidationObject;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;



/**
 * Created by jlarrieux on 5/18/2017.
 */
public class AddItemController extends AbstractContainer {
    @FXML private JFXTextField name, technician;
    @FXML private JFXTextArea status;
    @FXML private ComboBox<String> boxLocation, projectType;
    @FXML private JFXDatePicker startDatePicker;
    @FXML private Label label;
    @FXML private JFXButton addButton;
    private Stage dialogStage;
    private Item item;

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
        boxLocation.getItems().addAll("1","2","3","4");
        projectType.getItems().addAll( Arrays.stream(Type.values()).map(e->e.toString()).collect(Collectors.toList()));
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
        if(item==null) System.out.print("Item is null");
        if(name==null) System.out.print("Name is null");
        item.setName(name.getText());
        item.setBox(Integer.parseInt(boxLocation.getValue()));
        item.setDateStarted(startDatePicker.getValue());
        item.setTechnician(technician.getText());
        item.setStatus(status.getText());
        item.setProjectType(projectType.getValue());
    }



    private boolean validate(){
        NumericValidator val = new NumericValidator();
        ArrayList<AbstractComponentValidationObject> validationObjects = new ArrayList<>();
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
             addButton.setText("Update");
         }
         else{
             label.setText("Add item");
             addButton.setText("Add");
         }
         dialogStage.showAndWait();
    }

    private void populateGUI(){
        Item item1 =controller.getItem();
        name.setText(item1.getName());
        boxLocation.setValue(String.valueOf(item1.getBox()));
        startDatePicker.setValue(item1.getDateStarted());
        technician.setText(item1.getTechnician());
        status.setText(item1.getStatus());
        projectType.setValue(item1.getProjectType());

    }

    @FXML
    private void HandleCancel(){
        MyLogger.log.trace("handle cancel");
        dialogStage.close();
    }



    @Override
    public void handleController(ItemNonCompleteController controller) {
//        this.itemNonCompleteController = controller;
    }



}
