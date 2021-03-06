package com.fp.fourBoxViewer.Manager;



import com.fp.fourBoxViewer.Entity.Item;
import com.fp.fourBoxViewer.Util.Constants;
import com.fp.fourBoxViewer.Util.FX_LookUp;
import com.fp.fourBoxViewer.Util.MyLogger;
import com.fp.fourBoxViewer.controller.AbstractItemController;
import com.fp.fourBoxViewer.controller.AddItemController;
import com.fp.fourBoxViewer.controller.ItemCompleteController;
import com.fp.fourBoxViewer.controller.ItemNonCompleteController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Side;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by jlarrieux on 5/18/2017.
 */
public class FourBoxManager extends AbstractContainer  implements MainContainer, ChangeListener{


    private VBox urgentImportant, urgentNotImportant, notUrgentImportant, notUrgentNotImportant, complete;
    private Button add;
    private ItemManager itemManager = new ItemManager(this);
    private ComboBox<String> orderBy;






    private enum itemControllerType{
        COMPLETE, NON_COMPLETE
    }


    public FourBoxManager(Stage primaryStage){
        this.primaryStage = primaryStage;
        Initialize();

        loadNonCompleteInProperContainer();
        loadComplete();
    }


    private void Initialize(){
        urgentImportant = (VBox) lookUp(FX_LookUp.VBOX_URGENT_IMPORTANT);
        urgentImportant.setFillWidth(true);
        urgentNotImportant = (VBox) lookUp(FX_LookUp.VBOX_URGENT_NOT_IMPORTANT);
        notUrgentImportant = (VBox) lookUp(FX_LookUp.VBOX_NOT_URGENT_IMPORTANT);
        notUrgentNotImportant = (VBox) lookUp(FX_LookUp.VBOX_NOT_URGENT_NOT_IMPORTANT);
        orderBy = (ComboBox) lookUp(FX_LookUp.ORDER_BY);
        complete = (VBox)lookUp(FX_LookUp.VBOX_COMPLETE);
        add = (Button) lookUp(FX_LookUp.BUTTON_ADD);

        buildOrderBy();
        add.addEventHandler(ActionEvent.ACTION, event -> buildAndShow());
        orderBy.valueProperty().addListener(this);
    }


    private void buildOrderBy(){
        orderBy.getItems().addAll(Constants.ORDER_BY_NAME_ASC,
                Constants.ORDER_BY_NAME_DES,
                Constants.ORDER_BY_LAST_BOX_NUMBER_ASC,
                Constants.ORDER_BY_LAST_BOX_NUMBER_DES,
                Constants.ORDER_BY_START_DATE_ASC,
                Constants.ORDER_BY_START_DATE_DES,
                Constants.ORDER_BY_COMPLETED_DATE_ASC,
                Constants.ORDER_BY_COMPLETED_DATE_DES);
    }

    private void buildAndShow(){
        MyLogger.log.trace("build from 4box");
        new AddItemController(primaryStage, this, AddItemController.MODE.ADD, null);
    }



    @Override
    public void handleController(ItemNonCompleteController controller) {
        MyLogger.log.debug("About to handle controller\n"+controller.getItem().toString());
        assignItemController(controller, true);

    }


    private void assignItemController(ItemNonCompleteController controller, boolean updateOrCreate){
//        MyLogger.log.debug("Number of children before: "+ urgentImportant.getChildren().size());
        Item item = controller.getItem();
        loadItem(controller);
        if(updateOrCreate) itemManager.updateOrCreate(item);
//        MyLogger.log.debug("Number of children after: "+ urgentImportant.getChildren().size());
    }



    @Override
    public void loadNonCompleteInProperContainer() {
        clearAllNonCompleteItem();
        for(Item item: itemManager.getNonCompletelist()){
            ItemNonCompleteController controller = (ItemNonCompleteController) createItemController(item, itemControllerType.NON_COMPLETE);
            assignItemController(controller, false);
        }

    }



    @Override
    public void loadComplete() {
        clearAllCompleteItem();
        for(Item item: itemManager.getCompleteList()){
            ItemCompleteController controller = (ItemCompleteController) createItemController(item, itemControllerType.COMPLETE);
            complete.getChildren().add(controller.getView());

        }

    }

    private AbstractItemController createItemController(Item item, itemControllerType type){
        AbstractItemController controller= createController(type);
        controller.setItem(item);
        return controller;
    }


    private AbstractItemController createController(itemControllerType type){
        if(type==itemControllerType.NON_COMPLETE) return new ItemNonCompleteController(primaryStage);
        else if(type==itemControllerType.COMPLETE) return new ItemCompleteController();
        return null;
    }


    private void clearAllNonCompleteItem(){
        urgentImportant.getChildren().clear();
        urgentNotImportant.getChildren().clear();
        notUrgentImportant.getChildren().clear();
        notUrgentNotImportant.getChildren().clear();
    }

    private void clearAllCompleteItem(){
        complete.getChildren().clear();
    }

    private void loadItem(ItemNonCompleteController controller){
        Item item = controller.getItem();
        int box = item.getBox();
//        GridPane gridPane = (GridPane) controller.getView();
        if(!item.isComplete()) {
            if (box == 1)     urgentImportant.getChildren().add(controller.getView());

            else if (box == 2) notUrgentImportant.getChildren().add(controller.getView());
            else if (box == 3) urgentNotImportant.getChildren().add(controller.getView());
            else if (box == 4) notUrgentNotImportant.getChildren().add(controller.getView());
        }
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        String s = newValue.toString();
        itemManager.sortCompleteList(s);

    }

    private void loadBackground(){
        setCustomBackgroundText("1", urgentImportant);
    }

    private void setCustomBackgroundText(String myText, VBox box){
        Text text = new Text(myText);
        text.setFill(Color.RED);
        WritableImage image = text.snapshot(new SnapshotParameters(), null);
        double imageVPos = (box.getHeight() - image.getHeight()) / 2;
        BackgroundImage backImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                new BackgroundPosition(Side.LEFT, 7D, false, Side.TOP, imageVPos, false),
                new BackgroundSize(image.getWidth(), image.getHeight(), false, false, false, false));
        List<BackgroundImage> images = new ArrayList<>();
        images.add(backImage);
        box.setBackground(new Background(box.getBackground().getFills(), images));
    }



}
