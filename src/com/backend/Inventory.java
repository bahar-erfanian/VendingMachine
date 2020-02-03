package com.backend;

public class Inventory {
    public Item item;
    public int availability;

    public Inventory(Item item, int availability){
        this.item = item;
        this.availability = availability;
    }
}
