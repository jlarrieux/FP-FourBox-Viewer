package com.fp.fourBoxViewer.Comparators;



import com.fp.fourBoxViewer.Entity.Item;

import java.util.Comparator;



/**
 * Created by jlarrieux on 5/25/2017.
 */
public class ItemComparators {

    public static class ItemNameComparatorAscending implements Comparator<Item> {
        @Override
        public int compare(Item o1, Item o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }


    public static class ItemNameComparatorDescending implements Comparator<Item>{

        @Override
        public int compare(Item o1, Item o2) {
            return -o1.getName().compareTo(o2.getName());
        }
    }


    public static class  ItemBoxComparatorAscending implements Comparator<Item>{

        @Override
        public int compare(Item o1, Item o2) {
            return  Integer.compare(o1.getBox(), o2.getBox());
        }
    }


    public static class ItemBoxComparatorDescending implements Comparator<Item>{

        @Override
        public int compare(Item o1, Item o2) {
            return -Integer.compare(o1.getBox(), o2.getBox());
        }
    }


    public static class ItemStartDateComparatorAscending implements Comparator<Item>{

        @Override
        public int compare(Item o1, Item o2) {
            return o1.getDateStarted().compareTo(o2.getDateStarted());
        }
    }


    public static class ItemStartDateComparatorDescending implements Comparator<Item>{

        @Override
        public int compare(Item o1, Item o2) {
            return -o1.getDateStarted().compareTo(o2.getDateStarted());
        }
    }

    public static class ItemCompleteDateComparatorAscending implements Comparator<Item>{

        @Override
        public int compare(Item o1, Item o2) {
            return o1.getDateCompleted().compareTo(o2.getDateCompleted());
        }
    }


    public static class ItemCompleteDateComparatorDescending implements Comparator<Item>{

        @Override
        public int compare(Item o1, Item o2) {
            return -o1.getDateCompleted().compareTo(o2.getDateCompleted());
        }
    }


}
