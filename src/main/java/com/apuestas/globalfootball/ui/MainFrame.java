package com.apuestas.globalfootball.ui;

import com.apuestas.globalfootball.controller.StatsCalculator;
import com.apuestas.globalfootball.model.Match;
import com.apuestas.globalfootball.model.TeamStats;
import com.apuestas.globalfootball.util.CSVLoader;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {

    private JComboBox<String> comboTeams;
    private JLabel lblWins, lblDraws, lblLosses, lblGoalsFor, lblGoalsAgainst;
    private JTextArea txtMatches;

    public MainFrame() {
        setTitle("Global Football Analyzer");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
        loadData();
    }

    private StatsCalculator calculator;
    private List<Match> matchList;

    private void initUI() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        comboTeams = new JComboBox<>();
        comboTeams.addActionListener(e -> updateStats());

        topPanel.add(new JLabel("Selecciona un equipo:"));
        topPanel.add(comboTeams);
        add(topPanel, BorderLayout.NORTH);

        JPanel statsPanel = new JPanel(new GridLayout(5, 1));
        lblWins = new JLabel("Victorias: ");
        lblDraws = new JLabel("Empates: ");
        lblLosses = new JLabel("Derrotas: ");
        lblGoalsFor = new JLabel("Goles a favor: ");
        lblGoalsAgainst = new JLabel("Goles en contra: ");

        statsPanel.add(lblWins);
        statsPanel.add(lblDraws);
        statsPanel.add(lblLosses);
        statsPanel.add(lblGoalsFor);
        statsPanel.add(lblGoalsAgainst);

        add(statsPanel, BorderLayout.WEST);

        txtMatches = new JTextArea();
        txtMatches.setEditable(false);

        add(new JScrollPane(txtMatches), BorderLayout.CENTER);
    }

    private void loadData() {
        matchList = CSVLoader.load("results.csv");
        calculator = new StatsCalculator(matchList);

        for (String team : calculator.getTeams()) {
            comboTeams.addItem(team);
        }

        if (comboTeams.getItemCount() > 0) comboTeams.setSelectedIndex(0);
        updateStats();
    }

    private void updateStats() {
        String team = (String) comboTeams.getSelectedItem();
        if (team == null) return;

        TeamStats stats = calculator.getStats(team);

        lblWins.setText("Victorias: " + stats.getWins());
        lblDraws.setText("Empates: " + stats.getDraws());
        lblLosses.setText("Derrotas: " + stats.getLosses());
        lblGoalsFor.setText("Goles a favor: " + stats.getGoalsFor());
        lblGoalsAgainst.setText("Goles en contra: " + stats.getGoalsAgainst());

        txtMatches.setText("");

        for (Match m : matchList) {
            if (m.getHomeTeam().equals(team) || m.getAwayTeam().equals(team)) {
                txtMatches.append(m.toString() + "\n");
            }
        }
    }
}
