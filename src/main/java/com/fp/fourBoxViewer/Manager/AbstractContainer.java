package com.fp.fourBoxViewer.Manager;



import javafx.scene.Node;
import javafx.stage.Stage;



/**
 * Created by jlarrieux on 5/18/2017.
 */
public abstract class AbstractContainer {


   protected Stage primaryStage;



   protected Node lookUp(String name){
       return primaryStage.getScene().lookup(name);
   }
}
