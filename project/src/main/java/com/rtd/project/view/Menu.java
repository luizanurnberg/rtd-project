package com.rtd.project.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Menu extends JFrame {
    public Menu() {
        setTitle("Menu Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(2000, 1000));

        BackgroundPanel backgroundPanel = new BackgroundPanel("src/main/resources/static/menuBackground.png");
        backgroundPanel.setBounds(0, 0, 2000, 1000);
        layeredPane.add(backgroundPanel, Integer.valueOf(0));

        ImageIcon logoIcon = new ImageIcon("src/main/resources/static/renaultLogoHeader.png");
        Image scaledLogo = logoIcon.getImage().getScaledInstance(2000, 80, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
        logoLabel.setBounds(0, 0, 2000, 80);
        layeredPane.add(logoLabel, Integer.valueOf(1));

        ImageIcon logoLgIcon = new ImageIcon("src/main/resources/static/renaultLogoLg.png");
        Image scaledLogoLg = logoLgIcon.getImage().getScaledInstance(180, 50, Image.SCALE_SMOOTH);
        JLabel logoLgLabel = new JLabel(new ImageIcon(scaledLogoLg));
        logoLgLabel.setBounds(50, 15, 180, 50);
        layeredPane.add(logoLgLabel, Integer.valueOf(2));

        int buttonWidth = 400;
        int buttonHeight = 150;
        int horizontalSpacing = 50;

        RoundedButton predictButton = new RoundedButton("src/main/resources/static/predictIcon.png", "Predições");
        predictButton.setBounds((2000 - (buttonWidth * 2 + horizontalSpacing)) / 2, 350, buttonWidth, buttonHeight);
        predictButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                Predict predictScreen = new Predict();
                predictScreen.showPredict();
            }
        });
        layeredPane.add(predictButton, Integer.valueOf(1));

        RoundedButton manageButton = new RoundedButton("src/main/resources/static/manageIcon.png", "Gerenciar Equipamentos");
        manageButton.setBounds((2000 - (buttonWidth * 2 + horizontalSpacing)) / 2 + buttonWidth + horizontalSpacing, 350, buttonWidth, buttonHeight);
        manageButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                Machine manageScreen = new Machine();
                manageScreen.showMachine();
            }
        });
        layeredPane.add(manageButton, Integer.valueOf(1));

        add(layeredPane);
    }

    public void showMenu() {
        setVisible(true);
    }
}

