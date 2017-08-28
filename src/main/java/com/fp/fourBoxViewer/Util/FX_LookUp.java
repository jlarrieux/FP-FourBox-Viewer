package com.fp.fourBoxViewer.Util;



import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Optional;



/**
 * Created by jlarrieux on 5/17/2017.
 */
public class FX_LookUp {

    private static final String PREFIX = "views/";
    public static final String PREFIX2 = "/views/";
    private static final String CSS_PREFIX = "/css/";
    public static final String VIEWS_ROOT_LAYOUT_FXML = PREFIX + "RootLayout.fxml";
    public static final String VBOX_URGENT_IMPORTANT = "#vBoxUrgentImportant";
    public static final String VBOX_COMPLETE = "#vBoxComplete";
    public static final String VBOX_URGENT_NOT_IMPORTANT = "#vBoxUrgentNotImportant";
    public static final String VBOX_NOT_URGENT_NOT_IMPORTANT = "#vBoxNotUrgentNotImportant";
    public static final String VBOX_NOT_URGENT_IMPORTANT= "#vboxNotUrgentImportant";
    public static final String BUTTON_ADD = "#buttonAdd";
    public static final String ORDER_BY = "#orderBy";
    public static final String MY_LIGHT = getCSSResource("myLight.css");
    public static final String JMETRO_LIGHT_THEME_CSS =  getCSSResource("JMetroLight.css");
    public static final String ADD_ITEM_DIALOG_FXML =PREFIX2+ "addItemDialog.fxml";
    public static final String VIEW_ITEM_DIALOG_FXML = PREFIX2+"viewItemDialog.fxml";

    private static String getCSSResource(String name){
        return  FX_LookUp.class.getResource(CSS_PREFIX+name).toExternalForm();
    }

    public static Pane loadResource(FXMLLoader loader){
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
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initOwner(owner);

        dialogStage.setScene(new Scene(pane));
        return dialogStage;
    }


    public static Stage getDialogStage(String fxml, Object controller, Stage primaryStage){
        MyLogger.log.trace("Getting loader");
        FXMLLoader loader = getLoader(fxml);
        loader.setController(controller);
        GridPane pane = (GridPane) FX_LookUp.loadResource(loader);
        return createDialogStage("",primaryStage,pane);

    }

    public static FXMLLoader getLoader(String fxml){
        return new FXMLLoader(FX_LookUp.class.getClass().getResource(fxml));
    }



    public static boolean confirmationDialog(ConfirmationDialogObject object){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(object.getTitle());
        alert.setHeaderText(object.getHeader());
        alert.setContentText(object.getContentText());
        Optional<ButtonType> result = alert.showAndWait();

        if(result.get()== ButtonType.OK) return true;
        else return false;
    }




}
