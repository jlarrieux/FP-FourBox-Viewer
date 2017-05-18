package com.fp.fourBoxViewer.Util;



import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;



/**
 * Created by jlarrieux on 5/17/2017.
 */
public class FX_LookUp {

    private static final String PREFIX = "views/";
    private static final String PREFIX2 = "/views/";
    private static final String CSS_PREFIX = "/css/";
    public static final String VIEWS_ROOT_LAYOUT_FXML = PREFIX + "RootLayout.fxml";
    public static final String VBOX_URGENT_IMPORTANT = "#vBoxUrgentImportant";
    public static final String ADD_ITEM_NAME = "#addItemNameTextField";
    public static final String ADD_ITEM_DESCRIPTION = "#addItemDescriptionTextArea";
    public static final String ADD_ITEM_BOXLOCATION = "#addItemBoxLocationTextField";
    public static final String ADD_ITEM_START_DATEPICKER = "#addItemStartDatePicker";
    public static final String ADD_ITEM_ADD_BUTTON = "#addItemAddButton";
    public static final String ADD_ITEM_CANCEL_BUTTON = "#addItemCancelButton";
    public static final String VBOX_URGENT_NOT_IMPORTANT = "#vBoxUrgentNotImportant";
    public static final String VBOX_NOT_URGENT_NOT_IMPORTANT = "#vBoxNotUrgentNotImportant";
    public static final String VBOX_NOT_URGENT_IMPORTANT= "#vBoxNotUrgentImportant";
    public static final String BUTTON_ADD = "#buttonAdd";
    public static final String MY_LIGHT = getCSSResource("myLight.css");
    public static final String JMETRO_LIGHT_THEME_CSS =  getCSSResource("JMetroLight.css");
    public static final String ADD_ITEM_DIALOG_FXML =PREFIX2+ "addItemDialog.fxml";

    private static String getCSSResource(String name){
        return  FX_LookUp.class.getResource(CSS_PREFIX+name).toExternalForm();
    }

    public static Pane LoadResource(FXMLLoader loader){
        try {
            return loader.load();
        } catch (IOException E){
            E.printStackTrace();
        }
        return null;
    }


    public static Stage createDialogStage(String title, Window owner, Pane pane){
        Stage dialogStage = new Stage();
        dialogStage.setTitle(title);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(owner);

        dialogStage.setScene(new Scene(pane));
        return dialogStage;
    }
}
