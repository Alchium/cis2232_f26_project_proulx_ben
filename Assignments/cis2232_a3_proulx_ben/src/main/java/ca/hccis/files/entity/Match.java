package ca.hccis.files.entity;

import ca.hccis.util.CisUtility;

/**
 * Entity representing a single Fortnite match report.
 *
 * @author BP / Claude
 * @since 20261012
 */
public class Match {

    private int matchNum;
    private String playerName;
    private String currentRank;
    private int rankProgress;
    private int eliminations;
    private String gameWon; // "y" or "n"

    public Match() {
    }

    public Match(int matchNum, String playerName, String currentRank, int rankProgress, int eliminations, String gameWon) {
        this.matchNum = matchNum;
        this.playerName = playerName;
        this.currentRank = currentRank;
        this.rankProgress = rankProgress;
        this.eliminations = eliminations;
        this.gameWon = gameWon;
    }

    /**
     * Gather the match information from the console.
     */
    public void getInformation() {
        matchNum = CisUtility.getInputInt("Match number");
        getRemainingInformation();
    }

    /**
     * Gather every field except the match number from the console.
     */
    public void getRemainingInformation() {
        playerName = CisUtility.getInputString("Player name");
        currentRank = CisUtility.getInputString("Current rank");
        rankProgress = CisUtility.getInputInt("New rank progress");
        eliminations = CisUtility.getInputInt("Eliminations this match");
        gameWon = CisUtility.getInputString("Game won? (y/n)").trim().toLowerCase();
    }

    public int getMatchNum() { return matchNum; }
    public void setMatchNum(int matchNum) { this.matchNum = matchNum; }

    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }

    public String getCurrentRank() { return currentRank; }
    public void setCurrentRank(String currentRank) { this.currentRank = currentRank; }

    public int getRankProgress() { return rankProgress; }
    public void setRankProgress(int rankProgress) { this.rankProgress = rankProgress; }

    public int getEliminations() { return eliminations; }
    public void setEliminations(int eliminations) { this.eliminations = eliminations; }

    public String isGameWon() { return gameWon; }
    public void setGameWon(String gameWon) { this.gameWon = gameWon; }

    @Override
    public String toString() {
        return String.format(
                "Match: matchNum=%d, playerName=%s, currentRank=%s, rankProgress=%d, eliminations=%d, gameWon=%s",
                matchNum, playerName, currentRank, rankProgress, eliminations, gameWon);
    }
}
