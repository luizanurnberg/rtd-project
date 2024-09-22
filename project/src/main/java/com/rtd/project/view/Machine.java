package com.rtd.project.view;

import javax.swing.*;
import java.awt.*;

public class Machine extends JFrame {

    public Machine() {
        setTitle("Gerenciamento");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(2000, 1000));
        layeredPane.setBackground(new Color(240, 240, 240));

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

        add(layeredPane);
    }

    public void showMachine() {
        setVisible(true);
    }
}
