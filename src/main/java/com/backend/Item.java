package main.java.com.backend;

import java.text.NumberFormat;

public class Item {
    public String name;
    public int priceCent;


    public Item(String name, int priceCent) {
        this.name = name;
        this.priceCent = priceCent;
    }

    public String GetPriceString() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(this.priceCent / 100.0);
    }
}