/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flower;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Group 4 - SE1607 - Tran Ba Nam CE161036 - Do Huynh Anh Vu CE171446
 */
public class Flower {

    //initialize variable
    int ID;
    String name;
    double price;
    String color;

    /**
     * @param ID
     * @param name
     * @param price
     * @param color
     */
    public Flower(int ID, String name, double price, String color) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.color = color;
    }
}

/**
 * Sort array list through ID in ascending order
 */
class IDComparator implements Comparator<Flower> {

    @Override
    public int compare(Flower t, Flower t1) {
        if (t.ID == t1.ID) {
            return 0;
        } else if (t.ID > t1.ID) {
            return 1;
        } else {
            return -1;
        }
    }

}

/**
 * Sort array list through name in alphabet
 */
class NameComparator implements Comparator<Flower> {

    @Override
    public int compare(Flower t, Flower t1) {
        return t.name.compareTo(t1.name);
    }

}

/**
 * Sort array list through price in ascending order
 */
class PriceComparator implements Comparator<Flower> {

    @Override
    public int compare(Flower t, Flower t1) {
        if (t.price == t1.price) {
            return 0;
        } else if (t.price < t1.price) {
            return 1;
        } else {
            return -1;
        }
    }

}

/**
 * Sort array list through color in alphabet
 */
class ColorComparator implements Comparator<Flower> {

    @Override
    public int compare(Flower t, Flower t1) {
        return t1.color.compareTo(t.color);

    }
}

class SF {

    public static void main(String[] args) {
        //Add flower and its value to the array
        ArrayList<Flower> fl = new ArrayList<Flower>();
        fl.add(new Flower(1, "Rose", 3.0, "red"));
        fl.add(new Flower(6, "Violet", 3.0, "blue"));
        fl.add(new Flower(3, "SunFlower", 4.0, "yellow"));
        fl.add(new Flower(4, "Cherry blossom", 5.0, "pink"));
        fl.add(new Flower(8, "Daisy", 3.0, "whitle"));
        fl.add(new Flower(9, "Snapdragon", 3.0, "red"));
        fl.add(new Flower(12, "Carnation", 4.5, "red"));
        fl.add(new Flower(2, "Red valerian", 4.9, "red"));
        fl.add(new Flower(11, "Golden marguerite", 3.5, "yellow"));
        fl.add(new Flower(5, "Mold orchids", 14.5, "white"));

        //Show the sorted array list through ID
        System.out.println("Sorting with ID");
        Collections.sort(fl, new IDComparator());
        for (Flower flower : fl) {
            System.out.println(flower.ID + " " + flower.name
                    + " " + flower.price + " " + flower.color);

        }

        //Show the sorted array list through name
        System.out.println("\nSorting with name");
        Collections.sort(fl, new NameComparator());
        for (Flower flower : fl) {
            System.out.println(flower.ID + " " + flower.name
                    + " " + flower.price + " " + flower.color);

        }

        //Show the sorted array list through price
        System.out.println("\nSorting with price");
        Collections.sort(fl, new PriceComparator());
        for (Flower flower : fl) {
            System.out.println(flower.ID + " " + flower.name
                    + " " + flower.price + " " + flower.color);

        }

        //Show the sorted array list through color
        System.out.println("\nSorting with color");
        Collections.sort(fl, new ColorComparator());
        for (Flower flower : fl) {
            System.out.println(flower.ID + " " + flower.name
                    + " " + flower.price + " " + flower.color);

        }
    }
}
