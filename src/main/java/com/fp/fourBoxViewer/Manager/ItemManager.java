package com.fp.fourBoxViewer.Manager;





import com.fp.fourBoxViewer.Boundaries.HibernateManager;
import com.fp.fourBoxViewer.Comparators.ItemComparators;
import com.fp.fourBoxViewer.Entity.Item;
import com.fp.fourBoxViewer.Util.Constants;
import com.fp.fourBoxViewer.Util.MyLogger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;



/**
 * Created by jlarrieux on 5/16/2017.
 */
public class ItemManager implements Observer, ListChangeListener {

    private HibernateManager hibernateManager = HibernateManager.getInstance();
    private ArrayList<Item> nonCompletelist = new ArrayList<>();
    private ArrayList<Item> completeList = new ArrayList<>();
    private ObservableList<Item> observableList = FXCollections.observableArrayList(completeList);
    MainContainer mainContainer;

    public ItemManager(MainContainer mainContainer){
        this.mainContainer = mainContainer;
        loadNonComplete();
        loadComplete();
        printAll();
        observableList.addListener(this);
    }

    private void loadNonComplete(){
        for(Item item: hibernateManager.loadAll(false)){
            MyLogger.log.info("finish calling hibernate");
            addToNonComplete(item);
        }
    }

    private void loadComplete(){
        for(Item item: hibernateManager.loadAll(true)) addToComplete(item);
    }


    public int size(){
        return nonCompletelist.size();
    }

    public void addToNonComplete(Item item){
        MyLogger.log.info("adding observer");
        item.addObserver(this);
        nonCompletelist.add(item);
    }

    public void addToComplete(Item item){
        observableList.add(item);
    }


    public boolean contains(Item item){
        return nonCompletelist.contains(item);
    }

    public void updateOrCreate(Item item){
        if(contains(item)) hibernateManager.update(item);
        else{
            addToNonComplete(item);
            hibernateManager.create(item);
        }
    }

    public void printAll(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~NON-COMPLETE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        lowLevelPrint(nonCompletelist);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~COMPLETE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        lowLevelPrint(observableList);
    }

    private void lowLevelPrint(ArrayList<Item> items){
        for(Item i: items)System.out.println(i.toString()+"\n\n");
    }

    private  void lowLevelPrint(ObservableList<Item> items){
        for(Item i: items) System.out.println(i.toString()+"\n\n");
    }


    public ArrayList<Item> getNonCompletelist(){
        return nonCompletelist;
    }

    public ObservableList<Item> getCompleteList(){
        return observableList;
    }

    @Override
    public void update(Observable o, Object arg) {
        Item item = (Item) o;
        MyLogger.log.info("inside of update");
        if(contains(item)){
           if(item.isToBeDeleted()) deleteItem(item);
           else updateItem(item);

            reload();
        }

    }

    private void deleteItem(Item item){
        nonCompletelist.remove(item);
        hibernateManager.delete(item);
    }

    private void updateItem(Item item){
        MyLogger.log.info("hibernate update");
        if(item.isComplete()) performComplete(item);
        hibernateManager.update(item);
    }

    private void reload(){
        mainContainer.loadNonCompleteInProperContainer();
        mainContainer.loadComplete();
    }

    private void performComplete(Item item){
        nonCompletelist.remove(item);
        item.setDateCompleted(LocalDate.now());
        MyLogger.log.info(item.toString());
        observableList.add(item);

    }



    public void sortCompleteList(String s) {
        MyLogger.log.info("sorting with s = "+ s);
        if(s.equals(Constants.ORDER_BY_NAME_ASC)) Collections.sort(observableList, new ItemComparators.ItemNameComparatorAscending());
        else if(s.equals(Constants.ORDER_BY_NAME_DES)) Collections.sort(observableList,new ItemComparators.ItemNameComparatorDescending());
        else if(s.equals(Constants.ORDER_BY_LAST_BOX_NUMBER_ASC)) Collections.sort(observableList, new ItemComparators.ItemBoxComparatorAscending());
        else if(s.equals(Constants.ORDER_BY_LAST_BOX_NUMBER_DES)) Collections.sort(observableList, new ItemComparators.ItemBoxComparatorDescending());
        else if(s.equals(Constants.ORDER_BY_START_DATE_ASC)) Collections.sort(observableList, new ItemComparators.ItemStartDateComparatorAscending());
        else if(s.equals(Constants.ORDER_BY_START_DATE_DES)) Collections.sort(observableList, new ItemComparators.ItemStartDateComparatorDescending());
        else if(s.equals(Constants.ORDER_BY_COMPLETED_DATE_ASC)) Collections.sort(observableList, new ItemComparators.ItemCompleteDateComparatorAscending());
        else if(s.equals(Constants.ORDER_BY_COMPLETED_DATE_DES)) Collections.sort(observableList, new ItemComparators.ItemCompleteDateComparatorDescending());
    }



    @Override
    public void onChanged(Change c) {
        MyLogger.log.info("changed!");
        mainContainer.loadComplete();
    }
}
