package com.fp.fourBoxViewer.Manager;



import com.fp.fourBoxViewer.Util.Constants;
import javafx.scene.Node;
import javafx.stage.Stage;



/**
 * Created by jlarrieux on 5/18/2017.
 */
public abstract class AbstractContainer {


   protected Stage primaryStage;
   protected Constants.MODE mode = Constants.MODE.NEW;


   protected Node lookUp(String name){
       return primaryStage.getScene().lookup(name);
   }
}
