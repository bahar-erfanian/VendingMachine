package com.gui;

import com.backend.CoinEnum;
import com.backend.Inventory;
import com.backend.VendingMachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class VendingMachineUI extends JFrame implements Observer {
    private VendingMachine myVendingMachine;

    private JPanel mainPanel;
    private JButton quarterButton;
    private JButton dimesButton;
    private JButton nickleButton;
    private JTextField fundTextField;
    private JButton returnFundButton;
    private JPanel itemsPanel;
    private JPanel cokePanel;
    private JPanel chipsPanel;
    private JPanel redBullPanel;
    private JButton cokeButton;
    private JTextField cokeAvailabilityTextField;
    private JButton chipsButton;
    private JTextField chipsAvailabilityTextField;
    private JButton redbullButton;
    private JTextField redbullAvailabilityTextField;
    private JTextField cokePriceTextField;
    private JTextField chipsPriceTextField;
    private JTextField redbullPriceTextField;

    public VendingMachineUI(String title, VendingMachine vendingMachine){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(mainPanel);
        this.pack();

        this.myVendingMachine = vendingMachine;

        quarterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myVendingMachine.addFund(CoinEnum.Quarter);
            }
        });

        dimesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myVendingMachine.addFund(CoinEnum.Dime);
            }
        });

        nickleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myVendingMachine.addFund(CoinEnum.Nickel);
            }
        });

        returnFundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = myVendingMachine.resetFund();
                ShowMessage(message);
            }
        });

        cokeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = myVendingMachine.ChooseItem("Coke");
                ShowMessage(message);
            }
        });

        chipsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = myVendingMachine.ChooseItem("Chips");
                ShowMessage(message);
            }
        });

        redbullButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = myVendingMachine.ChooseItem("RedBull");
                ShowMessage(message);
            }
        });

        InitiateVendingMachineInformation();
    }

    @Override
    public void update(Observable o, Object arg) {
        SetChangeableVendingMachineVariable();
    }

    private void InitiateVendingMachineInformation(){
        Inventory coke = myVendingMachine.GetItem("Coke");
        Inventory chips = myVendingMachine.GetItem("Chips");
        Inventory redbull = myVendingMachine.GetItem("RedBull");

        this.fundTextField.setText(myVendingMachine.GetFund());

        cokePriceTextField.setText(coke.item.GetPriceString());
        cokeAvailabilityTextField.setText(Integer.toString(coke.availability));
        chipsPriceTextField.setText(chips.item.GetPriceString());
        chipsAvailabilityTextField.setText(Integer.toString(chips.availability));
        redbullPriceTextField.setText(redbull.item.GetPriceString());
        redbullAvailabilityTextField.setText(Integer.toString(redbull.availability));
    }

    private void SetChangeableVendingMachineVariable(){
        Inventory coke = myVendingMachine.GetItem("Coke");
        Inventory chips = myVendingMachine.GetItem("Chips");
        Inventory redbull = myVendingMachine.GetItem("RedBull");

        this.fundTextField.setText(myVendingMachine.GetFund());

        cokeAvailabilityTextField.setText(Integer.toString(coke.availability));
        chipsAvailabilityTextField.setText(Integer.toString(chips.availability));
        redbullAvailabilityTextField.setText(Integer.toString(redbull.availability));
    }

    private void ShowMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        VendingMachine observable = new VendingMachine();
        JFrame frame = new VendingMachineUI("Bahar's Vending Machine.", observable);
        observable.addObserver((Observer) frame);
        frame.setVisible(true);
    }
}
