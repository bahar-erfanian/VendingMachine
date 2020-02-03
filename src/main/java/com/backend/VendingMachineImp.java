package main.java.com.backend;

import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.util.Observable;


public class VendingMachineImp extends Observable implements VendingMachine {
    private Inventory[] inventories;
    private int fundCent = 0;

    public VendingMachineImp() {
        initiateInventory();
    }

    public String getFundCent() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(this.fundCent / 100.0);
    }

    public void addFund(@NotNull CoinEnum coin) {
        int priceCent = 0;
        switch (coin) {
            case Nickel:
                priceCent = 5;
                break;
            case Dime:
                priceCent = 10;
                break;
            case Quarter:
                priceCent = 25;
                break;
        }
        SetFund(fundCent + priceCent);
    }

    public String chooseItem(String itemName) {
        String msg;
        boolean isSuccess = true;
        for (Inventory inventory : inventories) {
            if (inventory.item.name.equals(itemName)) {
                if (inventory.availability == 0) {
                    msg = "The item does not have sufficient inventory!";
                    isSuccess = false;
                } else if (fundCent < inventory.item.priceCent) {
                    msg = "You do not have sufficient funds!";
                    isSuccess = false;
                } else {
                    //Successfully delivered the item
                    inventory.availability--;
                    SetFund(fundCent - inventory.item.priceCent);
                    msg = "Enjoy your " + itemName + "!";
                    isSuccess = true;

                    setChanged();
                    notifyObservers();
                }
                if (fundCent > 0) {
                    msg += "\nReturned " + getFundCent();
                    resetFund();
                }
                if (!isSuccess) {
                    throw new IllegalArgumentException(msg);
                }
                return msg;
            }
        }
        resetFund();
        throw new IllegalArgumentException("The item: " + itemName + " does not exist");
    }

    public Inventory getItem(String itemName) {
        for (Inventory inventory : inventories) {
            if (inventory.item.name == itemName) {
                return inventory;
            }
        }
        return null;
    }

    public String resetFund() {
        if (this.fundCent == 0) {
            this.SetFund(0);
            throw new IllegalArgumentException("There are no funds to return!");
        }
        String msg = "Returned " + getFundCent();
        this.SetFund(0);
        return msg;
    }

    private void initiateInventory() {
        inventories = new Inventory[]
                {
                        new Inventory(new Item("Coke", 55), 10),
                        new Inventory(new Item("Chips", 70), 10),
                        new Inventory(new Item("RedBull", 75), 10)
                };
    }

    private void SetFund(int fundCent) {
        this.fundCent = fundCent;
        setChanged();
        notifyObservers();
    }
}
