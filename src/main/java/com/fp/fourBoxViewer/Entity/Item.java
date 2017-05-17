package com.fp.fourBoxViewer.Entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;



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
    private LocalDate dateStarted, dateCompleted;



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



    public LocalDate getDateStarted() {
        return dateStarted;
    }



    public void setDateStarted(LocalDate dateStarted) {
        this.dateStarted = dateStarted;
    }



    public LocalDate getDateCompleted() {
        return dateCompleted;
    }



    public void setDateCompleted(LocalDate dateCompleted) {
        this.dateCompleted = dateCompleted;
    }
}
