package ca.hccis.files.entity;

import java.util.Scanner;

public class Match {

    private int matchNum;
    private String playerName;
    private String currentRank;
    private int rankProgress;
    private int eliminations;
    private boolean gameWon;

    public Match() {
    }

    // For assignment 1, I am interpreting each entity as a match report,
    // not each player, as the description requests match history and deletion of matches.
    public Match(int matchNum, String playerName, String currentRank, int rankProgress, int eliminations, boolean gameWon) {
        this.matchNum = matchNum;
        this.playerName = playerName;
        this.currentRank = currentRank;
        this.rankProgress = rankProgress;
        this.eliminations = eliminations;
        this.gameWon = gameWon;
    }

    public void getInformation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Match number: ");
        matchNum = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Player name: ");
        playerName = scanner.nextLine();
        System.out.print("Current rank: ");
        currentRank = scanner.nextLine();
        System.out.print("New rank progress: ");
        rankProgress = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Eliminations this match: ");
        eliminations = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Game won? (true/false): ");
        gameWon = scanner.nextBoolean();
    }

    public void edit(){
        System.out.println("--Editing Fortnite Match--");
        int mNum = ca.hccis.util.CisUtility.getInputInt("New match number: ");
        String pName = ca.hccis.util.CisUtility.getInputString("Player name: ");
        String cRank = ca.hccis.util.CisUtility.getInputString("Current rank: ");
        int rankProg = ca.hccis.util.CisUtility.getInputInt("New rank progress: ");
        int elim = ca.hccis.util.CisUtility.getInputInt("Elimination count: ");
        boolean gWon = ca.hccis.util.CisUtility.getInputBoolean("Game won? ");

        setMatchNum(mNum);
        setPlayerName(pName);
        setCurrentRank(cRank);
        setRankProgress(rankProg);
        setEliminations(elim);
        setGameWon(gWon);
    }

    public int getMatchNum() {
        return matchNum;
    }

    public void setMatchNum(int matchNum) {
        this.matchNum = matchNum;
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

    public int getRankProgress() {
        return rankProgress;
    }

    public void setRankProgress(int rankProgress) {
        this.rankProgress = rankProgress;
    }

    public int getEliminations() {
        return eliminations;
    }

    public void setEliminations(int eliminations) {
        this.eliminations = eliminations;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }

    @Override
    public String toString() {
        return String.format(
                "Match: matchNum=%d, playerName=%s, currentRank=%s, rankProgress=%d, eliminations=%d, gameWon=%s ",
                matchNum, playerName, currentRank, rankProgress,eliminations,gameWon
        );
    }

}
