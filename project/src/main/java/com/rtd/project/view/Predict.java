package com.rtd.project.view;

import javax.swing.*;
import java.awt.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class Predict extends JFrame {

    public Predict() {
        setTitle("DashBoard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);

        getContentPane().setBackground(Color.WHITE);

        // JLayeredPane para as logos
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(2000, 100));
        layeredPane.setBackground(new Color(255, 255, 255));

        ImageIcon logoIcon = new ImageIcon("C:/Users/User/OneDrive/Documentos/rtd-project/project/src/main/resources/static/renaultLogoHeader.png");
        Image scaledLogo = logoIcon.getImage().getScaledInstance(2000, 80, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
        logoLabel.setBounds(0, 0, 2000, 80);
        layeredPane.add(logoLabel, Integer.valueOf(1));

        ImageIcon logoLgIcon = new ImageIcon("C:/Users/User/OneDrive/Documentos/rtd-project/project/src/main/resources/static/renaultLogoLg.png");
        Image scaledLogoLg = logoLgIcon.getImage().getScaledInstance(180, 50, Image.SCALE_SMOOTH);
        JLabel logoLgLabel = new JLabel(new ImageIcon(scaledLogoLg));
        logoLgLabel.setBounds(50, 15, 180, 50);
        layeredPane.add(logoLgLabel, Integer.valueOf(2));

        ImageIcon imagemIcon = new ImageIcon("C:/Users/User/OneDrive/Documentos/rtd-project/project/src/main/resources/static/personIcon.png");
        JLabel personIcon = new JLabel(imagemIcon);
        personIcon.setBounds(1250, 15, 50, 50);
        layeredPane.add(personIcon, Integer.valueOf(3));

        // Adiciona o painel com logos na parte superior
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(layeredPane, gbc);

        // Gráfico de Consumo (Barras)
        DefaultCategoryDataset consumoDataset = new DefaultCategoryDataset();
        consumoDataset.addValue(2, "Últimos 6 dias", "01");
        consumoDataset.addValue(4, "Últimos 6 dias", "02");
        consumoDataset.addValue(3, "Últimos 6 dias", "03");
        consumoDataset.addValue(5, "Últimos 6 dias", "04");
        consumoDataset.addValue(4, "Última semana", "01");
        consumoDataset.addValue(3, "Última semana", "02");
        consumoDataset.addValue(2, "Última semana", "03");

        JFreeChart barChart = ChartFactory.createBarChart(
            "Gráfico de Consumo", "Dias", "Consumo", consumoDataset);
        barChart.getTitle().setFont(new Font("Poppins", Font.PLAIN, 18));
        barChart.getTitle().setHorizontalAlignment(HorizontalAlignment.LEFT);
        barChart.getTitle().setPadding(0, 60, 0, 0);

        ChartPanel barPanel = new ChartPanel(barChart);
        JPanel barChartPanel = new JPanel(new BorderLayout());
        barChartPanel.add(barPanel, BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.4;
        gbc.insets = new Insets(0, 50, 10, 25);
        add(barChartPanel, gbc);

        // Gráfico de Status das Máquinas (Linha)
        DefaultCategoryDataset statusDataset = new DefaultCategoryDataset();
        statusDataset.addValue(2, "Atual", "01");
        statusDataset.addValue(3, "Atual", "02");
        statusDataset.addValue(5, "Atual", "03");
        statusDataset.addValue(1, "Atual", "04");
        statusDataset.addValue(3, "Semana passada", "01");
        statusDataset.addValue(4, "Semana passada", "02");
        statusDataset.addValue(2, "Semana passada", "03");

        JFreeChart lineChart = ChartFactory.createLineChart(
                "Status das Máquinas", "Dias", "Status",
                statusDataset);
        lineChart.getTitle().setFont(new Font("Poppins", Font.PLAIN, 18));
        lineChart.getTitle().setHorizontalAlignment(HorizontalAlignment.LEFT);
        lineChart.getTitle().setPadding(0, 60, 0, 0);
        ChartPanel linePanel = new ChartPanel(lineChart);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(0, 25, 10, 50);
        add(linePanel, gbc);

        // Gráfico de Sugestões de Ação (Círculos/Pizza)
        DefaultPieDataset actionDataset = new DefaultPieDataset();
        actionDataset.setValue("19h - Reduz 62%", 62);
        actionDataset.setValue("18h - Reduz 82%", 82);
        actionDataset.setValue("20h - Reduz 75%", 75);

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Sugestões de Ação", actionDataset, true, true, false);
        pieChart.getTitle().setFont(new Font("Poppins", Font.PLAIN, 18));
        pieChart.getTitle().setHorizontalAlignment(HorizontalAlignment.LEFT);
        pieChart.getTitle().setPadding(0, 10, 5, 0);
        ChartPanel piePanel = new ChartPanel(pieChart);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.2;
        gbc.weighty = 0.3;
        gbc.insets = new Insets(30, 50, 30, 50);
        add(piePanel, gbc);

        // Gráfico de Predições de Consumo Futuro (Rosca/Pizza)
        DefaultPieDataset futureDataset = new DefaultPieDataset();
        futureDataset.setValue("Tarde", 32);
        futureDataset.setValue("Noite", 40);
        futureDataset.setValue("Manhã", 28);

        JFreeChart futureChart = ChartFactory.createRingChart(
                "Predições de Consumo Futuro", futureDataset, true, true, false);
        futureChart.getTitle().setFont(new Font("Poppins", Font.PLAIN, 18));
        futureChart.getTitle().setHorizontalAlignment(HorizontalAlignment.LEFT);
        futureChart.getTitle().setPadding(0, 10, 5, 0);
        ChartPanel futurePanel = new ChartPanel(futureChart);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(30, 50, 30, 50);
        add(futurePanel, gbc);

        // Adicionar botões pequenos abaixo dos gráficos
        JButton button1 = new JButton("ACEITAR SUGESTÃO");
        JButton button2 = new JButton("CONTROLE AUTOMÁTICO");

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(5, 0, 30, 5);
        gbc.anchor = GridBagConstraints.CENTER;  
        gbc.fill = GridBagConstraints.NONE; 
        add(button1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(5, 5, 30, 50);
        gbc.anchor = GridBagConstraints.CENTER;  
        gbc.fill = GridBagConstraints.NONE;
        add(button2, gbc);
    }

    public void showPredict() {
        setVisible(true);
    }


}


