package com.rtd.project.view;

import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JPanel {
    private final ImageIcon icon;
    private final String text;

    public RoundedButton(String iconPath, String text) {
        this.icon = new ImageIcon(iconPath);
        this.text = text;
        setOpaque(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);

        int newIconWidth = icon.getIconWidth() / 2;
        int newIconHeight = icon.getIconHeight() / 2;
        Image scaledImage = icon.getImage().getScaledInstance(newIconWidth, newIconHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.setColor(Color.BLACK);
        FontMetrics fm = g2d.getFontMetrics();

        int textX = (getWidth() - fm.stringWidth(text) - newIconWidth - 10) / 2;
        int textY = (getHeight() + fm.getHeight()) / 2 - fm.getDescent();

        g2d.drawString(text, textX, textY);

        int iconX = textX + fm.stringWidth(text) + 10;
        int iconY = (getHeight() - newIconHeight) / 2;

        scaledIcon.paintIcon(this, g2d, iconX, iconY);
    }
}