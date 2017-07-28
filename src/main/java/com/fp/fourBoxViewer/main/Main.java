package com.fp.fourBoxViewer.main;



import com.fp.fourBoxViewer.Manager.FourBoxManager;
import com.fp.fourBoxViewer.Util.FX_LookUp;
import com.fp.fourBoxViewer.Util.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



/**
 * Created by jlarrieux on 5/17/2017.
 */
public class Main extends Application{



    private Stage primaryStage;


    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root = FXMLLoader.load(getClass().getClassLoader().getResource(FX_LookUp.VIEWS_ROOT_LAYOUT_FXML));
        this.primaryStage = primaryStage;

        primaryStage.setTitle(Constants.FOUR_BOX_VIEWER);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(this.getClass().getResource("/css/jfoenix-components.css").toExternalForm());
        scene.getStylesheets().add(this.getClass().getResource("/css/RedBorder.css").toExternalForm());

        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        initializeContainers();
    }




    public static void main(String[] args){
        launch(args);
    }


    private void initializeContainers(){
        new FourBoxManager(primaryStage);

    }












}
