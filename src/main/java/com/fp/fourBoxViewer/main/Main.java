package com.fp.fourBoxViewer.main;



import com.fp.fourBoxViewer.Util.Constants;
import com.fp.fourBoxViewer.Util.FX_LookUp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



/**
 * Created by jlarrieux on 5/17/2017.
 */
public class Main extends Application{


    private BorderPane root;
    private Stage primaryStage;
    private VBox urgentImportant, urgentNotImportant, notUrgentImportant, notUrgentNotImportant;
    private Button add;

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = FXMLLoader.load(getClass().getClassLoader().getResource(FX_LookUp.VIEWS_ROOT_LAYOUT_FXML));
        this.primaryStage = primaryStage;

        primaryStage.setTitle(Constants.FOUR_BOX_VIEWER);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(this.getClass().getResource("/css/jfoenix-components.css").toExternalForm());
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        initializeIDs();
    }




    public static void main(String[] args){
        launch(args);
    }


    private void initializeIDs(){

        urgentImportant = (VBox) lookUp(FX_LookUp.VBOX_URGENT_IMPORTANT);
        urgentNotImportant = (VBox) lookUp(FX_LookUp.VBOX_URGENT_NOT_IMPORTANT);
        notUrgentImportant = (VBox) lookUp(FX_LookUp.VBOX_NOT_URGENT_IMPORTANT);
        notUrgentNotImportant = (VBox) lookUp(FX_LookUp.VBOX_NOT_URGENT_NOT_IMPORTANT);
        add = (Button) lookUp(FX_LookUp.BUTTON_ADD);

    }



    private Node lookUp(String name){
        return primaryStage.getScene().lookup(name);
    }









}
