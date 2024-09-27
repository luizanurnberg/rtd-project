package com.rtd.project.view;

import com.rtd.project.service.ArduinoClient;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public final class Card extends JPanel {
    private ImageIcon icon;
    private JButton buttonToggle;
    private boolean status;
    private int idMaquina;
    private String nomeMaquina;
    private String horario;
    private String responsavel;
    private String energia;

    private JLabel labelHorarioFuncionamento;
    private JLabel horarioFuncionamento;
    private ArduinoClient arduinoClient;
    private int horasFuncionamento;

    public Card(ImageIcon icon, boolean status, int idMaquina, String nomeMaquina, String horario, String responsavel, String energia) {
        this.icon = icon;
        this.status = status;
        this.idMaquina = idMaquina;
        this.nomeMaquina = nomeMaquina;
        this.horario = horario;
        this.responsavel = responsavel;
        this.energia = energia;

        Random random = new Random();
        this.horasFuncionamento = random.nextInt(6) + 1;

        setOpaque(false);
        Font font = new Font("Arial", Font.BOLD, 15);
        setLayout(null);

        JLabel label1 = new JLabel("ID Máquina:");
        label1.setFont(font);
        label1.setForeground(Color.BLACK);
        label1.setBounds(40, 100, 100, 30);
        add(label1);

        JLabel lableidMaquina = new JLabel(Integer.toString(idMaquina));
        lableidMaquina.setFont(font);
        lableidMaquina.setForeground(Color.BLACK);
        lableidMaquina.setBounds(40, 122, 100, 30);
        add(lableidMaquina);

        JLabel lable2 = new JLabel("Responsável pela Máquina:");
        lable2.setFont(font);
        lable2.setForeground(Color.BLACK);
        lable2.setBounds(40, 150, 200, 30);
        add(lable2);

        JLabel responsavelMaquina = new JLabel(responsavel);
        responsavelMaquina.setFont(font);
        responsavelMaquina.setForeground(Color.BLACK);
        responsavelMaquina.setBounds(40, 172, 200, 30);
        add(responsavelMaquina);

        JLabel lable3 = new JLabel("");
        lable3.setFont(font);
        lable3.setForeground(Color.BLACK);
        lable3.setBounds(40, 210, 200, 20);
        add(lable3);

        this.labelHorarioFuncionamento = lable3;

        horarioFuncionamento = new JLabel(horario);
        horarioFuncionamento.setFont(font);
        horarioFuncionamento.setForeground(Color.BLACK);
        horarioFuncionamento.setBounds(40, 232, 200, 20);
        add(horarioFuncionamento);

        JLabel lable4 = new JLabel("Consumo médio (15 minutos):");
        lable4.setFont(font);
        lable4.setForeground(Color.BLACK);
        lable4.setBounds(40, 262, 200, 20);
        add(lable4);

        JLabel energiaConsumida = new JLabel(energia);
        energiaConsumida.setFont(font);
        energiaConsumida.setForeground(Color.BLACK);
        energiaConsumida.setBounds(40, 284, 200, 20);
        add(energiaConsumida);

        buttonToggle = new JButton(status ? "Desligar" : "Ligar");
        buttonToggle.setBackground(status ? new Color(255, 0, 0) : new Color(0, 255, 0));
        buttonToggle.setForeground(Color.BLACK);
        buttonToggle.setFont(font);
        buttonToggle.setBounds(40, 345, 150, 30);
        buttonToggle.setFocusPainted(false);
        buttonToggle.setBorder(BorderFactory.createEmptyBorder());
        add(buttonToggle);

        buttonToggle.addActionListener((ActionEvent e) -> {
            toggleMaquina();
        });
    }

    private void toggleMaquina() {
        this.status = !this.status;

        buttonToggle.setText(status ? "Desligar" : "Ligar");
        buttonToggle.setBackground(status ? new Color(255, 0, 0) : new Color(0, 255, 0));
        labelHorarioFuncionamento.setText(status ? "Tempo Desligada: " + horasFuncionamento + " horas" : "Tempo Ligada: " + horasFuncionamento + " horas");

        sendRequestArduino();
    }

    private void sendRequestArduino() {
        try {
            if (status) {
                arduinoClient.turnOnLed();
                JOptionPane.showMessageDialog(null, "Máquina Ligada e LED LIGADO no Arduino!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                arduinoClient.turnOffLed();
                JOptionPane.showMessageDialog(null, "Máquina Desligada e LED DESLIGADO no Arduino!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao se comunicar com o Arduino", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

        int newIconWidth = icon.getIconWidth() / 2;
        int newIconHeight = icon.getIconHeight() / 2;
        Image scaledImage = icon.getImage().getScaledInstance(newIconWidth, newIconHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.setColor(Color.BLACK);
        FontMetrics fm = g2d.getFontMetrics();

        int textX = (getWidth() - Math.max(fm.stringWidth(nomeMaquina), newIconWidth) - 40) / 2;
        int textY = (getHeight() - newIconHeight) / 2 - 125;
        int iconX = (getWidth() - newIconWidth - fm.stringWidth(nomeMaquina) + 350) / 2; // Centraliza o ícone
        int iconY = (getHeight() - newIconHeight) / 2 - 150;

        g2d.drawImage(scaledIcon.getImage(), iconX, iconY, null);
        g2d.drawString(nomeMaquina, textX, textY);

    }
}