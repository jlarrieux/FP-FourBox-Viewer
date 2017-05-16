package com.fp.fourBoxViewer.Manager;





import java.util.ArrayList;


/**
 * Created by jlarrieux on 5/16/2017.
 */
public class ItemManager {


    private ArrayList<com.fp.fourBoxViewer.Entity.Item> list = new ArrayList<>();


    public int size(){
        return list.size();
    }

    public void add(com.fp.fourBoxViewer.Entity.Item item){
        list.add(item);
    }


}
