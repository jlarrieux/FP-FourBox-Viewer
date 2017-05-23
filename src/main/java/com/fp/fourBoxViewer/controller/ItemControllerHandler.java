package com.fp.fourBoxViewer.controller;



/**
 * Created by jlarrieux on 5/23/2017.
 */
public interface ItemControllerHandler {

    public ItemController getController();
    public void setController(ItemController controller);
    void handleController(ItemController controller);


}
