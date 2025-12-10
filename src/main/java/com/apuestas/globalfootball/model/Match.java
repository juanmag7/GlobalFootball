package com.apuestas.globalfootball.model;

import java.time.LocalDate;

public class Match {
    private LocalDate date;
    private String homeTeam;
    private String awayTeam;
    private int homeScore;
    private int awayScore;

    public Match(LocalDate date, String homeTeam, String awayTeam, int homeScore, int awayScore) {
        this.date = date;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public LocalDate getDate() { return date; }
    public String getHomeTeam() { return homeTeam; }
    public String getAwayTeam() { return awayTeam; }
    public int getHomeScore() { return homeScore; }
    public int getAwayScore() { return awayScore; }

    @Override
    public String toString() {
        return date + " - " + homeTeam + " " + homeScore + " vs " + awayScore + " " + awayTeam;
    }
}
