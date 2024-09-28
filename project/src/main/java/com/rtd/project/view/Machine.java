package com.rtd.project.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Machine extends JFrame {

    public Machine() {
        setTitle("Gerenciamento");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(2000, 1000));
        layeredPane.setBackground(new Color(240, 240, 240));

        Font fieldFont = new Font("Arial", Font.PLAIN, 16);
        Font buttonFont = new Font("Arial", Font.BOLD, 15);

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

        ImageIcon imagemIcon = new ImageIcon(getClass().getClassLoader().getResource("static/personIcon.jpeg").getPath());
        JLabel personIcon = new JLabel(imagemIcon);
        personIcon.setBounds(1250, 15, 50, 50);
        layeredPane.add(personIcon, Integer.valueOf(2));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));
        searchPanel.setOpaque(false);

        JTextField searchField = new JTextField("", 13);
        searchField.setFont(fieldFont);
        searchField.setForeground(Color.LIGHT_GRAY);
        searchPanel.add(searchField);

        JButton searchButton = new JButton("Buscar");
        searchButton.setBackground(new Color(211, 211, 211));
        searchButton.setForeground(Color.BLACK);
        searchButton.setFont(buttonFont);
        searchButton.setPreferredSize(new Dimension(100, 25));
        searchButton.setFocusPainted(false);
        searchButton.setBorder(BorderFactory.createEmptyBorder());
        searchPanel.add(searchButton);

        searchPanel.setBounds(250, 10, 1000, 50);
        layeredPane.add(searchPanel, Integer.valueOf(3));

        int buttonWidth = 300;
        int buttonHeight = 400;
        int horizontalSpacing = 50;

        try {
            BufferedImage originalImage = ImageIO.read(new File(getClass().getClassLoader().getResource("static/garbageIcon.png").getPath()));

            int newWidth = 70;
            int newHeight = 70;
            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

            ImageIcon icon = new ImageIcon(resizedImage);

            JButton backButton = new JButton("Voltar");
            backButton.setBackground(new Color(211, 211, 211));
            backButton.setFont(buttonFont);
            backButton.setBounds(50, 100, 60, 25);
            backButton.setFocusPainted(false);
            backButton.setBorder(BorderFactory.createEmptyBorder());
            layeredPane.add(backButton, Integer.valueOf(2));

            backButton.addActionListener((ActionEvent e) -> {
                dispose();
                Menu menuScreen = new Menu();
                menuScreen.showMenu();
            });

            Card card3 = new Card(icon, false, 3, "Máquina de pintura", "", "maria.holler@renault.com", "40kWh");
            card3.setBounds((2000 - (buttonWidth * 3 + horizontalSpacing)) / 2 + buttonWidth + horizontalSpacing, 180, buttonWidth, buttonHeight);
            layeredPane.add(card3, Integer.valueOf(4));
            add(layeredPane);
            pack();
            setLocationRelativeTo(null);

            Card card2 = new Card(icon, true, 2, "Máquina de montagem", "", "luiza.nurnberg@renault.com", "30kWh");
            card2.setBounds((2000 - (buttonWidth * 3 + horizontalSpacing)) / 2, 180, buttonWidth, buttonHeight);
            layeredPane.add(card2, Integer.valueOf(5));
            add(layeredPane);
            pack();
            setLocationRelativeTo(null);

            Card card1 = new Card(icon, false, 1, "Máquina de Pintura", "", "maria.holler@renault.com", "60kW");
            card1.setBounds((2000 - (buttonWidth * 3 + horizontalSpacing)) / 2 - (buttonWidth + horizontalSpacing), 180, buttonWidth, buttonHeight);
            layeredPane.add(card1, Integer.valueOf(6));
            add(layeredPane);
            pack();
            setLocationRelativeTo(null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        JButton buttonParear = new JButton("Parear Novo Equipamento");
        buttonParear.setBackground(new Color(255, 204, 0));
        buttonParear.setForeground(Color.BLACK);
        buttonParear.setFont(buttonFont);
        buttonParear.setOpaque(true);
        buttonParear.setBounds(570, 610, 200, 35);
        buttonParear.setFocusPainted(false);
        buttonParear.setBorder(BorderFactory.createEmptyBorder());
        layeredPane.add(buttonParear, Integer.valueOf(7));
        add(layeredPane);
        pack();
        setLocationRelativeTo(null);

        buttonParear.addActionListener((ActionEvent e) ->
                JOptionPane.showMessageDialog(null, "Buscando Equipamento", null, JOptionPane.INFORMATION_MESSAGE)
        );

    }

    public void showMachine() {
        setVisible(true);
    }
}
