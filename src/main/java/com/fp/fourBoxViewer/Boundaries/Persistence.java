package com.fp.fourBoxViewer.Boundaries;



import com.fp.fourBoxViewer.Entity.Item;



/**
 * Created by jlarrieux on 5/16/2017.
 */
 interface Persistence {


     void create(Item item);
     void read();
     void update(Item item);
     void delete(Item item);



}
