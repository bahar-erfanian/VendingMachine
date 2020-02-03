package main.java.com.backend;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class Item {
    public String name;
    public BigDecimal price;


    public Item(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String GetPriceString(){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(this.price);
    }
}