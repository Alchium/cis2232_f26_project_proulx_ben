package ca.hccis.fortnite.entity;

import ca.hccis.fortnite.jpa.entity.MatchEntry;

import java.util.ArrayList;

/**
 * Entity class to hold the attributes of the reports.
 * @author bjmaclean
 * @since 20261008
 */
public class ReportInput {
    private String name;
    private String startDate;
    private String endDate;

    private ArrayList<MatchEntry> matchEntries;
    private ArrayList<PlayerStats> playerStatsList;

    // Chart data (one entry per match in the selected range, in date order)
    private ArrayList<String> chartLabels = new ArrayList<>();
    private ArrayList<Double> chartScores = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public ArrayList<PlayerStats> getPlayerStatsList() {
        return playerStatsList;
    }

    public void setPlayerStatsList(ArrayList<PlayerStats> playerStatsList) {
        this.playerStatsList = playerStatsList;
    }

    public ArrayList<MatchEntry> getMatchEntries() {
        return matchEntries;
    }

    public void setMatchEntries(ArrayList<MatchEntry> matchEntries) {
        this.matchEntries = matchEntries;
    }

    public ArrayList<String> getChartLabels() {
        return chartLabels;
    }

    public void setChartLabels(ArrayList<String> chartLabels) {
        this.chartLabels = chartLabels;
    }

    public ArrayList<Double> getChartScores() {
        return chartScores;
    }

    public void setChartScores(ArrayList<Double> chartScores) {
        this.chartScores = chartScores;
    }
}