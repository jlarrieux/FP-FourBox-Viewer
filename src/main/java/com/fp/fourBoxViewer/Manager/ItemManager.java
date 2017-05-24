package com.fp.fourBoxViewer.Manager;





import com.fp.fourBoxViewer.Boundaries.HibernateManager;
import com.fp.fourBoxViewer.Entity.Item;
import com.fp.fourBoxViewer.Util.MyLogger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;



/**
 * Created by jlarrieux on 5/16/2017.
 */
public class ItemManager implements Observer {

    private HibernateManager hibernateManager = HibernateManager.getInstance();
    private ArrayList<Item> nonCompletelist = new ArrayList<>();
    private ArrayList<Item> completeList = new ArrayList<>();
    MainContainer mainContainer;

    public ItemManager(MainContainer mainContainer){
        this.mainContainer = mainContainer;
        loadNonComplete();
        loadComplete();
        printAll();

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
        completeList.add(item);
    }


    public boolean contains(Item item){
        return nonCompletelist.contains(item);
    }

    public void updateOrCreate(Item item){
        if(contains(item)) hibernateManager.update(item);
        else hibernateManager.create(item);
    }

    public void printAll(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~NON-COMPLETE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        lowLevelPrint(nonCompletelist);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~COMPLETE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        lowLevelPrint(completeList);
    }

    private void lowLevelPrint(ArrayList<Item> items){
        for(Item i: items)System.out.println(i.toString()+"\n\n");
    }


    public ArrayList<Item> getNonCompletelist(){
        return nonCompletelist;
    }

    public  ArrayList<Item> getCompleteList(){
        return completeList;
    }

    @Override
    public void update(Observable o, Object arg) {
        Item item = (Item) o;
        MyLogger.log.info("inside of update");
        if(contains(item)){
            MyLogger.log.info("hibernate update");
            if(item.isComplete()) performComplete(item);
            hibernateManager.update(item);
            reload();
        }

    }

    private void reload(){
        mainContainer.loadNonCompleteInProperContainer();
        mainContainer.loadComplete();
    }

    private void performComplete(Item item){
        nonCompletelist.remove(item);
        item.setDateCompleted(LocalDate.now());
        MyLogger.log.info(item.toString());
        completeList.add(item);

    }




}
