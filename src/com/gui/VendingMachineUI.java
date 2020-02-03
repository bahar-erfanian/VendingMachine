package com.gui;

import com.backend.*;

import javax.imageio.ImageIO;
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
    private JButton coinReturnButton;
    private JPanel itemsPanel;
    private JPanel cokePanel;
    private JPanel chipsPanel;
    private JPanel redBullPanel;
    private JButton cokeButton;
    private JTextField cokeAvailabilityTextField;
    private JButton chipsButton;
    private JTextField chipsAvailabilityTextField;
    private JButton redBullButton;
    private JTextField redBullAvailabilityTextField;
    private JTextField cokePriceTextField;
    private JTextField chipsPriceTextField;
    private JTextField redBullPriceTextField;

    public VendingMachineUI(String title, VendingMachine vendingMachine) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800, 400));
        this.setContentPane(mainPanel);
        setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);

        this.myVendingMachine = vendingMachine;
        try {
            Image qImg = ImageIO.read(getClass().getResource("../../resources/images/quarter.jpg"));
            Image qNewImg = qImg.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            quarterButton.setIcon(new ImageIcon(qNewImg));

            Image dImg = ImageIO.read(getClass().getResource("../../resources/images/dime.jpg"));
            Image dNewImg = dImg.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            dimesButton.setIcon(new ImageIcon(dNewImg));

            Image nImg = ImageIO.read(getClass().getResource("../../resources/images/nickel.jpg"));
            Image nNewImg = nImg.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            nickleButton.setIcon(new ImageIcon(nNewImg));

            Image cImg = ImageIO.read(getClass().getResource("../../resources/images/coke.jpg"));
            Image cNewImg = cImg.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            cokeButton.setIcon(new ImageIcon(cNewImg));

            Image chImg = ImageIO.read(getClass().getResource("../../resources/images/chips.jpg"));
            Image chNewImg = chImg.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            chipsButton.setIcon(new ImageIcon(chNewImg));

            Image rImg = ImageIO.read(getClass().getResource("../../resources/images/redbull.jpg"));
            Image rNewImg = rImg.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            redBullButton.setIcon(new ImageIcon(rNewImg));
        } catch (Exception ex) {
            System.out.println(ex);
        }

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

        coinReturnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String msg = myVendingMachine.resetFund();
                    showMessage(msg, true);
                }
                catch (Exception ex) {
                    showMessage(ex.getMessage(), false);
                }
            }
        });

        cokeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String msg = myVendingMachine.chooseItem("Coke");
                    showMessage(msg, true);
                }
                catch (Exception ex) {
                    showMessage(ex.getMessage(), false);
                }
            }
        });

        chipsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String msg = myVendingMachine.chooseItem("Chips");
                    showMessage(msg, true);
                }
                catch (Exception ex) {
                    showMessage(ex.getMessage(), false);
                }
            }
        });

        redBullButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String msg = myVendingMachine.chooseItem("RedBull");
                    showMessage(msg, true);
                }
                catch (Exception ex) {
                    showMessage(ex.getMessage(), false);
                }
            }
        });

        initiateVendingMachineInformation();
    }

    @Override
    public void update(Observable o, Object arg) {
        setChangeableVendingMachineVariable();
    }

    private void initiateVendingMachineInformation() {
        Inventory coke = myVendingMachine.getItem("Coke");
        Inventory chips = myVendingMachine.getItem("Chips");
        Inventory redbull = myVendingMachine.getItem("RedBull");

        this.fundTextField.setText(myVendingMachine.getFund());

        cokePriceTextField.setText(coke.item.GetPriceString());
        cokeAvailabilityTextField.setText(Integer.toString(coke.availability));
        chipsPriceTextField.setText(chips.item.GetPriceString());
        chipsAvailabilityTextField.setText(Integer.toString(chips.availability));
        redBullPriceTextField.setText(redbull.item.GetPriceString());
        redBullAvailabilityTextField.setText(Integer.toString(redbull.availability));
    }

    private void setChangeableVendingMachineVariable() {
        Inventory coke = myVendingMachine.getItem("Coke");
        Inventory chips = myVendingMachine.getItem("Chips");
        Inventory redbull = myVendingMachine.getItem("RedBull");

        this.fundTextField.setText(myVendingMachine.getFund());

        cokeAvailabilityTextField.setText(Integer.toString(coke.availability));
        chipsAvailabilityTextField.setText(Integer.toString(chips.availability));
        redBullAvailabilityTextField.setText(Integer.toString(redbull.availability));
    }

    private void showMessage(String message, boolean isSuccess) {
        int msgType = JOptionPane.INFORMATION_MESSAGE;
        if (!isSuccess) {
            msgType = JOptionPane.ERROR_MESSAGE;
        }
        JOptionPane.showMessageDialog(this, message, "Dialog", msgType);
    }

    public static void main(String[] args) {
        VendingMachineImp vendingMachine = new VendingMachineImp();
        JFrame frame = new VendingMachineUI("Bahar's Vending Machine.", vendingMachine);
        vendingMachine.addObserver((Observer) frame);
        frame.setVisible(true);
    }
}
