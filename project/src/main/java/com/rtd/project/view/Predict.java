package com.rtd.project.view;

import javax.swing.*;
import java.awt.*;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.rtd.project.service.ArduinoClient;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.event.ActionEvent;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.IntervalBarRenderer;
import org.jfree.data.category.DefaultIntervalCategoryDataset;


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

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(2000, 100));
        layeredPane.setBackground(new Color(255, 255, 255));
        JPanel maquinasPanel = new JPanel();
        maquinasPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        maquinasPanel.setOpaque(false);

        String[] maquinas = {"Máquina 1", "Máquina 2", "Máquina 3"};
        JComboBox<String> maquinasComboBox = new JComboBox<>(maquinas);
        maquinasComboBox.setSelectedIndex(1);
        maquinasComboBox.setFont(new Font("Poppins", Font.PLAIN, 14));
        maquinasComboBox.setPreferredSize(new Dimension(150, 25));
        maquinasComboBox.setBackground(Color.WHITE);

        maquinasPanel.add(maquinasComboBox);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(-40, 500, 0, 0);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(maquinasPanel, gbc);

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
        backButton.setBounds( 50, 90, 60, 25);
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

        JLabel text2legend = new JLabel("1-7 Ago, 2024");
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
        consumoDataset.addValue(2, "Últimos dias", "01");
        consumoDataset.addValue(4, "Últimos dias", "02");
        consumoDataset.addValue(3, "Últimos dias", "03");
        consumoDataset.addValue(5, "Últimos dias", "04");
        consumoDataset.addValue(4, "Mês anterior", "01");
        consumoDataset.addValue(3, "Mês anterior", "02");
        consumoDataset.addValue(2, "Mês anterior", "03");
        
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

        //Gráfico 2
        Number[] startTimes = {4, 5, 6, 0, 5, 6, 6};
        Number[] endTimes = {23, 22, 24, 0, 24, 21, 22};

        Number[][] durations = new Number[1][startTimes.length];

        String[] days = new String[7];
        for (int i = 0; i < 7; i++) {
            days[i] = String.format("%02d", i + 1); 
        }

        for (int i = 0; i < startTimes.length; i++) {
            durations[0][i] = endTimes[i].doubleValue() - startTimes[i].doubleValue();
        }

        DefaultIntervalCategoryDataset dataset = new DefaultIntervalCategoryDataset(
            new String[] {"Horário de Operação"}, 
            days,
            new Number[][] {startTimes},  
            new Number[][] {endTimes}     
        );



        JFreeChart chart = ChartFactory.createStackedBarChart(
            "Horários de Funcionamento",  
            "",  
            "",  
            dataset,  
            PlotOrientation.HORIZONTAL,  
            true,  
            false,  
            false  
        );

        chart.getTitle().setFont(new Font("Poppins", Font.BOLD, 18));
        chart.getTitle().setHorizontalAlignment(HorizontalAlignment.LEFT);
        chart.getTitle().setPadding(10, 20, 5, 0);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.BLACK);

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setTickUnit(new NumberTickUnit(2));  

        rangeAxis.setNumberFormatOverride(new NumberFormat() {
            @Override
            public StringBuffer format(double number, StringBuffer toAppendTo, FieldPosition pos) {
                int hours = (int) number;
                return toAppendTo.append(String.format("%02dh", hours));  
            }

            @Override
            public StringBuffer format(long number, StringBuffer toAppendTo, FieldPosition pos) {
                return format((double) number, toAppendTo, pos);
            }

            @Override
            public Number parse(String source, ParsePosition parsePosition) {
                return null;
            }
        });

        IntervalBarRenderer renderer = new IntervalBarRenderer();
        renderer.setSeriesPaint(0, new Color(255, 193, 7)); 

        plot.setRenderer(renderer);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 400));


        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(0, 25, 0, 50);
        add(chartPanel, gbc);

        
        //Legendas
        JLabel text3 = new JLabel("Sugestão de Ligamento");
        text3.setFont(new Font("Poppins", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0;
        gbc.insets = new Insets(30, 55, 0, 0);
        add(text3, gbc);

        JLabel sugest1 = new JLabel("4h: redução de 75% (11,7kW) ");
        sugest1.setFont(new Font("Arial", Font.PLAIN, 15));
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(-200, 55, 10, 10);
        add(sugest1, gbc);

        JButton buttonSugest1 = new JButton("Programar Ligamento");
        buttonSugest1.setBackground(new Color(33, 150, 243));
        buttonSugest1.setForeground(Color.WHITE);
        buttonSugest1.setBorder(new LineBorder(new Color(33, 150, 243), 5));
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(-200, 450, 10, 0);
        add(buttonSugest1, gbc);

        JLabel sugest2 = new JLabel("4h30: redução de 82% (14kW)");
        sugest2.setFont(new Font("Arial", Font.PLAIN, 15));
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0;
        gbc.insets = new Insets(-130, 55, 10, 0);
        add(sugest2, gbc);

        JButton buttonSugest2 = new JButton("Programar Ligamento");
        buttonSugest2.setBackground(new Color(33, 150, 243));
        buttonSugest2.setForeground(Color.WHITE);
        buttonSugest2.setBorder(new LineBorder(new Color(33, 150, 243), 5));
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(-130, 450, 10, 0);
        add(buttonSugest2, gbc);

        JLabel sugest3 = new JLabel("4h40h: redução de 62% (9,6kW)");
        sugest3.setFont(new Font("Arial", Font.PLAIN, 15));
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0;
        gbc.insets = new Insets(-60, 55, 10, 0);
        add(sugest3, gbc);

        JButton buttonSugest3 = new JButton("Programar Ligamento");
        buttonSugest3.setBackground(new Color(33, 150, 243));
        buttonSugest3.setForeground(Color.WHITE);
        buttonSugest3.setBorder(new LineBorder(new Color(33, 150, 243), 5));
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(-60, 450, 10, 0);
        add(buttonSugest3, gbc);

        JLabel sugest4 = new JLabel("4h50: risco de pane!");
        sugest4.setFont(new Font("Arial", Font.PLAIN, 15));
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0;
        gbc.insets = new Insets(-10, 55, 10, 0);
        add(sugest4, gbc);

        JButton buttonSugest4 = new JButton("Programar Ligamento");
        buttonSugest4.setBackground(new Color(200, 200, 200));
        buttonSugest4.setEnabled(false);
        buttonSugest4.setForeground(Color.WHITE);
        buttonSugest4.setBorder(new LineBorder(new Color(200, 200, 200), 5));
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(-10, 450, 10, 0);
        add(buttonSugest4, gbc);

        buttonSugest1.addActionListener((ActionEvent e) -> {
            try {
                arduinoClient.turnOffLed("1"); // Ligando o LED no Arduino, simulando o "ligamento"
                JOptionPane.showMessageDialog(null, "Desligamento programado às 4h: redução de 75% (11,7kW)", "Desligamento Programado", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao comunicar com o Arduino para ligar a máquina", "Erro de Comunicação", JOptionPane.ERROR_MESSAGE);
            }
        });


        buttonSugest2.addActionListener((ActionEvent e) -> {
            try {
                arduinoClient.turnOnLed("1"); // Desligando o LED no Arduino, simulando o "desligamento"
                JOptionPane.showMessageDialog(null, "Ligamento programado às 4h30: redução de 82% (14kW)", "Ligamento Programado", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao comunicar com o Arduino para desligar a máquina", "Erro de Comunicação", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonSugest3.addActionListener((ActionEvent e) -> {
            try {
                arduinoClient.turnOnLed("2"); // Ligando outro LED no Arduino
                JOptionPane.showMessageDialog(null, "Ligamento programado às 4h40: redução de 62% (9,6kW)", "Ligamento Programado", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao comunicar com o Arduino para ligar a máquina", "Erro de Comunicação", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonSugest4.addActionListener((ActionEvent e) -> {
            // Não faz nada porque está desabilitado, indicando um risco de pane
            JOptionPane.showMessageDialog(null, "Não é possível programar o ligamento devido ao risco de pane!", "Aviso de Pane", JOptionPane.WARNING_MESSAGE);
        });


        JLabel text4 = new JLabel("Sugestão de Funcionamento Automático");
        text4.setFont(new Font("Poppins", Font.BOLD, 16));

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0;
        gbc.insets = new Insets(30, 55, 0, 0);
        add(text4, gbc);

        JPanel colunasPanel = new JPanel(new GridBagLayout());
        colunasPanel.setBackground(Color.WHITE);
        colunasPanel.setBorder(new EmptyBorder(0, 0, 0, 20));
        GridBagConstraints colGbc = new GridBagConstraints();
        colGbc.fill = GridBagConstraints.HORIZONTAL;
        colGbc.insets = new Insets(5, 5, 5, 5);

        String[] textos = {
                "3h45 - 23h15: com redução de 2%",
                "4h30 - 23h15: com redução de 8%",
                "5h50 - 23h15: com redução de 12%"
        };

        for (int i = 0; i < textos.length; i++) {

            JLabel colunaTexto = new JLabel(textos[i]);
            colunaTexto.setFont(new Font("Poppins", Font.PLAIN, 14));

            colGbc.gridx = 0;
            colGbc.gridy = i;
            colGbc.weightx = 0.8;
            colGbc.fill = GridBagConstraints.HORIZONTAL;
            colunasPanel.add(colunaTexto, colGbc);

            JButton botaoControle = new JButton("Controle Automático");
            botaoControle.setBackground(new Color(255, 87, 34));
            botaoControle.setForeground(Color.WHITE);
            botaoControle.setBorder(new LineBorder(new Color(255, 87, 34), 5));
            botaoControle.setPreferredSize(new Dimension(150, 30));
            botaoControle.addActionListener((ActionEvent e) -> btControleAutomatico());

            colGbc.gridx = 1;
            colGbc.weightx = 0;
            colGbc.fill = GridBagConstraints.NONE;
            colunasPanel.add(botaoControle, colGbc);
        }


        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add(colunasPanel, gbc);


        JLabel text3cont = new JLabel("Baseado nas predições de consumo, ligar às 4h30 para reduzir o consumo  em tempo ocioso");
        text3cont.setFont(new Font("Poppins", Font.PLAIN, 14));

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0;
        gbc.insets = new Insets(20, 55, 0, 0);
        add(text3cont, gbc);

        JLabel text4cont = new JLabel("1-7 Ago, 2024");
        text4cont.setFont(new Font("Poppins", Font.PLAIN, 11));
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0;
        gbc.insets = new Insets(0, 55, 0, 0);

        add(text4cont, gbc);


        gbc.gridx = 0;
        gbc.gridy = 6;

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;

        gbc.insets = new Insets(5, 50, 5, 100);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        //Footer
        JLabel footerLabel = new JLabel("Copyright Renault. All rights reserved", JLabel.CENTER);
        footerLabel.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
        footerLabel.setForeground(Color.BLACK);

        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(10, 0, 20, 0);
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

    private void btControleAutomatico() {
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
    }

}