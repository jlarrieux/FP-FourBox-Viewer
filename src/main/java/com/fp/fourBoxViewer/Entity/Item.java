package com.fp.fourBoxViewer.Entity;



import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;



/**
 * Created by jlarrieux on 5/16/2017.
 */
@Entity
@Table(name = "fourbox", catalog = "OKR", schema = "dbo")
public class Item extends Observable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private int box;
    private boolean complete;
    private LocalDate dateStarted, dateCompleted;

    @Transient
    private ArrayList<Observer> observers = new ArrayList<>();



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



    public boolean isComplete() {
        return complete;
    }



    public void setComplete(boolean complete) {
        this.complete = complete;
    }



    public String toString(){
        String datecompleteString = "null";
        if(dateCompleted!=null) datecompleteString = dateCompleted.toString();
        return String.format("\n******ITEM*********\nName: %s\nBox: %d\nid: %d\nStart Date: %s\nComplete date: %s\n complete: %b", name,box,id,dateStarted.toString(), datecompleteString, complete);
    }



    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(obj instanceof  Item){
            Item i = (Item) obj;
            return getName().equals(i.getName()) &&  getBox()==i.getBox() && getDateStarted().isEqual(i.getDateStarted());
        }

        return false;
    }



    @Override
    public int hashCode() {
        int result = 17;
        result = 31*result+getName().hashCode();
        result= 31*result + getBox();
        result = 31* result + getDateStarted().hashCode();
        return result;
    }



    @Override
    public synchronized void addObserver(Observer o) {
        observers.add(o);
    }



    @Override
    public synchronized void deleteObserver(Observer o) {
        observers.remove(o);
    }



    @Override
    public void notifyObservers() {
        for(Observer observer: observers) observer.update(this, null);
    }
}
