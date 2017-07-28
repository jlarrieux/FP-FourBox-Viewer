package com.fp.fourBoxViewer.Manager;



import com.fp.fourBoxViewer.controller.ItemControllerHandler;
import com.fp.fourBoxViewer.controller.ItemNonCompleteController;
import javafx.scene.Node;
import javafx.stage.Stage;



/**
 * Created by jlarrieux on 5/18/2017.
 */
public abstract class AbstractContainer implements ItemControllerHandler {


   protected Stage primaryStage;
   protected ItemNonCompleteController controller;

   protected Node lookUp(String name){
       return primaryStage.getScene().lookup(name);
   }


    @Override
    public ItemNonCompleteController getController() {
        return controller;
    }


    @Override
    public void setController(ItemNonCompleteController controller) {
        this.controller = controller;
    }
}
