package com.fp.fourBoxViewer.test

import com.fp.fourBoxViewer.Entity.Item
import spock.lang.Specification

import java.time.LocalDate

/**
 * Created by jlarrieux on 5/23/2017.
 */
class ItemTest extends Specification{


    def "two equals items are equal"(){
        given: "a fully defined item"
        Item i1 = new Item();
        i1.setName("name")
        i1.setStatus("this is my description")
        i1.setDateStarted(LocalDate.now())

        when: "another item equals the previous one"
        Item i2 = i1

        then: "equals method should return true"
        assert i1.equals(i2)
    }


    def "two different items should no be equal"(){
        given: "a fully defined item"
        Item i1 = new Item();
        i1.setName("name")
        i1.setStatus("this is my description")
        i1.setDateStarted(LocalDate.now())

        when: "another item equals the previous one"
        Item i2 = new Item()

        then: "equals method should be false"
        assert !i1.equals(i2)
    }


    def "two equals items should have same hashcode"(){
        given: "a fully defined item"
        Item i1 = new Item();
        i1.setName("name")
        i1.setStatus("this is my description")
        i1.setDateStarted(LocalDate.now())

        when: "another item equals the previous one"
        Item i2 = i1

        then: "equals method should return true"
        assert i1.hashCode()==i2.hashCode()
    }
}
