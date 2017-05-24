package com.fp.fourBoxViewer.controller;



/**
 * Created by jlarrieux on 5/23/2017.
 */
public interface ItemControllerHandler {

    public ItemNonCompleteController getController();
    public void setController(ItemNonCompleteController controller);
    void handleController(ItemNonCompleteController controller);


}
