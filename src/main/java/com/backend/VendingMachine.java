package main.java.com.backend;

import org.jetbrains.annotations.NotNull;

public interface VendingMachine {
    String getFund();

    void addFund(@NotNull CoinEnum coin);

    String chooseItem(String itemName);

    Inventory getItem(String itemName);

    String resetFund();
}
