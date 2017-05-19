package com.fp.fourBoxViewer.controller;



import com.fp.fourBoxViewer.Manager.AbstractContainer;
import com.fp.fourBoxViewer.Manager.FourBoxManager;
import com.fp.fourBoxViewer.Util.FX_LookUp;
import com.fp.fourBoxViewer.Util.MyLogger;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;



/**
 * Created by jlarrieux on 5/18/2017.
 */
public class AddItemController extends AbstractContainer{
    @FXML private JFXTextField name, boxLocation;
    @FXML private JFXTextArea description;
    @FXML private JFXDatePicker startDatePicker;
   private Stage dialogStage;



    public AddItemController(Stage primaryStage){
        this.primaryStage = primaryStage;
        buildAndShow();
        MyLogger.log.trace("Constructor");
    }



    @FXML
    private void getInput(){
        MyLogger.log.trace("getinput");
    }


    private void buildAndShow(){
        MyLogger.log.trace("building + " );
        if(primaryStage==null) MyLogger.log.trace("null primary stage");
        FXMLLoader loader = new FXMLLoader(FourBoxManager.class.getResource(FX_LookUp.ADD_ITEM_DIALOG_FXML));
        loader.setController(this);
        GridPane pane = (GridPane) FX_LookUp.LoadResource(loader);
        dialogStage = FX_LookUp.createDialogStage("test",primaryStage, pane );

        dialogStage.showAndWait();
    }


    @FXML
    private void HandleCancel(){
        MyLogger.log.trace("handle cancel");
        dialogStage.close();
    }


}
