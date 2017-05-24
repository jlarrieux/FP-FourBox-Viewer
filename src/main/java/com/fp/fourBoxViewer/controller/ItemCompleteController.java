package com.fp.fourBoxViewer.controller;



import com.fp.fourBoxViewer.Util.FX_LookUp;
import com.fp.fourBoxViewer.Util.MyLogger;



/**
 * Created by jlarrieux on 5/24/2017.
 */
public class ItemCompleteController extends AbstractItemController{


    public static final String ITEM_COMPLETE_FXML = "itemComplete.fxml";


    public ItemCompleteController(){
        load();

    }

    @Override
    String setView() {
        return FX_LookUp.PREFIX2 + ITEM_COMPLETE_FXML;
    }



    @Override
    protected void populate() {
        if(item.getDateCompleted()==null) MyLogger.log.info("NULL datecomplete");
        name.setText(item.getName());
        box.setText(String.valueOf(item.getBox()));
        dateStarted.setText(item.getDateStarted().toString());
        dateCompleted.setText(item.getDateCompleted().toString());
    }
}
