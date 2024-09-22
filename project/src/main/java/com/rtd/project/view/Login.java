package com.rtd.project.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;

public class Login extends JFrame {
    public Login() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        BackgroundPanel backgroundPanel = new BackgroundPanel("src/main/resources/static/background.png");
        backgroundPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Font labelFont = new Font("Arial", Font.BOLD, 18);
        Font fieldFont = new Font("Arial", Font.PLAIN, 16);
        Font buttonFont = new Font("Arial", Font.BOLD, 18);

        GridBagConstraints loginGbc = new GridBagConstraints();
        loginGbc.insets = new Insets(10, 10, 10, 10);

        ImageIcon logoIcon = new ImageIcon("src/main/resources/static/renaultLogoSm.png");

        Image logoImage = logoIcon.getImage();
        Image scaledLogoImage = logoImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledLogoIcon = new ImageIcon(scaledLogoImage);

        JLabel logoLabel = new JLabel(scaledLogoIcon);
        loginGbc.gridx = 0;
        loginGbc.gridy = 0;
        loginGbc.gridwidth = 2;
        loginPanel.add(logoLabel, loginGbc);

        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        loginGbc.gridy = 1;
        loginPanel.add(titleLabel, loginGbc);

        JLabel userLabel = new JLabel("E-mail");
        userLabel.setFont(labelFont);
        loginGbc.gridwidth = 1;
        loginGbc.gridx = 0;
        loginGbc.gridy = 2;
        loginPanel.add(userLabel, loginGbc);

        JTextField userField = new JTextField("", 15);
        userField.setFont(fieldFont);
        userField.setForeground(Color.GRAY);
        loginGbc.gridx = 1;
        loginPanel.add(userField, loginGbc);

        JLabel passwordLabel = new JLabel("Senha");
        passwordLabel.setFont(labelFont);
        loginGbc.gridx = 0;
        loginGbc.gridy = 3;
        loginPanel.add(passwordLabel, loginGbc);

        JPasswordField passwordField = new JPasswordField("", 15);
        passwordField.setFont(fieldFont);
        passwordField.setForeground(Color.GRAY);
        loginGbc.gridx = 1;
        loginPanel.add(passwordField, loginGbc);

        JButton loginButton = new JButton("Logar");
        loginButton.setBackground(new Color(255, 204, 0));
        loginButton.setForeground(Color.BLACK);
        loginButton.setFont(buttonFont);
        loginButton.setPreferredSize(new Dimension(150, 40));
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder());
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passwordField.getPassword());

                // Fechar a tela de Login
                dispose();

                Menu menu = new Menu();
                menu.showMenu();
            }
        });

        loginGbc.gridwidth = 2;
        loginGbc.gridx = 0;
        loginGbc.gridy = 4;
        loginGbc.insets = new Insets(20, 0, 0, 0);
        loginPanel.add(loginButton, loginGbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(20, 0, 0, 0);
        backgroundPanel.add(loginPanel, gbc);

        add(backgroundPanel);
    }

    public void showLogin() {
        setVisible(true);
    }
}