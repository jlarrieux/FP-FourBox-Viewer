package com.fp.fourBoxViewer.Entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * Created by jlarrieux on 5/16/2017.
 */
@Entity
@Table(name = "fourbox")
public class Item {

    @Id
    @GeneratedValue
    private long id;

    private String name, description;
    private int box;



    public long getId() {
        return id;
    }



    public void setId(long id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public String getDescription() {
        return description;
    }



    public void setDescription(String description) {
        this.description = description;
    }



    public int getBox() {
        return box;
    }



    public void setBox(int box) {
        this.box = box;
    }
}
