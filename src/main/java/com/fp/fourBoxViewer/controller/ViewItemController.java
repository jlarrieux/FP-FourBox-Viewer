package com.fp.fourBoxViewer.controller;



import com.fp.fourBoxViewer.Entity.Item;
import com.fp.fourBoxViewer.Manager.AbstractContainer;
import com.fp.fourBoxViewer.Util.FX_LookUp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;



public class ViewItemController extends AbstractContainer {

    private Stage dialogStage;
    @FXML private Label name, boxLocation, technician, status, projectType, startDate;

    public ViewItemController(Stage primaryStage, ItemNonCompleteController controller){
        this.controller = controller;
        this.primaryStage = primaryStage;
        buildAndShow();
    }




//    @FXML
//    private void initialize(){
//
//    }



    private void buildAndShow(){
        dialogStage = FX_LookUp.getDialogStage(FX_LookUp.VIEW_ITEM_DIALOG_FXML, this, dialogStage);
        populateGUI();
        dialogStage.showAndWait();
    }


    private void populateGUI(){
        Item item = controller.getItem();
        name.setText(item.getName());
        boxLocation.setText(String.valueOf(item.getBox()));
        technician.setText(item.getTechnician());
        status.setText(item.getStatus());
        projectType.setText(item.getProjectType());
        startDate.setText(String.valueOf(item.getDateStarted()));
    }


    @FXML
    private void HandleCancel(){
        dialogStage.close();
    }



    @Override
    public void handleController(ItemNonCompleteController controller) {

    }








}
