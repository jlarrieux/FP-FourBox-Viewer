package com.fp.fourBoxViewer.Manager;



import com.fp.fourBoxViewer.Entity.Item;
import com.fp.fourBoxViewer.Util.FX_LookUp;
import com.fp.fourBoxViewer.Util.MyLogger;
import com.fp.fourBoxViewer.controller.AddItemController;
import com.fp.fourBoxViewer.controller.ItemController;
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
    private ItemManager itemManager = new ItemManager();



    public FourBoxManager(Stage primaryStage){
        this.primaryStage = primaryStage;
        Initialize();
        populate();
    }


    private void Initialize(){
        urgentImportant = (VBox) lookUp(FX_LookUp.VBOX_URGENT_IMPORTANT);
        urgentNotImportant = (VBox) lookUp(FX_LookUp.VBOX_URGENT_NOT_IMPORTANT);
        notUrgentImportant = (VBox) lookUp(FX_LookUp.VBOX_NOT_URGENT_IMPORTANT);
        notUrgentNotImportant = (VBox) lookUp(FX_LookUp.VBOX_NOT_URGENT_NOT_IMPORTANT);
        add = (Button) lookUp(FX_LookUp.BUTTON_ADD);
        add.addEventHandler(ActionEvent.ACTION, event -> buildAndShow());
    }


    private void populate(){
        for(Item item: itemManager.getList()){
            ItemController controller = new ItemController();
            controller.setItem(item);
            assignItemController(controller, false);
        }
    }


    private void buildAndShow(){
        MyLogger.log.trace("build from 4box");
       AddItemController controller= new AddItemController(primaryStage, this);
    }



    @Override
    public void handleController(ItemController controller) {
        MyLogger.log.debug("About to handle controller\n"+controller.getItem().toString());
        assignItemController(controller, true);

    }


    private void assignItemController(ItemController controller, boolean updateOrCreate){
        MyLogger.log.debug("Number of children before: "+ urgentImportant.getChildren().size());
        Item item = controller.getItem();
        if(item.getBox()==1) urgentImportant.getChildren().add(controller.getView());

        if(updateOrCreate) itemManager.updateOrCreate(item);

        MyLogger.log.debug("Number of children after: "+ urgentImportant.getChildren().size());
    }
}
