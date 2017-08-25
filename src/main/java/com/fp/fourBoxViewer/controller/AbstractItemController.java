package com.fp.fourBoxViewer.controller;



import com.fp.fourBoxViewer.Entity.Item;
import com.fp.fourBoxViewer.Util.FX_LookUp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;



/**
 * Created by jlarrieux on 5/24/2017.
 */
public abstract class AbstractItemController {


    protected Item item;
    protected  String ITEM_FXML = setView();
    @FXML Label name, box, dateStarted, dateCompleted, technician, type, status;
    protected GridPane pane = new GridPane();

    abstract String setView();
    protected abstract void populate();


    protected void load() {
        FXMLLoader loader = FX_LookUp.getLoader(ITEM_FXML);
        loader.setRoot(pane);
        loader.setController(this);
        FX_LookUp.loadResource(loader);
    }


    public Item getItem() {
        return item;
    }



    public void setItem(Item item) {
        this.item = item;
        populate();
    }

    public Node getView(){
        return pane;
    }

}
