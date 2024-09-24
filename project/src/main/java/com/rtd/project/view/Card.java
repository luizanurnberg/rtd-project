package com.rtd.project.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class Card extends JPanel {
    private ImageIcon icon;
    private JButton button; 
    private boolean status;
    private int idMaquina;
    private String nomeMaquina;
    private String text;
    private String horario;
    private String responsavel;
    private String energia;

    public Card(ImageIcon icon, boolean status, int idMaquina, String nomeMaquina,String horario, String responsavel, String energia) {
        this.icon = icon;
        this.status = status;
        this.idMaquina = idMaquina;
        this.nomeMaquina = nomeMaquina;
        this.horario = horario;
        this.responsavel = responsavel;
        this.energia = energia;
        setOpaque(false);
        Font font = new Font("Arial", Font.BOLD, 15);
        
        setText(status);
        JButton buttoncard = new JButton(text);
        buttoncard.setBackground(new Color(230, 230, 230));
        buttoncard.setForeground(Color.BLACK);
        buttoncard.setFont(font);
        buttoncard.setOpaque(true);
        buttoncard.setBounds(105, 345, 100, 30); 
        buttoncard.setFocusPainted(false);
        buttoncard.setBorder(BorderFactory.createEmptyBorder());
       
        add(buttoncard);

        buttoncard.addActionListener((ActionEvent e) -> {
            setStatus(status);
            setText(getStatus()); 
            buttoncard.setText(text); 
        });
        
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
    
    JLabel lable3 = new JLabel("Horário de Funcionamento:");
    lable3.setFont(font);
    lable3.setForeground(Color.BLACK);
    lable3.setBounds(40, 210, 200, 20); 
    add(lable3);
    
    JLabel horarioFuncionamento = new JLabel(horario);
    horarioFuncionamento.setFont(font);
    horarioFuncionamento.setForeground(Color.BLACK);
    horarioFuncionamento.setBounds(40, 232, 200, 20); 
    add(horarioFuncionamento);
    
    JLabel lable4 = new JLabel("Energia Consumida:");
    lable4.setFont(font);
    lable4.setForeground(Color.BLACK);
    lable4.setBounds(40, 262, 200, 20); 
    add(lable4);
    
    JLabel energiaConsumida = new JLabel(energia);
    energiaConsumida.setFont(font);
    energiaConsumida.setForeground(Color.BLACK);
    energiaConsumida.setBounds(40, 284, 200, 20); 
    add(energiaConsumida);
    }
    
    public void setStatus(boolean status) {
        this.status = !status;
    }
    
    public boolean getStatus() {
        return status;
    }
    
    public void setText(boolean status) {
        if(status){
            this.text = "Ligada";
        }else {
            this.text = "Desligada";
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
