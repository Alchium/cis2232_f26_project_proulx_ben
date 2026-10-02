package ca.hccis.fortnite.entity;

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
    private int wins;
    private int totalPlacement;           // sum of finishing positions over all matches
    private double averagePlacement;
    private String lastUpdated;

    public PlayerStats() {
    }

    public PlayerStats(String playerName, String currentRank, double rankProgress,
                       double previousRankProgress, int matchesPlayed, int eliminations,
                       int wins, int totalPlacement, double averagePlacement,
                       String lastUpdated) {
        this.playerName = playerName;
        this.currentRank = currentRank;
        this.rankProgress = rankProgress;
        this.previousRankProgress = previousRankProgress;
        this.matchesPlayed = matchesPlayed;
        this.eliminations = eliminations;
        this.wins = wins;
        this.totalPlacement = totalPlacement;
        this.averagePlacement = averagePlacement;
        this.lastUpdated = lastUpdated;
    }

    /**
     * Prompt the user for the stats values.
     */
    public void getInformation() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Player Name: ");
        playerName = scanner.nextLine();

        System.out.print("Current Rank: ");
        currentRank = scanner.nextLine();

        System.out.print("Previous Rank Progress (%): ");
        previousRankProgress = scanner.nextDouble();

        System.out.print("Current Rank Progress (%): ");
        rankProgress = scanner.nextDouble();

        System.out.print("Matches Played: ");
        matchesPlayed = scanner.nextInt();

        System.out.print("Total Eliminations: ");
        eliminations = scanner.nextInt();

        System.out.print("Wins: ");
        wins = scanner.nextInt();

        System.out.print("Total Placement (sum of all finishing positions): ");
        totalPlacement = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Last Updated (date/time): ");
        lastUpdated = scanner.nextLine();
    }

    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }

    public String getCurrentRank() { return currentRank; }
    public void setCurrentRank(String currentRank) { this.currentRank = currentRank; }

    public double getRankProgress() { return rankProgress; }
    public void setRankProgress(double rankProgress) { this.rankProgress = rankProgress; }

    public double getPreviousRankProgress() { return previousRankProgress; }
    public void setPreviousRankProgress(double previousRankProgress) { this.previousRankProgress = previousRankProgress; }

    public int getMatchesPlayed() { return matchesPlayed; }
    public void setMatchesPlayed(int matchesPlayed) { this.matchesPlayed = matchesPlayed; }

    public int getEliminations() { return eliminations; }
    public void setEliminations(int eliminations) { this.eliminations = eliminations; }

    public int getWins() { return wins; }
    public void setWins(int wins) { this.wins = wins; }

    public int getTotalPlacement() { return totalPlacement; }
    public void setTotalPlacement(int totalPlacement) { this.totalPlacement = totalPlacement; }

    public double getAveragePlacement() { return averagePlacement; }
    public void setAveragePlacement(double averagePlacement) { this.averagePlacement = averagePlacement; }

    public String getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(String lastUpdated) { this.lastUpdated = lastUpdated; }

    @Override
    public String toString() {
        return String.format(
                "PlayerStats: playerName='%s', currentRank='%s', rankProgress=%.1f, " +
                        "previousRankProgress=%.1f, matchesPlayed=%d, eliminations=%d, wins=%d, " +
                        "totalPlacement=%d, averagePlacement=%.2f, lastUpdated='%s'",
                playerName, currentRank, rankProgress, previousRankProgress,
                matchesPlayed, eliminations, wins, totalPlacement, averagePlacement,
                lastUpdated);
    }
}
