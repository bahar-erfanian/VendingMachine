package com.backend;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Observable;


public class VendingMachine extends Observable {
    private Inventory[] inventories;
    private BigDecimal fund = BigDecimal.ZERO;
    private String fundText;

    public VendingMachine() {
        initiateInventory();
    }

    public void SetFund(BigDecimal fund) {
        this.fund = fund;
        setChanged();
        notifyObservers();
    }

    public String GetFund() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(this.fund);
    }

    public void addFund(@NotNull CoinEnum coin) {
        BigDecimal price = new BigDecimal("0");
        switch (coin) {
            case Nickel:
                price = new BigDecimal("0.05");
                break;
            case Dime:
                price = new BigDecimal("0.10");
                break;
            case Quarter:
                price = new BigDecimal("0.25");
                break;
        }
        SetFund(fund.add(price));
    }

    public String ChooseItem(String itemName) {
        String msg;
        for (Inventory inventory : inventories) {
            if (inventory.item.name.equals(itemName)) {
                if (inventory.availability == 0) {
                    msg = "The item does not have sufficient inventory!";
                } else if (fund.compareTo(inventory.item.price) < 0) {
                    msg = "You do not have sufficient fund!";
                } else {
                    //Successfully delivered the item
                    inventory.availability--;
                    SetFund(fund.subtract(inventory.item.price));
                    msg = "Enjoy your " + itemName + "!";

                    setChanged();
                    notifyObservers();
                }
                if (fund.compareTo(BigDecimal.ZERO) > 0) {
                    msg += "\nReturning " + GetFund();
                }
                resetFund();
                return msg;
            }
        }
        resetFund();
        return "The item: " + itemName + " does not exist";

    }

    public Inventory GetItem(String itemName) {
        for (Inventory inventory : inventories) {
            if (inventory.item.name == itemName) {
                return inventory;
            }
        }
        return null;
    }

    public String resetFund() {
        String msg = "There is no fund!";
        if (this.fund.compareTo(BigDecimal.ZERO) > 0) {
            msg = "Returning " + GetFund();
        }
        this.SetFund(BigDecimal.ZERO);
        return msg;
    }

    private void initiateInventory() {
        inventories = new Inventory[]
                {
                        new Inventory(new Item("Coke", new BigDecimal("0.55")), 10),
                        new Inventory(new Item("Chips", new BigDecimal("0.70")), 10),
                        new Inventory(new Item("RedBull", new BigDecimal("0.75")), 10)
                };
        resetFund();
    }
}
