package ca.hccis.fortnite.bo;

import ca.hccis.fortnite.entity.PlayerStats;
import ca.hccis.fortnite.entity.ReportInput;
import ca.hccis.fortnite.jpa.entity.MatchEntry;

import java.util.ArrayList;

/**
 * Class to hold business logic needed for the report.
 *
 * @author BP
 * @since 20261030
 */
public class ReportBO {

    public static void processPlayerScoreReport(ReportInput reportInput) {

        //Get all of the matches
        MatchEntryBO matchEntryBO = new MatchEntryBO();
        ArrayList<MatchEntry> theList = matchEntryBO.processSelectAllByPlayerName(reportInput.getName());

        //Process through the matches and get score summaries.
        ArrayList<PlayerStats> playerStatsList = new ArrayList<>();
        PlayerStats playerStats = new PlayerStats();

        int matchesPlayed = 0;
        int totalEliminations = 0;
        int totalPlacement = 0;
        int totalWins = 0;
        String currentRank = "";
        double rankProgress = 0.0;
        double rankDifference = 0.0;
        double eliminationAverage = 0.0;
        double placementAverage = 0.0;
        double winAverage = 0.0;

        for (int current = 0; current < theList.size(); current++ ) {

            //filter out if match is before the start date
            if (reportInput.getStartDate() != null && !reportInput.getStartDate().isEmpty()) {
                if (theList.get(current).getMatchDate().compareTo(reportInput.getStartDate()) < 0) {
                    System.out.println("Skipping match " + theList.get(current).getMatchDate() + " because it is before the start date");
                    continue;
                }
            }
            //filter out if match is after the date
            if (reportInput.getEndDate() != null && !reportInput.getEndDate().isEmpty()) {
                if (theList.get(current).getMatchDate().compareTo(reportInput.getEndDate()) > 0) {
                    System.out.println("Skipping match " + theList.get(current).getMatchDate() + " because it is after the end date");
                    continue;
                }
            }

            if (theList.get(current).getPlayerName().equalsIgnoreCase(reportInput.getName())) {
                totalEliminations += theList.get(current).getEliminations();
                totalPlacement += theList.get(current).getPlacement();
                matchesPlayed += 1;

                if (theList.get(current).getWin()) {
                    totalWins += 1;
                }
            }

            rankDifference = theList.get(current).getRankProgress()/*-theList.get(i-1).getRankProgress()*/;
            currentRank = theList.get(current).getCurrentRank();
            rankProgress = theList.get(current).getRankProgress();
        }

        placementAverage = (double) totalPlacement / matchesPlayed;
        winAverage = (double) totalWins / matchesPlayed * 100.00;
        eliminationAverage = (double) totalEliminations / matchesPlayed;

        playerStats.setCurrentRank(currentRank);
        playerStats.setRankProgress(rankProgress);
        playerStats.setRankDifference(rankDifference);
        playerStats.setEliminations(totalEliminations);
        playerStats.setMatchesPlayed(matchesPlayed);
        playerStats.setWins(totalWins);
        playerStats.setEliminationAverage(eliminationAverage);
        playerStats.setPlacementAverage(placementAverage);
        playerStats.setWinAverage(winAverage);
        playerStats.setPlayerName(reportInput.getName());
        playerStatsList.add(playerStats);

        reportInput.setPlayerStatsList(playerStatsList);
    }
}