package ca.hccis.fortnite.entity;

import ca.hccis.fortnite.jpa.entity.MatchEntry;

import java.util.Scanner;

/**
 * Entity holding a player's ranked Fortnite statistics.
 *
 * Note: totalPlacement and previousRankProgress are supporting fields needed
 * for the Average Placement and Rank Change calculations in the README.
 *
 * @author Ben Proulx / Claude
 * @since 20260921
 */
public class PlayerStats {

    private String playerName;
    private String currentRank;
    private double rankProgress;          // current % progress within the rank
    private double previousRankProgress;  // % progress before the latest update
    private int matchesPlayed;
    private int eliminations;
    private double eliminationAverage;
    private double placementAverage;
    private int wins;
    private double winAverage;
    private MatchEntry matchEntry;

    public PlayerStats(MatchEntry matchEntry) {
        this.matchEntry = matchEntry;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getCurrentRank() {
        return currentRank;
    }

    public void setCurrentRank(String currentRank) {
        this.currentRank = currentRank;
    }

    public double getRankProgress() {
        return rankProgress;
    }

    public void setRankProgress(double rankProgress) {
        this.rankProgress = rankProgress;
    }

    public double getPreviousRankProgress() {
        return previousRankProgress;
    }

    public void setPreviousRankProgress(double previousRankProgress) {
        this.previousRankProgress = previousRankProgress;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public int getEliminations() {
        return eliminations;
    }

    public void setEliminations(int eliminations) {
        this.eliminations = eliminations;
    }

    public double getEliminationAverage() {
        return eliminationAverage;
    }

    public void setEliminationAverage(double eliminationAverage) {
        this.eliminationAverage = eliminationAverage;
    }

    public double getPlacementAverage() {
        return placementAverage;
    }

    public void setPlacementAverage(double placementAverage) {
        this.placementAverage = placementAverage;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public double getWinAverage() {
        return winAverage;
    }

    public void setWinAverage(double winAverage) {
        this.winAverage = winAverage;
    }

    public MatchEntry getMatchEntry() {
        return matchEntry;
    }

    public void setMatchEntry(MatchEntry matchEntry) {
        this.matchEntry = matchEntry;
    }
}
