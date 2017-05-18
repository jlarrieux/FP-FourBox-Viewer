package com.fp.fourBoxViewer.Manager;



import com.fp.fourBoxViewer.Util.FX_LookUp;
import com.fp.fourBoxViewer.Util.MyLogger;
import com.fp.fourBoxViewer.controller.AddItemController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



/**
 * Created by jlarrieux on 5/18/2017.
 */
public class FourBoxManager extends AbstractContainer {


    private VBox urgentImportant, urgentNotImportant, notUrgentImportant, notUrgentNotImportant;
    private Button add;


    public FourBoxManager(Stage primaryStage){
        this.primaryStage = primaryStage;
        Initialize();
    }


    private void Initialize(){
        urgentImportant = (VBox) lookUp(FX_LookUp.VBOX_URGENT_IMPORTANT);
        urgentNotImportant = (VBox) lookUp(FX_LookUp.VBOX_URGENT_NOT_IMPORTANT);
        notUrgentImportant = (VBox) lookUp(FX_LookUp.VBOX_NOT_URGENT_IMPORTANT);
        notUrgentNotImportant = (VBox) lookUp(FX_LookUp.VBOX_NOT_URGENT_NOT_IMPORTANT);
        add = (Button) lookUp(FX_LookUp.BUTTON_ADD);
        add.addEventHandler(ActionEvent.ACTION, event -> buildAndShow());
    }




    private void buildAndShow(){
        MyLogger.log.trace("build from 4box");
       AddItemController controller= new AddItemController(primaryStage);
    }





}
