package com.fp.fourBoxViewer.Entity;



import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;



/**
 * Created by jlarrieux on 5/16/2017.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "fourbox", catalog = "OKR", schema = "dbo")
public class Item extends Observable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name, technician;
    private int box;
    private boolean complete;
    private LocalDate dateStarted, dateCompleted;
    private String status;
    private Type projectType;

    @Transient
    private ArrayList<Observer> observers = new ArrayList<>();
    @Transient private boolean toBeDeleted = false;


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
