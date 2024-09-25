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
        layeredPane.setLayout(null);

        BackgroundPanel backgroundPanel = new BackgroundPanel(getClass().getClassLoader().getResource("static/menuBackground.png").getPath());
        backgroundPanel.setBounds(0, 0, getWidth(), getHeight());
        layeredPane.add(backgroundPanel, Integer.valueOf(0));
        
        ImageIcon logoIcon = new ImageIcon(getClass().getClassLoader().getResource("static/renaultLogoHeader.png").getPath());
        Image scaledLogo = logoIcon.getImage().getScaledInstance(2000, 80, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
        logoLabel.setBounds(0, 0, 2000, 80);
        layeredPane.add(logoLabel, Integer.valueOf(1));

        ImageIcon logoLgIcon = new ImageIcon(getClass().getClassLoader().getResource("static/renaultLogoLg.png").getPath());
        Image scaledLogoLg = logoLgIcon.getImage().getScaledInstance(180, 50, Image.SCALE_SMOOTH);
        JLabel logoLgLabel = new JLabel(new ImageIcon(scaledLogoLg));
        logoLgLabel.setBounds(50, 15, 180, 50);
        layeredPane.add(logoLgLabel, Integer.valueOf(2));

        int buttonWidth = 400;
        int buttonHeight = 150;
        int horizontalSpacing = 50;


        int totalWidth = layeredPane.getWidth(); 

        int buttonX = (totalWidth - (buttonWidth * 2 + horizontalSpacing)) / 2;

        RoundedButton predictButton = new RoundedButton(getClass().getClassLoader().getResource("static/predictIcon.png").getPath(), "Predições");
        predictButton.setBounds((2000 - (buttonWidth * 3 + horizontalSpacing)) / 3, 330, buttonWidth, buttonHeight);
        predictButton.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            dispose();
            Predict predictScreen = new Predict();
            predictScreen.showPredict();
           }
        });
        layeredPane.add(predictButton, Integer.valueOf(1));

        RoundedButton manageButton = new RoundedButton(getClass().getClassLoader().getResource("static/manageIcon.png").getPath(), "Gerenciar Equipamentos");
        manageButton.setBounds((2000 - (buttonWidth * 3 + horizontalSpacing)) / 3 + buttonWidth + horizontalSpacing, 330, buttonWidth, buttonHeight);
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
                pack();
                setLocationRelativeTo(null);
                backgroundPanel.setBounds(0, 0, getWidth(), getHeight());
            }

    public void showMenu() {
        setVisible(true);
    }
}