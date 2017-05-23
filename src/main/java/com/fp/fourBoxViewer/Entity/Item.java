package com.fp.fourBoxViewer.Entity;



import javax.persistence.*;
import java.time.LocalDate;



/**
 * Created by jlarrieux on 5/16/2017.
 */
@Entity
@Table(name = "fourbox", catalog = "OKR", schema = "dbo")
public class Item {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    public String toString(){
        return String.format("\n******ITEM*********\nName: %s\nDescription: %s\nBox: %d\nStart Date: %s", name,description,box,dateStarted.toString());
    }



    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(obj instanceof  Item){
            Item i = (Item) obj;
            return getName().equals(i.getName()) && getDescription().equals(i.getDescription()) && getBox()==i.getBox() && getDateStarted().isEqual(i.getDateStarted());
        }

        return false;
    }



    @Override
    public int hashCode() {
        int result = 17;
        result = 31*result+getName().hashCode();
        result = 31*result+getDescription().hashCode();
        result= 31*result + getBox();
        result = 31* result + getDateStarted().hashCode();
        return result;
    }
}
