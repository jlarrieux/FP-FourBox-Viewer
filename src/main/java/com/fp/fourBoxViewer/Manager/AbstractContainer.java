package com.fp.fourBoxViewer.Manager;



import com.fp.fourBoxViewer.controller.ItemController;
import com.fp.fourBoxViewer.controller.ItemControllerHandler;
import javafx.scene.Node;
import javafx.stage.Stage;



/**
 * Created by jlarrieux on 5/18/2017.
 */
public abstract class AbstractContainer implements ItemControllerHandler{


   protected Stage primaryStage;
   protected ItemController controller;

   protected Node lookUp(String name){
       return primaryStage.getScene().lookup(name);
   }


    @Override
    public ItemController getController() {
        return controller;
    }


    @Override
    public void setController(ItemController controller) {
        this.controller = controller;
    }
}
