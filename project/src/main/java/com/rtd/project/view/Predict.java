package com.rtd.project.view;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

import com.rtd.project.service.ArduinoClient;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.event.ActionEvent;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;


public class Predict extends JFrame {
    private ArduinoClient arduinoClient;
    private Thread ledThread;

    public Predict() {
        setTitle("DashBoard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);

        arduinoClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(ArduinoClient.class, "http://192.168.4.1");

        getContentPane().setBackground(Color.WHITE);

        //Header
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(2000, 100));
        layeredPane.setBackground(new Color(255, 255, 255));

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
        layeredPane.add(personIcon, Integer.valueOf(3));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.2;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(layeredPane, gbc);

        Font buttonFont = new Font("Arial", Font.BOLD, 15);
        JButton backButton = new JButton("Voltar");
        backButton.setBackground(new Color(211, 211, 211));
        backButton.setFont(buttonFont);
        backButton.setBounds(10, 100, 100, 30);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        layeredPane.add(backButton, Integer.valueOf(2));

        backButton.addActionListener((ActionEvent e) -> {
            dispose();
            Menu menuScreen = new Menu();
            menuScreen.showMenu();
        });

        //Lengendas
        JLabel text1 = new JLabel("Consumo de Energia Atual");
        text1.setFont(new Font("Poppins", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0;
        gbc.insets = new Insets(0, 55, 0, 0);
        add(text1, gbc);

        JLabel text2 = new JLabel("Status das Máquinas");
        text2.setFont(new Font("Poppins", Font.PLAIN, 14));

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0;
        gbc.insets = new Insets(0, 55, 0, 0);
        add(text2, gbc);

        JLabel text1legend = new JLabel("1-12 Ago, 2024");
        text1legend.setFont(new Font("Poppins", Font.PLAIN, 10));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0;
        gbc.insets = new Insets(0, 55, 0, 0);
        add(text1legend, gbc);

        JLabel text2legend = new JLabel("1-6 Ago, 2024");
        text2legend.setFont(new Font("Poppins", Font.PLAIN, 10));

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0;
        gbc.insets = new Insets(0, 55, 0, 0);
        add(text2legend, gbc);

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
                "Gráfico de Consumo", "", "", consumoDataset);
        barChart.getTitle().setFont(new Font("Poppins", Font.BOLD, 18));
        barChart.getTitle().setHorizontalAlignment(HorizontalAlignment.LEFT);
        barChart.getTitle().setPadding(10, 20, 5, 0);

        barChart.getPlot().setBackgroundPaint(Color.WHITE);
        barChart.getPlot().setOutlineVisible(false);

        CategoryPlot barPlot = barChart.getCategoryPlot();
        BarRenderer barRenderer = (BarRenderer) barPlot.getRenderer();
        barRenderer.setSeriesPaint(0, new Color(255, 193, 7));
        barRenderer.setSeriesPaint(1, new Color(96, 96, 96));

        ChartPanel barPanel = new ChartPanel(barChart);
        JPanel barChartPanel = new JPanel(new BorderLayout());
        barChartPanel.add(barPanel, BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.3;
        gbc.weighty = 0.3;
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
                "", "", "",
                statusDataset);
        lineChart.getTitle().setFont(new Font("Poppins", Font.PLAIN, 18));
        lineChart.getTitle().setHorizontalAlignment(HorizontalAlignment.LEFT);
        lineChart.getTitle().setPadding(0, 60, 0, 0);
        ChartPanel linePanel = new ChartPanel(lineChart);

        CategoryPlot linePlot = lineChart.getCategoryPlot();
        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) linePlot.getRenderer();
        lineRenderer.setSeriesPaint(0, new Color(255, 193, 7));
        lineRenderer.setSeriesPaint(1, new Color(96, 96, 96));

        lineChart.getPlot().setBackgroundPaint(Color.WHITE);
        lineChart.getPlot().setOutlineVisible(false);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(0, 25, 0, 50);
        add(linePanel, gbc);

        JLabel text3 = new JLabel("Sugestão de desligamento");
        text3.setFont(new Font("Poppins", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0;
        gbc.insets = new Insets(0, 55, 0, 0);

        add(text3, gbc);

        JLabel text4 = new JLabel("Predições de Consumo Futuro");
        text4.setFont(new Font("Poppins", Font.PLAIN, 14));

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0;
        gbc.insets = new Insets(0, 55, 0, 0);
        add(text4, gbc);

        JLabel text3cont = new JLabel("Baseado nas predições de consumo, desligar às 18h para reduzir o consumo noturno");
        text3cont.setFont(new Font("Poppins", Font.PLAIN, 10));

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0;
        gbc.insets = new Insets(0, 55, 0, 0);

        add(text3cont, gbc);

        JLabel text4cont = new JLabel("1-6 Ago, 2024");
        text4cont.setFont(new Font("Poppins", Font.PLAIN, 10));
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0;
        gbc.insets = new Insets(0, 55, 0, 0);

        add(text4cont, gbc);

        //Botões
        JButton button1 = new JButton("ACEITAR SUGESTÃO");
        JButton button2 = new JButton("CONTROLE AUTOMÁTICO");

        button1.addActionListener((ActionEvent e) -> {

            try {
                arduinoClient.turnOnLed("1");
                JOptionPane.showMessageDialog(null, "Sugestão aceita e LED ligado no Arduino!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao se comunicar com o Arduino", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        button2.addActionListener((ActionEvent e) -> {

            if (ledThread != null && ledThread.isAlive()) {
                // If the thread is running, interrupt it to stop the current task
                ledThread.interrupt();
                System.out.println("Previous task interrupted.");
            } else {
                // Start the task in a new thread
                Runnable ledTask = () -> {
                    try {
                        while (!Thread.currentThread().isInterrupted()) {
                            arduinoClient.turnOnLed("3");
                            Thread.sleep(5000);  // Sleep for 5 seconds
                            arduinoClient.turnOffLed("3");
                            Thread.sleep(5000);
                        }
                    } catch (InterruptedException e1) {
                        Thread.currentThread().interrupt();  // Restore the interrupted status
                        System.out.println("Thread interrupted, stopping the task.");
                    }
                };

                // Start the new task
                ledThread = new Thread(ledTask);
                ledThread.start();
                System.out.println("New task started.");
            }
        });

        button1.setBackground(new Color(33, 150, 243));
        button1.setForeground(Color.WHITE);
        button1.setBorder(new LineBorder(new Color(33, 150, 243), 5));

        button2.setBackground(new Color(255, 87, 34));
        button2.setForeground(Color.WHITE);
        button2.setBorder(new LineBorder(new Color(255, 87, 34), 5));

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(5, 0, 30, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        add(button1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(5, 5, 30, 50);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        add(button2, gbc);

        //Footer
        JLabel footerLabel = new JLabel("Copyright Renault. All rights reserved", JLabel.CENTER);
        footerLabel.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
        footerLabel.setForeground(Color.BLACK);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(0, 0, 20, 0);
        gbc.anchor = GridBagConstraints.PAGE_END;
        add(footerLabel, gbc);

    }

    public void showPredict() {
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Predict dashboard = new Predict();
            dashboard.showPredict();
        });
    }

}
