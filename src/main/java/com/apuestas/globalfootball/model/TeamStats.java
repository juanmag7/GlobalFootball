package com.apuestas.globalfootball.model;

public class TeamStats {
    private String teamName;
    private int wins;
    private int draws;
    private int losses;
    private int goalsFor;
    private int goalsAgainst;

    public TeamStats(String teamName) {
        this.teamName = teamName;
    }

    public void addMatch(int scored, int conceded) {
        goalsFor += scored;
        goalsAgainst += conceded;

        if (scored > conceded) wins++;
        else if (scored == conceded) draws++;
        else losses++;
    }

    public String getTeamName() { return teamName; }
    public int getWins() { return wins; }
    public int getDraws() { return draws; }
    public int getLosses() { return losses; }
    public int getGoalsFor() { return goalsFor; }
    public int getGoalsAgainst() { return goalsAgainst; }
}

