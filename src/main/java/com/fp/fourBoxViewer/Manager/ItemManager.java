package com.fp.fourBoxViewer.Manager;





import com.fp.fourBoxViewer.Boundaries.HibernateManager;
import com.fp.fourBoxViewer.Entity.Item;

import java.util.ArrayList;


/**
 * Created by jlarrieux on 5/16/2017.
 */
public class ItemManager {

    private HibernateManager hibernateManager = new HibernateManager();
    private ArrayList<Item> list = new ArrayList<>();

    public ItemManager(){
        list.addAll(hibernateManager.loadAll());
        printAll();
    }


    public int size(){
        return list.size();
    }

    public void add(Item item){
        list.add(item);
    }


    public boolean contains(Item item){
        return list.contains(item);
    }

    public void updateOrCreate(Item item){
        if(contains(item)) hibernateManager.update(item);
        else hibernateManager.create(item);
    }

    public void printAll(){
        for(Item item: list){
            System.out.println(item.toString()+"\n\n\n");
        }
    }


    public ArrayList<Item> getList(){
        return list;
    }

}
