package com.fp.fourBoxViewer.controller;



import com.fp.fourBoxViewer.Entity.Item;
import com.fp.fourBoxViewer.Util.FX_LookUp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;



/**
 * Created by jlarrieux on 5/23/2017.
 */
public class ItemController {

    private Item item;
    private final static String ITEM_FXML = FX_LookUp.PREFIX2+"item.fxml";
    private GridPane pane= new GridPane();
    @FXML private Label name, description;




    public ItemController(){
        FXMLLoader loader = FX_LookUp.getLoader(ITEM_FXML);
        loader.setRoot(pane);
        loader.setController(this);
        FX_LookUp.LoadResource(loader);
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

    private void populate(){
        this.name.setText( item.getName());
        this.description.setText(item.getDescription());

    }
}
