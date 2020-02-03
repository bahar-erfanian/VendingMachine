package com.gui;

import com.backend.CoinEnum;
import com.backend.Inventory;
import com.backend.MessagePacket;
import com.backend.VendingMachine;

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
    private JButton redbullButton;
    private JTextField redbullAvailabilityTextField;
    private JTextField cokePriceTextField;
    private JTextField chipsPriceTextField;
    private JTextField redbullPriceTextField;

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
            Image qImg = ImageIO.read(getClass().getResource("../../images/quarter.jpg"));
            Image qNewImg = qImg.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            quarterButton.setIcon(new ImageIcon(qNewImg));

            Image dImg = ImageIO.read(getClass().getResource("../../images/dime.jpg"));
            Image dNewImg = dImg.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            dimesButton.setIcon(new ImageIcon(dNewImg));

            Image nImg = ImageIO.read(getClass().getResource("../../images/nickel.jpg"));
            Image nNewImg = nImg.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            nickleButton.setIcon(new ImageIcon(nNewImg));

            Image cImg = ImageIO.read(getClass().getResource("../../images/coke.jpg"));
            Image cNewImg = cImg.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            cokeButton.setIcon(new ImageIcon(cNewImg));

            Image chImg = ImageIO.read(getClass().getResource("../../images/chips.jpg"));
            Image chNewImg = chImg.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            chipsButton.setIcon(new ImageIcon(chNewImg));

            Image rImg = ImageIO.read(getClass().getResource("../../images/redbull.jpg"));
            Image rNewImg = rImg.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            redbullButton.setIcon(new ImageIcon(rNewImg));
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
                MessagePacket message = myVendingMachine.resetFund();
                ShowMessage(message);
            }
        });

        cokeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessagePacket message = myVendingMachine.ChooseItem("Coke");
                ShowMessage(message);
            }
        });

        chipsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessagePacket message = myVendingMachine.ChooseItem("Chips");
                ShowMessage(message);
            }
        });

        redbullButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessagePacket messagePacket = myVendingMachine.ChooseItem("RedBull");
                ShowMessage(messagePacket);
            }
        });

        InitiateVendingMachineInformation();
    }

    @Override
    public void update(Observable o, Object arg) {
        SetChangeableVendingMachineVariable();
    }

    private void InitiateVendingMachineInformation() {
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

    private void SetChangeableVendingMachineVariable() {
        Inventory coke = myVendingMachine.GetItem("Coke");
        Inventory chips = myVendingMachine.GetItem("Chips");
        Inventory redbull = myVendingMachine.GetItem("RedBull");

        this.fundTextField.setText(myVendingMachine.GetFund());

        cokeAvailabilityTextField.setText(Integer.toString(coke.availability));
        chipsAvailabilityTextField.setText(Integer.toString(chips.availability));
        redbullAvailabilityTextField.setText(Integer.toString(redbull.availability));
    }

    private void ShowMessage(MessagePacket messagePacket) {
        int msgType = JOptionPane.INFORMATION_MESSAGE;
        if (!messagePacket.isSuccess) {
            msgType = JOptionPane.ERROR_MESSAGE;
        }
        JOptionPane.showMessageDialog(this, messagePacket.message, "Dialog", msgType);
    }

    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        JFrame frame = new VendingMachineUI("Bahar's Vending Machine.", vendingMachine);
        vendingMachine.addObserver((Observer) frame);
        frame.setVisible(true);
    }
}
