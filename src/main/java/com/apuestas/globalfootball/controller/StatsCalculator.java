package com.apuestas.globalfootball.controller;

import com.apuestas.globalfootball.model.Match;
import com.apuestas.globalfootball.model.TeamStats;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StatsCalculator {

    private Map<String, TeamStats> stats = new HashMap<>();

    public StatsCalculator(List<Match> matches) {
        for (Match m : matches) {
            stats.putIfAbsent(m.getHomeTeam(), new TeamStats(m.getHomeTeam()));
            stats.putIfAbsent(m.getAwayTeam(), new TeamStats(m.getAwayTeam()));

            stats.get(m.getHomeTeam()).addMatch(m.getHomeScore(), m.getAwayScore());
            stats.get(m.getAwayTeam()).addMatch(m.getAwayScore(), m.getHomeScore());
        }
    }

    public Set<String> getTeams() {
        return stats.keySet();
    }

    public TeamStats getStats(String team) {
        return stats.get(team);
    }
}