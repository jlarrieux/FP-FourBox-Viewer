package com.fp.fourBoxViewer.Boundaries;



import com.fp.fourBoxViewer.Entity.Item;



/**
 * Created by jlarrieux on 5/16/2017.
 */
public interface Persistence {


    public void create(Item item);
    public void read();
    public void update();
    public void delete();


}
