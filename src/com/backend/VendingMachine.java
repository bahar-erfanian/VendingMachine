package com.backend;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Observable;

public interface VendingMachine {
    String getFund();

    void addFund(@NotNull CoinEnum coin);

    String chooseItem(String itemName);

    Inventory getItem(String itemName);

    String resetFund();
}
